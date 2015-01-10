package com.lyra.util.calculator;

import static com.lyra.util.instance.SetInstance.hashSet;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import net.sf.oval.constraint.NotNull;
import net.sf.oval.guard.Guarded;

/**
 * Set,Map集合的交、并、减
 * 
 * @author Lang
 * @see
 */
@Guarded
public final class SetCalculator {
	// ~ Static Methods ======================================

	/**
	 * 求两个Set/List集合的交集
	 * 
	 * @param collectionOne
	 * @param collectionTwo
	 * @return
	 */
	@NotNull
	public static <T> Set<T> intersect(
			@NotNull final Collection<T> collectionOne,
			@NotNull final Collection<T> collectionTwo) {
		// Invariant Attribute
		final Set<T> retSet = hashSet(collectionOne);
		retSet.retainAll(collectionTwo);
		return returnSet(retSet);
	}

	/**
	 * 求两个Set/List集合的并集
	 * 
	 * @param collectionOne
	 * @param collectionTwo
	 * @return
	 */
	@NotNull
	public static <T> Set<T> union(@NotNull final Collection<T> collectionOne,
			@NotNull final Collection<T> collectionTwo) {
		// Invariant Attribute
		final Set<T> retSet = hashSet(collectionOne);
		retSet.addAll(collectionTwo);
		return returnSet(retSet);
	}

	/**
	 * 求两个Set/List集合的差集
	 * 
	 * @param collectionFrom
	 *            被减数
	 * @param collectionTo
	 *            减数
	 * @return
	 */
	@NotNull
	public static <T> Set<T> diff(@NotNull final Collection<T> collectionFrom,
			@NotNull final Collection<T> collectionTo) {
		// Invariant Attribute
		final Set<T> retSet = hashSet(collectionFrom);
		retSet.removeAll(collectionTo);
		return returnSet(retSet);
	}

	// ~ Constructors ========================================
	private SetCalculator() {
	}

	// ~ Private Methods =====================================
	@NotNull
	private static <T> Set<T> returnSet(@NotNull final Collection<T> collection) { // NOPMD
		Set<T> retSet = hashSet();
		if (collection instanceof List) {
			if (!collection.isEmpty()) {
				retSet = hashSet(collection);
			}
		} else {
			retSet = (Set<T>) collection;
		}
		return retSet;
	}
}
