package com.lyra.util.calculator;

import static com.lyra.util.instance.ArrayInstance.arrayList;
import static com.lyra.util.internal.Validator.nullable;

import java.util.List;
import java.util.Set;

import net.sf.oval.constraint.Min;
import net.sf.oval.constraint.NotNull;
import net.sf.oval.guard.Guarded;

/**
 * 索引计算器
 * 
 * @author Lang
 * @see
 */
@Guarded
public final class IndexCalculator {
	// ~ Static Methods ======================================
	/**
	 * 从集合中获取实体所在的索引值：Input = java.util.Set
	 * 
	 * @param set
	 * @param entity
	 * @return
	 */
	@Min(-1)
	public static <T> int index(@NotNull final Set<T> set,
			@NotNull final T entity) {
		return index(arrayList(set), entity);
	}

	/**
	 * 从数组中获取传入实体所在的索引值: Input = []
	 * 
	 * @param arr
	 * @param entity
	 * @return
	 */
	@Min(-1)
	public static <T> int index(@NotNull final T[] arr,
			@NotNull final T entity) {
		return index(arrayList(arr), entity);
	}

	/**
	 * 从列表中获取传入实体所在索引值：Input = java.util.List
	 * 
	 * @param list
	 * @param entity
	 * @return
	 */
	@Min(-1)
	public static <T> int index(@NotNull final List<T> list,
			@NotNull final T entity) {
		int retIndex = -1;
		final int length = list.size();
		for (int i = 0; i < length; i++) {
			if (!nullable(list.get(i)) && entity.equals(list.get(i))) {
				retIndex = i;
				break;
			}
		}
		return retIndex;
	}

	// ~ Constructors ========================================
	private IndexCalculator() {
	}
}
