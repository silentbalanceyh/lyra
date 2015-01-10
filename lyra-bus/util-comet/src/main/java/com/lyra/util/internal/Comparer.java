package com.lyra.util.internal;

import com.lyra.res.Symbol;

import static com.lyra.util.internal.Validator.nullable;

/**
 * 对象比较器，主要用于hashCode和equal的统一重写
 *
 * @author Lang
 * @see
 */
public final class Comparer {
	// ~ Static Methods ======================================

	/**
	 * 检查对象obj1和obj2是否相等（不检查类型） obj1 == obj2 == null -> true
	 *
	 * @param obj1
	 * @param obj2
	 * @return
	 */
	public static <T> boolean equal(final T obj1, final T obj2) {
		boolean retValue = true;
		if (nullable(obj1) && !nullable(obj2)) {
			retValue = false;
		} else if (!nullable(obj1) && nullable(obj2)) {
			retValue = false;
		} else if (!nullable(obj1) && !nullable(obj2)) {
			retValue = obj1.equals(obj2) ? true : false;
		}
		return retValue;
	}

	/**
	 * @param obj1
	 * @param obj2
	 * @param clazz
	 * @return
	 */
	public static <T> boolean equal(final T obj1, final T obj2,
			final Class<?> clazz) {
		return equal(obj1, obj2, clazz, false);
	}

	/**
	 * 检查对象obj1和obj2是否相等（检查类型）
	 *
	 * @param obj1
	 * @param obj2
	 * @param clazz
	 * @return
	 */
	public static <T> boolean equal(final T obj1, final T obj2,
			final Class<?> clazz, final boolean useInEquals) {
		boolean retValue;
		if (!equalsRef(obj1, obj2) && equalsNil(obj1, obj2)) {
			retValue = false;
		} else {
			if (nullable(obj1) && nullable(obj2)) {
				retValue = true;
			} else {
				if (instanceOf(obj2, clazz) && instanceOf(obj1, clazz)) {
					/*
					 * 当equal方法用于equals的重写的时候调用这个方法
					 * 而且当useInEquals为false时意味着下边两种调用等价 equal(obj1,obj2,clazz);
					 * equal(obj1,obj2,clazz,false);
					 */
					if (useInEquals) {
						retValue = true;
					} else {
						retValue = equal(obj1, obj2);
					}
				} else {
					retValue = false;
				}
			}
		}
		return retValue;
	}

	/**
	 * 检查对象obj1和obj2是否不相等（不检查类型）
	 *
	 * @param obj1
	 * @param obj2
	 * @return
	 */
	public static <T> boolean notEqual(final T obj1, final T obj2) {
		return !equal(obj1, obj2);
	}

	/**
	 * 根据传入hashCode叠加hash值
	 *
	 * @param seed
	 * @param field
	 * @return
	 */
	public static <T> int hash(final int seed, final T field) {
		int result = seed;
		result = Symbol.I_HASH_CODE * result
				+ (nullable(field) ? Symbol.I_ZERO : field.hashCode());
		return result;
	}

	// ~ Private Methods =====================================

	/**
	 * 【1】equals重写第一步判断 ：检查null
	 *
	 * @param obj1
	 * @param obj2
	 * @return
	 */
	private static <T> boolean equalsNil(final T obj1, final T obj2) {
		return nullable(obj1) || nullable(obj2);
	}

	/**
	 * 【2】equals重写第二步：检查引用
	 *
	 * @param obj1
	 * @param obj2
	 * @return
	 */
	private static <T> boolean equalsRef(final T obj1, final T obj2) {
		return obj1 == obj2; // NOPMD, 特殊代码
	}

	/**
	 * 【3】同instanceOf，检查类型
	 *
	 * @param obj
	 * @param clazz
	 * @return
	 */
	private static <T> boolean instanceOf(final T obj, final Class<?> clazz) {
		return obj.getClass().isAssignableFrom(clazz);
	}

	private Comparer() {
	}
}
