package com.lyra.util;

import jodd.log.Logger;
import jodd.log.LoggerFactory;

import org.apache.commons.pool2.KeyedPooledObjectFactory;

/**
 * 
 *
 * @author Lang
 * @see
 */
public final class Reflector {
	// ~ Static Fields =======================================
	/** **/
	private static final Logger LOGGER = LoggerFactory.getLogger(Reflector.class);
	// ~ Instance Fields =====================================
	// ~ Static Block ========================================
	// ~ Static Methods ======================================
	/**
	 * 构造一个新的实例
	 * @param clazz
	 * @param params
	 * @return
	 */
	public static <T> T instance(final Class<?> clazz,final Object... params){
		T ret = null;
		try{
			
		}catch(SecurityException ex){
			if(LOGGER.isErrorEnabled()){
				LOGGER.error("[E] Security issue.", ex);
			}
		}
		return ret;
	}
	// ~ Constructors ========================================
	// ~ Abstract Methods ====================================
	// ~ Override Methods ====================================
	// ~ Methods =============================================
	// ~ Private Methods =====================================
	private Reflector(){}
	// ~ hashCode,equals,toString ============================
}
