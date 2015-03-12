package com.lyra.util.instance;

import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import net.sf.oval.constraint.NotNull;
import net.sf.oval.guard.Guarded;

/**
 * 封装了创建可排序的Set的方法
 * 
 * @author Lang
 * @see
 */
@Guarded
public final class SortedSetInstance {
	// ~ Static Fields =======================================
	/**
	 * 新建{@link java.util.TreeSet}集合：Empty
	 * 
	 * @return
	 */
	@NotNull
	public static <T> Set<T> treeSet() {
		return new TreeSet<>();
	}

	/**
	 * 新建{@link java.util.TreeSet}集合：By Collection
	 * 
	 * @param collection
	 * @return
	 */
	@NotNull
	public static <T> Set<T> treeSet(@NotNull final Collection<T> collection) {
		return new TreeSet<>(collection);
	}

	/**
	 * 新建{@link java.util.TreeSet}集合：By Comparator
	 * 
	 * @param comparator
	 * @return
	 */
	@NotNull
	public static <T> SortedSet<T> treeSet(@NotNull final Comparator<T> comparator) {
		return new TreeSet<>(comparator);
	}

	/**
	 * 新建{@link java.util.LinkedHashSet}集合：Empty
	 * 
	 * @return
	 */
	@NotNull
	public static <T> Set<T> linkedHashSet() {
		return new LinkedHashSet<>();
	}

	/**
	 * 新建{@link java.util.LinkedHashSet}集合：By Collection
	 * 
	 * @param collection
	 * @return
	 */
	@NotNull
	public static <T> Set<T> linkedHashSet(
			@NotNull final Collection<T> collection) {
		return new LinkedHashSet<>(collection);
	}

	// ~ Constructors ========================================
	private SortedSetInstance() {
	}
}
