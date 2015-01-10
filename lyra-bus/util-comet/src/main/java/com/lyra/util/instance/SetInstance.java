package com.lyra.util.instance;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import net.sf.oval.constraint.Min;
import net.sf.oval.constraint.NotNull;
import net.sf.oval.guard.Guarded;

/**
 * 封装了创建集合Set的方法 !:hashSet(null)的调用方式无法通过编译，因为有两个Object重载方法 所以这种重载方法中不需要设置@NotNull
 * 
 * @author Lang
 * @see
 */
@Guarded
public final class SetInstance {
	// ~ Static Methods ======================================
	/**
	 * 新建{@link java.util.HashSet}集合：Empty
	 * 
	 * @return
	 */
	@NotNull
	public static <T> Set<T> hashSet() {
		return new HashSet<>();
	}

	/**
	 * 新建{@link java.util.HashSet}集合：By Collection
	 * 
	 * @param collection
	 * @return
	 */
	@NotNull
	public static <T> Set<T> hashSet(@NotNull final Collection<T> collection) {
		return new HashSet<>(collection);
	}

	/**
	 * 新建{@link java.util.HashSet}集合：By Size
	 * 
	 * @param size
	 * @return
	 */
	@NotNull
	public static <T> Set<T> hashSet(@Min(0) final int size) {
		return new HashSet<>(size);
	}

	/**
	 * 新建{@link java.util.HashSet}集合：By Array
	 * 
	 * @param array
	 * @return
	 */
	@NotNull
	public static <T> Set<T> hashSet(@NotNull final T[] array) {
		return hashSet(Arrays.asList(array));
	}

	// ~ Constructors ========================================
	private SetInstance() {
	}
}
