package com.lyra.util.converter;

import static com.lyra.util.instance.SetInstance.hashSet;

import java.util.Collection;
import java.util.Set;

import net.sf.oval.constraint.NotBlank;
import net.sf.oval.constraint.NotEmpty;
import net.sf.oval.constraint.NotNull;
import net.sf.oval.guard.Guarded;

/**
 * 条件转换判断
 * 
 * @author Lang
 * @see
 */
@Guarded
public final class ConditionalConverter {
	// ~ Static Methods ======================================
	/**
	 * 如果collectionStrs中包含了literal就返回true，反之返回false
	 * 
	 * @param literal
	 * @param collectionStrs
	 * @return
	 */
	public static boolean ifToBoolean(
			@NotNull @NotEmpty @NotBlank final String literal,
			@NotNull final String... collectionStrs) {
		final Set<String> strSet = hashSet(collectionStrs);
		return ifToBoolean(literal, strSet);
	}

	/**
	 * 如果collection中包含了entity就返回true，反之返回false
	 * 
	 * @param entity
	 * @param collection
	 * @return
	 */
	public static <T> boolean ifToBoolean(
			@NotNull final T entity,
			@NotNull final Collection<T> collection) {
		boolean ret = false;
		if (collection.contains(entity)) {
			ret = true;
		}
		return ret;
	}

	// ~ Constructors ========================================
	private ConditionalConverter() {
	}
}
