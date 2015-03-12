package com.lyra.util.instance;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

import net.sf.oval.constraint.NotNull;
import net.sf.oval.guard.Guarded;

/**
 * 封装了创建Map/Map的方法
 * 
 * @author Lang
 * @see
 */
@Guarded
public final class MapInstance {
	// ~ Static Methods ======================================
	/**
	 * 新建{@link java.util.HashMap}或
	 * {@link java.util.concurrent.ConcurrentHashMap}的哈希表: Empty
	 * 
	 * @param isConcurrent
	 *            是否并发
	 * @return
	 */
	@NotNull
	public static <K, V> Map<K, V> hashMap(final boolean isConcurrent) {
		Map<K, V> retMap;
		if (isConcurrent) {
			retMap = new ConcurrentHashMap<>();
		} else {
			retMap = new HashMap<>();
		}
		return retMap;
	}

	/**
	 * 新建{@link java.util.HashMap}或
	 * {@link java.util.concurrent.ConcurrentHashMap}的哈希表: By Map
	 * 
	 * @param map
	 * @return
	 */
	@NotNull
	public static <K, V> Map<K, V> hashMap(final boolean isConcurrent,
			@NotNull final Map<K, V> map) {
		Map<K, V> retMap;
		if (isConcurrent) {
			retMap = new ConcurrentHashMap<>(map);
		} else {
			retMap = new HashMap<>(map);
		}
		return retMap;
	}

	/**
	 * 新建{@link java.util.TreeMap}的可排序哈希表：Empty
	 * 
	 * @return
	 */
	@NotNull
	public static <K, V> Map<K, V> treeMap() {
		return new TreeMap<>();
	}

	/**
	 * 新建{@link java.util.TreeMap}的可排序哈希表：By Map
	 * 
	 * @param map
	 * @return
	 */
	@NotNull
	public static <K, V> Map<K, V> treeMap(
			@NotNull final Map<K, V> map) {
		return new TreeMap<>(map);
	}

	/**
	 * 新建{@link java.util.LinkedHashMap}的哈希链表：Empty
	 * 
	 * @return
	 */
	@NotNull
	public static <K, V> Map<K, V> linkedHashMap() {
		return new LinkedHashMap<>();
	}

	/**
	 * 新建{@link java.util.LinkedHashMap}的哈希链表：By Map
	 * 
	 * @param map
	 * @return
	 */
	@NotNull
	public static <K, V> Map<K, V> linkedHashMap(
			@NotNull final Map<K, V> map) {
		return new LinkedHashMap<>(map);
	}

	// ~ Constructors ========================================
	private MapInstance() {
	}
}
