package com.rexiwastaken.read.common.te;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

import com.rexiwastaken.read.core.util.NBTSerializer;

import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;

@Retention(RUNTIME)
@Target(FIELD)
public @interface SyncVariable {
	/**
	 * The name to read/write to NBT.
	 *
	 * @return The variables NBT key
	 */
	String name();

	/**
	 * Should the variable be loaded in
	 * {@link TileEntity#load(BlockState, CompoundNBT)}?
	 *
	 * @return True if we should load on read
	 */
	boolean onRead() default true;

	/**
	 * Should the variable be saved in {@link TileEntity#save(CompoundNBT)}?
	 *
	 * @return True if we should save on write
	 */
	boolean onWrite() default true;

	/**
	 * Should the variable be saved in {@link TileEntity#getUpdatePacket} and
	 * {@link TileEntity#getUpdateTag}?
	 *
	 * @return True if we should save on sync packet
	 */
	boolean onPacket() default true;

	/**
	 * Used together with onRead, onWrite, and onPacket to determine when a variable
	 * should be saved/loaded. In most cases, you should probably just sync
	 * everything at all times.
	 */
	enum Type {
		READ, WRITE, PACKET
	}

	/**
	 * Reads/writes sync variables for any object. Used by TileEntitySL in Lib. Gems
	 * uses this in PlayerDataHandler.
	 *
	 * @author SilentChaos512
	 * @since 2.1.1
	 */
	final class Helper {
		@SuppressWarnings("rawtypes")
		static final Map<Class, NBTSerializer> SERIALIZERS = new HashMap<>();

		private Helper() {
		}

		public static <T> void registerSerializer(Class<T> clazz, Function<CompoundNBT, T> reader,
				BiConsumer<CompoundNBT, T> writer) {
			SERIALIZERS.put(clazz, new NBTSerializer<T>() {
				@Override
				public T read(CompoundNBT tags) {
					return reader.apply(tags);
				}

				@Override
				public void write(CompoundNBT tags, T obj) {
					writer.accept(tags, obj);
				}
			});
		}

		/**
		 * Reads sync variables for the object. This method will attempt to read a value
		 * from NBT and assign that value for any field marked with the SyncVariable
		 * annotation.
		 *
		 * @param obj  The object with SyncVariable fields.
		 * @param tags The NBT to read values from.
		 */
		public static void readSyncVars(Object obj, CompoundNBT tags) {
			// Try to read from NBT for fields marked with SyncVariable.
			for (Field field : obj.getClass().getDeclaredFields()) {
				for (Annotation annotation : field.getDeclaredAnnotations()) {
					if (annotation instanceof SyncVariable) {
						SyncVariable sync = (SyncVariable) annotation;

						try {
							// Set fields accessible if necessary.
							if (!field.isAccessible())
								field.setAccessible(true);
							String name = sync.name();

							// noinspection ChainOfInstanceofChecks
							if (field.getType() == int.class)
								field.setInt(obj, tags.getInt(name));
							else if (field.getType() == float.class)
								field.setFloat(obj, tags.getFloat(name));
							else if (field.getType() == String.class)
								field.set(obj, tags.getString(name));
							else if (field.getType() == boolean.class)
								field.setBoolean(obj, tags.getBoolean(name));
							else if (field.getType() == double.class)
								field.setDouble(obj, tags.getDouble(name));
							else if (field.getType() == long.class)
								field.setLong(obj, tags.getLong(name));
							else if (field.getType() == short.class)
								field.setShort(obj, tags.getShort(name));
							else if (field.getType() == byte.class)
								field.setByte(obj, tags.getByte(name));
							else if (SERIALIZERS.containsKey(field.getType())) {
								@SuppressWarnings("rawtypes")
								NBTSerializer serializer = SERIALIZERS.get(field.getType());
								CompoundNBT compound = tags.getCompound(name);
								field.set(obj, serializer.read(compound));
							} else
								throw new IllegalArgumentException(
										"Don't know how to read type " + field.getType() + " from NBT!");
						} catch (IllegalAccessException | IllegalArgumentException ex) {
							ex.printStackTrace();
						}
					}
				}
			}
		}

		/**
		 * Writes sync variables for the object. This method will take the values in all
		 * fields marked with the SyncVariable annotation and save them to NBT.
		 *
		 * @param obj      The object with SyncVariable fields.
		 * @param tags     The NBT to save values to.
		 * @param syncType The sync type (WRITE or PACKET).
		 * @return The modified tags.
		 */
		@SuppressWarnings("unchecked") // from serializer
		public static CompoundNBT writeSyncVars(Object obj, CompoundNBT tags, Type syncType) {

			// Try to write to NBT for fields marked with SyncVariable.
			for (Field field : obj.getClass().getDeclaredFields()) {
				for (Annotation annotation : field.getDeclaredAnnotations()) {
					if (annotation instanceof SyncVariable) {
						SyncVariable sync = (SyncVariable) annotation;

						// Does variable allow writing in this case?
						if (syncType == SyncVariable.Type.WRITE && sync.onWrite()
								|| syncType == SyncVariable.Type.PACKET && sync.onPacket()) {
							try {
								// Set fields accessible if necessary.
								if (!field.isAccessible())
									field.setAccessible(true);
								String name = sync.name();

								// noinspection ChainOfInstanceofChecks
								if (field.getType() == int.class)
									tags.putInt(name, field.getInt(obj));
								else if (field.getType() == float.class)
									tags.putFloat(name, field.getFloat(obj));
								else if (field.getType() == String.class)
									tags.putString(name, (String) field.get(obj));
								else if (field.getType() == boolean.class)
									tags.putBoolean(name, field.getBoolean(obj));
								else if (field.getType() == double.class)
									tags.putDouble(name, field.getDouble(obj));
								else if (field.getType() == long.class)
									tags.putLong(name, field.getLong(obj));
								else if (field.getType() == short.class)
									tags.putShort(name, field.getShort(obj));
								else if (field.getType() == byte.class)
									tags.putByte(name, field.getByte(obj));
								else if (SERIALIZERS.containsKey(field.getType())) {
									CompoundNBT compound = new CompoundNBT();
									@SuppressWarnings("rawtypes")
									NBTSerializer serializer = SERIALIZERS.get(field.getType());
									serializer.write(compound, field.get(obj));
									tags.put(name, compound);
								} else
									throw new IllegalArgumentException(
											"Don't know how to write type " + field.getType() + " to NBT!");
							} catch (IllegalAccessException | IllegalArgumentException ex) {
								ex.printStackTrace();
							}
						}
					}
				}
			}

			return tags;
		}
	}
}