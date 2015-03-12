package com.lyra.util.calculator;

import static com.lyra.util.instance.MapInstance.hashMap;

import java.util.Map;
import java.util.Set;

import net.sf.oval.constraint.NotNull;
import net.sf.oval.guard.Guarded;

/**
 * Map集合的交、并、减：By Key
 *
 * @author Lang
 * @see
 */
@Guarded
public final class MapCalculator {
	// ~ Static Methods ======================================
	/**
	 * 求两个Map集合的交集：By Key
	 * 
	 * @param mapOne
	 * @param mapTwo
	 * @return
	 */
	@NotNull
	public static <K, V> Map<K, V> intersect(@NotNull final Map<K, V> mapOne,
			@NotNull final Map<K, V> mapTwo) {
		// 因为重名，必须使用：SetCalculator.intersect调用
		final Set<K> keys = SetCalculator.intersect(mapOne.keySet(),
				mapTwo.keySet());
		// Invariant Attribute
		final Map<K, V> retMap = hashMap(true);
		for (final K key : keys) {
			// mapOne.get(key) 和 mapTwo.get(key)相同
			retMap.put(key, mapOne.get(key));
		}
		return retMap;
	}

	/**
	 * 求两个Map集合的并集：By Key
	 * 
	 * @param mapOne
	 * @param mapTwo
	 * @return
	 */
	@NotNull
	public static <K, V> Map<K, V> union(@NotNull final Map<K, V> mapOne,
			@NotNull final Map<K, V> mapTwo) {
		// Invariant Attribute
		final Map<K, V> retMap = hashMap(true);
		retMap.putAll(mapOne);
		retMap.putAll(mapTwo);
		return retMap;
	}

	/**
	 * 求两个Map集合的差集：By Key
	 * 
	 * @param mapFrom
	 * @param mapTo
	 * @return
	 */
	@NotNull
	public static <K, V> Map<K, V> diff(@NotNull final Map<K, V> mapFrom,
			@NotNull final Map<K, V> mapTo) {
		final Set<K> toKeys = mapTo.keySet();
		// Invariant Attribute
		final Map<K, V> retMap = hashMap(true);
		retMap.putAll(mapFrom);
		for (final K key : toKeys) {
			if (mapFrom.containsKey(key)) {
				retMap.remove(key);
			}
		}
		return retMap;
	}

	// ~ Constructors ========================================
	private MapCalculator() {
	}
}
