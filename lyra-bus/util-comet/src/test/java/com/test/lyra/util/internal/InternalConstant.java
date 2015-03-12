package com.test.lyra.util.internal;
/**
 * 存放所有com.lyra.util.internal包下的Global Method
 *
 * @author Lang
 * @see
 */
interface InternalConstant {
	/**
	 * nullable(T) *
	 */
	String M_NULLABLE = "nullable(T)";
	/**
	 * zero(T...) *
	 */
	String M_ZREO = "zero(T...)";
	/**
	 * empty(String) *
	 */
	String M_EMPTY = "empty(String)";
	/**
	 * equals(Object)
	 */
	String M_EQUALS = "equals(Object)";
	/**
	 * equal(T,T) *
	 */
	String M_EQUAL1 = "equal(T,T)";
	/**
	 * equal(T,T,Class<?>) *
	 */
	String M_EQUAL2 = "equal(T,T,Class<?>)";
	/**
	 * notEqual(T,T) *
	 */
	String M_NOTEQUAL = "notEqual(T,T)";
	/**
	 * hash(int,T) *
	 */
	String M_HASH = "hash(int,T)";
	/**
	 * number(String)
	 */
	String M_NUMERIC = "numeric(String)";
	/**
	 * 
	 *
	 * @author Lang
	 * @see
	 */
	interface TestClasses{ // NOPMD
		/** **/
		String VALILDATOR = "com.lyra.util.internal.Validator";
		/** **/
		String COMPARER = "com.lyra.util.internal.Comparer";
		/** **/
		String EQUALS = "com.test.lyra.exp.sys.internal.EqualsModel";
	}
	/**
	 * 
	 * @param method
	 */
	void setMethod(String method);
}
