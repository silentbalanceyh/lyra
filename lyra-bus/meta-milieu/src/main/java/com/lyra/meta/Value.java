package com.lyra.meta;

import java.lang.reflect.Type;

/**
 * IValue interface to get field value.
 *
 * @author Lang
 */
public interface Value<T> {
	/**
	 * String Type
	 */
	String STRING = "StringType";
	/**
	 * Long Type
	 */
	String LONG = "LongType";
	/**
	 * Int Type
	 */
	String INT = "IntType";
	/**
	 * Short Type
	 */
	String SHORT = "ShortType";
	/**
	 * Boolean Type
	 */
	String BOOLEAN = "BooleanType";

	/**
	 * Get value of field
	 *
	 * @return
	 */
	T getValue();

	/**
	 * Get type of field
	 *
	 * @return
	 */
	Type getType();
}
