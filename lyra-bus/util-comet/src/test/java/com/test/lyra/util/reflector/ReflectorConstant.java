package com.test.lyra.util.reflector;
/**
 * 存放所有com.lyra.util.reflector包下的Global Method
 *
 * @author Lang
 * @see
 */
interface ReflectorConstant {
	/**
	 * Types.types(Class<?>)
	 */
	String M_TYPES = "types(Class<?>)";
	/**
	 * Types.supers(Class<?>)
	 */
	String M_SUPERS = "supers(Class<?>)";
	/**
	 * Types.interfaces(Class<?>)
	 */
	String M_INTERFACES = "interfaces(Class<?>)";
	/**
	 * Factory.instance(Class<?>,Object...)
	 */
	String M_INSTANCE1 = "instance(Class<?>,Object...)";
	/**
	 * Factory.instance(String,Object...)
	 */
	String M_INSTANCE2 = "instance(String,Object...)";
	/**
	 * Factory.singleton(String,Object...)
	 */
	String M_SINGLETON1 = "singleton(String,Object...)";
	/**
	 * Factory.singleton(String,Object...)
	 */
	String M_SINGLETON2 = "singleton(Class<?>,Object...)";
	/**
	 * Factory.singleton(String,Map<String,T>,Object...)
	 */
	String M_SINGLETON3 = "singleton(String,Map<String,T>,Object...)";
	/**
	 * Factory.singleton(Class<?>,Map<String,T>,Object...)
	 */
	String M_SINGLETON4 = "singleton(Class<?>,Map<String,T>,Object...)";
	/** **/
	String T_TARGET = "com.test.lyra.util.assist.InstanceModel";
	/**
	 * @author Lang
	 * @see
	 */
	interface TestClasses{ // NOPMD
		/** **/
		String TYPES = "com.lyra.util.reflector.Types";
		/** **/
		String FACTORY = "com.lyra.util.reflector.Factory";
	}
	/**
	 * 
	 * @param method
	 */
	void setMethod(String method);
}
