package com.test.lyra.util.converter;

/**
 * 存放所有com.lyra.util.converter包下的Global Method
 * 
 * @author Lang
 * @see
 */
interface ConverterConstant {
	/**
	 * StringConverter -> upper(String)
	 */
	String M_UPPER = "upper(String)";
	/**
	 * StringConverter -> lower(String)
	 */
	String M_LOWER = "lower(String)";
	/**
	 * StringConverter -> uuid(boolean)
	 */
	String M_UUID = "uuid(boolean)";
	/**
	 * ConditionalConverter -> ifToBoolean(String,String...)
	 */
	String M_IFTOBOOLEAN1 = "ifToBoolean(String,String...)";
	/**
	 * ConditionalConverter -> ifToBoolean(T,Collection<T>)
	 */
	String M_IFTOBOOLEAN2 = "ifToBoolean(T,Collection<T>)";

	/**
	 * 被测试类清单
	 * 
	 * @author Lang
	 * @see
	 */
	interface TestClasses { // NOPMD
		/** **/
		String STRING = "com.lyra.util.converter.StringConverter";
		/** **/
		String CONDITIONAL = "com.lyra.util.converter.ConditionalConverter";
	}

	/**
	 * 设置测试方法名
	 * 
	 * @param method
	 */
	void setMethod(String method);
}
