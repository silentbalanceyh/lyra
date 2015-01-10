package com.test.lyra.util.logger;

/**
 * 存放所有com.lyra.util.logger包下的Global Method
 *
 * @author Lang
 * @see
 */
interface LoggerConstant {
	/**
	 * Logger -> info(Class<?>,Object,Throwable...)
	 */
	String M_INFO = "info(Class<?>,Object,Throwable...)";
	/**
	 * Logger -> trace(Class<?>,Object,Throwable...)
	 */
	String M_TRACE = "trace(Class<?>,Object,Throwable...)";
	/**
	 * Logger -> debug(Class<?>,Object,Throwable...)
	 */
	String M_DEBUG = "debug(Class<?>,Object,Throwable...)";
	/**
	 * Logger -> warn(Class<?>,Object,Throwable...)
	 */
	String M_WARN = "warn(Class<?>,Object,Throwable...)";
	/**
	 * Logger -> error(Class<?>,Object,Throwable...)
	 */
	String M_ERROR = "error(Class<?>,Object,Throwable...)";
	/**
	 * Logger -> info(Class<?>,Object,Throwable...)
	 */
	String M_FATAL = "fatal(Class<?>,Object,Throwable...)";
	/**
	 * Console -> info(Class<?>,Collection<?>)
	 */
	String M_CONSOLE1 = "info(Class<?>,Collection<?>)";
	/**
	 * Console -> info(Class<?>,T[])
	 */
	String M_CONSOLE2 = "info(Class<?>,T[])";
	/**
	 * Console -> info(Class<?>,Map<?,?>)
	 */
	String M_CONSOLE3 = "info(Class<?>,Map<?,?>)";

	/**
	 * 被测试类清单
	 *
	 * @author Lang
	 * @see
	 */
	interface TestClasses { // NOPMD
		/** **/
		String LOGGER = "com.lyra.util.logger.Logger";
		/** **/
		String CONSOLE = "com.lyra.util.logger.Console";
	}
	/**
	 * 
	 * @param method
	 */
	void setMethod(String method);
}
