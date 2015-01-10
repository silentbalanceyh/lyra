package com.lyra.util.instance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import net.sf.oval.constraint.Min;
import net.sf.oval.constraint.NotNull;
import net.sf.oval.guard.Guarded;

/**
 * 封装了创建数组List的方法
 * 
 * @author Lang
 * @see
 */
@Guarded
public final class ArrayInstance {
	// ~ Static Methods ======================================
	/**
	 * 新建{@link java.util.ArrayList}集合：Empty
	 *
	 * @return
	 */
	@NotNull
	public static <T> List<T> arrayList() {
		return new ArrayList<>();
	}

	/**
	 * 新建{@link java.util.ArrayList}集合：By length
	 *
	 * @param length
	 * @return
	 */
	@NotNull
	public static <T> List<T> arrayList(@Min(0) final int length) {
		return new ArrayList<>(length);
	}

	/**
	 * 新建{@link java.util.ArrayList}集合：By Array
	 * 
	 * @param array
	 * @return
	 */
	@NotNull
	public static <T> List<T> arrayList(@NotNull final T[] array) {
		return Arrays.asList(array);
	}

	/**
	 * 新建{@link java.util.ArrayList}集合：Set -> List
	 *
	 * @param inputSet
	 * @return
	 */
	@NotNull
	public static <T> List<T> arrayList(@NotNull final Collection<T> inputSet) {
		return new ArrayList<>(inputSet);
	}

	// ~ Constructors ========================================
	private ArrayInstance() {
	}
}
