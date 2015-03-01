package com.lyra.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import net.sf.oval.exception.ConstraintsViolatedException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esotericsoftware.reflectasm.ConstructorAccess;

/**
 * 不启用OVal，因为反射部分需要知道的是实际对象的约束防御异常，而不是当前的
 * 
 * @author Lang
 * @see
 */
@SuppressWarnings("unchecked")
public final class Instance {
	// ~ Static Fields =======================================
	/** **/
	private static final Logger LOGGER = LoggerFactory
			.getLogger(Instance.class);
	/** KEY -> POOL **/
	private static final ConcurrentMap<String, Object> OBJ_POOLS = new ConcurrentHashMap<>();

	// ~ Instance Fields =====================================
	// ~ Static Block ========================================
	// ~ Static Methods ======================================
	/**
	 * 支持带Pool模式的单例
	 * 
	 * @param className
	 * @param key
	 * @param objectPool
	 * @param params
	 * @return
	 */
	public static <T> T singleton(final ConcurrentMap<String, T> objectPool,
			final String key, final String className, final Object... params) {
		T ret = objectPool.get(key);
		if (null == ret) {
			ret = instance(className, params);
			if (null != ret) {
				objectPool.put(key, ret);
			}
		}
		return ret;
	}

	/**
	 * 支持带Pool模式的单例
	 * 
	 * @param clazz
	 * @param key
	 * @param objectPool
	 * @param params
	 * @return
	 */
	public static <T> T singleton(final ConcurrentMap<String, T> objectPool,
			final String key, final Class<?> clazz, final Object... params) {
		T ret = objectPool.get(key);
		if (null == ret) {
			ret = instance(clazz, params);
			if (null != ret) {
				objectPool.put(key, ret);
			}
		}
		return ret;
	}

	/**
	 * 
	 * @param clazz
	 * @param params
	 * @return
	 */
	public static <T> T singleton(final Class<?> clazz, final Object... params) {
		return (T) singleton(OBJ_POOLS, clazz.getName(), clazz, params);
	}

	/**
	 * 全环境的singleton单例模式
	 * 
	 * @param className
	 * @param params
	 * @return
	 */
	public static <T> T singleton(final String className,
			final Object... params) {
		return (T) singleton(OBJ_POOLS, className, className, params);
	}

	/**
	 * 
	 * @param clazz
	 * @param params
	 * @return
	 */
	public static <T> T instance(final Class<?> clazz, final Object... params) {
		T ret = null;
		if (null != clazz) {
			try {
				if (0 == params.length) {
					ret = construct(clazz);
				} else {
					ret = construct(clazz, params);
				}
			} catch (ConstraintsViolatedException ex) { // NOPMD
				throw ex;
			} catch (SecurityException ex) {
				if (LOGGER.isDebugEnabled()) {
					LOGGER.debug("[E] Security issue.", ex);
				}
			}
		}
		return ret;
	}

	/**
	 * 构造一个新的实例
	 * 
	 * @param className
	 * @param params
	 * @return
	 */
	public static <T> T instance(final String className, final Object... params) {
		final Class<?> clazz = clazz(className);
		return instance(clazz, params);
	}

	// ~ Constructors ========================================
	private Instance() {
	}

	// ~ Abstract Methods ====================================
	// ~ Override Methods ====================================
	// ~ Methods =============================================
	// ~ Private Methods =====================================

	private static <T> T construct(final Class<?> clazz, final Object... params) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug(clazz.getName()
					+ " -> JDK reflection create instance.");
		}
		T ret = null;
		if (0 < params.length) {
			final Constructor<?>[] constructors = clazz
					.getDeclaredConstructors();
			for (final Constructor<?> constructor : constructors) {
				// 参数长度不匹配，直接略过
				if (params.length != constructor.getParameterTypes().length) {
					continue;
				}
				ret = construct(constructor, params);
				if (null != ret) {
					break;
				}
			}
		} else {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug(clazz.getName()
						+ " -> ASM reflection create instance.");
			}
			final ConstructorAccess<?> access = ConstructorAccess.get(clazz);
			ret = (T) access.newInstance();
		}
		return ret;
	}

	private static <T> T construct(final Constructor<?> constructor,
			final Object... params) {
		if (!constructor.isAccessible()) {
			constructor.setAccessible(true);
		}
		T ret = null;
		try {
			ret = (T) (constructor.newInstance(params));
		} catch (InvocationTargetException ex) {
			if (ex.getTargetException() instanceof ConstraintsViolatedException) {
				throw (ConstraintsViolatedException) ex.getTargetException();
			}
		} catch (IllegalArgumentException ex) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("[E] IllegalArgument", ex);
			}
		} catch (InstantiationException | IllegalAccessException ex) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug(
						"[E] Instantiation | IllegalAccess | InvocationTarget ",
						ex);
			}
		}
		return ret;
	}

	private static Class<?> clazz(final String className) {
		Class<?> ret = null;
		try {
			ret = Class.forName(className);
		} catch (ClassNotFoundException ex) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("[E] Class Not found: " + className, ex);
			}
		}
		return ret;
	}
	// ~ hashCode,equals,toString ============================
}