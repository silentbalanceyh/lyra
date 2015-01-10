package com.lyra.util.reflector;

import static com.lyra.util.instance.MapInstance.hashMap;
import static com.lyra.util.instance.NullInstance.nullObj;
import static com.lyra.util.internal.Validator.empty;
import static com.lyra.util.internal.Validator.nullable;
import static com.lyra.util.internal.Validator.zero;
import static com.lyra.util.logger.Logger.trace;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import net.sf.oval.constraint.NotBlank;
import net.sf.oval.constraint.NotEmpty;
import net.sf.oval.constraint.NotNull;
import net.sf.oval.guard.Guarded;

import com.lyra.res.Symbol;

/**
 * 工厂通用方法
 *
 * @author Lang
 * @see
 */
@Guarded
@SuppressWarnings("unchecked")
public final class Factory {
	// ~ Static Fields =======================================
	/** Singleton Object Pool **/
	private static final Map<String, Object> OBJ_POOLS = hashMap(true);

	// ~ Static Methods ======================================
	/**
	 * 特殊方法用于制定KEY的Pool，只用于空参构造
	 * @param clazz
	 * @param key
	 * @param pool
	 * @return
	 */
	@NotNull
	public static <T> T singleton(@NotNull final Class<?> clazz,
			@NotNull final String key,
			@NotNull final Map<String,T> pool){
		trace(Factory.class,clazz.getCanonicalName() + " : singleton(Class<?>,String,Map<String,T>)");
		T retObj = nullObj();
		final Object instance = pool.get(key);
		if(nullable(instance)){
			retObj = instance(clazz);
			pool.put(key, retObj);
		}else{
			retObj = (T)instance;
		}
		return retObj;
	}
	/**
	 * 创建一个池化单例
	 * 
	 * @param pool
	 * @param clazz
	 * @param params
	 * @return
	 */
	@NotNull
	public static <T> T singleton(@NotNull final Class<?> clazz,
			@NotNull final Map<String, T> pool, @NotNull final Object... params) {
		trace(Factory.class,clazz.getCanonicalName() + " : singleton(Class<?>,Map<String,T>,Object...)");
		// 计算Pool的Key
		String key = getPoolKey(clazz, Symbol.STR_EMPTY);
		if (!zero(params) && !nullable(params[Symbol.I_ZERO])) {
			key = getPoolKey(clazz, params[Symbol.I_ZERO].toString());
		}
		trace(clazz, "Key:" + key);
		// 获取实例
		T retObj = nullObj();
		final Object instance = pool.get(key);
		if (nullable(instance)) {
			retObj = instance(clazz, params);
			if(!nullable(retObj)){
				pool.put(key, retObj);
			}
		} else {
			retObj = (T) instance;
		}
		return retObj;
	}

	/**
	 * 
	 * @param pool
	 * @param className
	 * @param params
	 * @return
	 */
	@NotNull
	public static <T> T singleton(
			@NotNull @NotEmpty @NotBlank final String className,
			@NotNull final Map<String, T> pool, final Object... params) {
		trace(Factory.class,className + " : singleton(String,Map<String,T>,Object...)");
		return singleton(getClass(className), pool, params);
	}
	/**
	 * 
	 * @param clazz
	 * @param params
	 * @return
	 */
	@NotNull
	public static <T> T singleton(@NotNull final Class<?> clazz,
			final Object... params) {
		trace(Factory.class,clazz.getCanonicalName() + " : singleton(Class<?>,Object...)");
		return (T)singleton(clazz, OBJ_POOLS, params);
	}
	/**
	 * 
	 * @param className
	 * @param params
	 * @return
	 */
	@NotNull
	public static <T> T singleton(
			@NotNull @NotBlank @NotEmpty final String className,
			final Object... params) {
		trace(Factory.class,className + " : singleton(String,Object...)");
		return (T)singleton(className, OBJ_POOLS, params);
	}

	/**
	 * 创建一个新实例
	 * 
	 * @param className
	 * @param params
	 * @return
	 */
	@NotNull
	public static <T> T instance(
			@NotNull @NotEmpty @NotBlank final String className,
			final Object... params) {
		trace(Factory.class,className + " : instance(String,Object...)");
		return instance(getClass(className), params);
	}

	/**
	 * 构造一个新的实例
	 * 
	 * @param clazz
	 * @param params
	 * @param <T>
	 * @return
	 */
	@NotNull
	public static <T> T instance(@NotNull final Class<?> clazz,
			final Object... params) {
		trace(Factory.class,clazz.getCanonicalName() + " : instance(Class<?>,Object...)");
		T retInstance = nullObj();
		try {
			final Constructor<?>[] constructors = clazz
					.getDeclaredConstructors();
			for (final Constructor<?> constructor : constructors) {
				if (!constructor.isAccessible()) {
					constructor.setAccessible(true);
				}
				trace(clazz, constructor);
				try {
					retInstance = (T) (constructor.newInstance(params));
				} catch (IllegalArgumentException ex) {
					trace(Factory.class, ex.getMessage(), ex);
					continue;
				} catch (InstantiationException | IllegalAccessException
						| InvocationTargetException ex) {
					trace(Factory.class, ex.getMessage(), ex);
					continue;
				}
				if (!nullable(retInstance)) {
					break;
				}
			}
		} catch (SecurityException ex) {
			trace(Factory.class, ex);
		}
		return retInstance;
	}

	// ~ Constructors ========================================
	private Factory() {
	}

	// ~ Private Methods =====================================

	private static Class<?> getClass(
			@NotNull @NotEmpty @NotBlank final String className) {
		Class<?> retClass = nullObj();
		try {
			retClass = Class.forName(className);
		} catch (ClassNotFoundException ex) {
			trace(Factory.class, ex);
		}
		return retClass;
	}

	@NotNull
	private static String getPoolKey(@NotNull final Class<?> clazz,
			@NotNull final String key) {
		String retKey;
		if (empty(key)) {
			retKey = clazz.getName();
		} else {
			retKey = clazz.getName() + key;
		}
		return retKey;
	}
}
