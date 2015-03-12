package com.lyra.exp.sys;

import com.lyra.exp.AbstractSystemException;

/**
 * 【Runtime异常】资源文件读取异常，如果资源文件不存在的时候抛出该异常
 *
 * @author Lang
 * @package com.lyra.exp.sys
 * @name ResourceIOException
 * @class com.lyra.exp.sys.ResourceIOException
 * @date Oct 11, 2014 12:27:52 AM
 * @see
 */
public class ResourceIOException extends AbstractSystemException {

	// ~ Static Fields =======================================
	/**
     *
     */
	private static final long serialVersionUID = -6683492976884425722L;

	// ~ Constructors ========================================

	/**
	 * @param clazz
	 * @param methodName
	 */
	public ResourceIOException(final Class<?> clazz, final String methodName) {
		super("[E] Class -> " + clazz.getName() + ",Method -> " + methodName
				+ ",Resource couldn't not be read, please check the path!");
	}

	// ~ Override Methods ====================================

	/**
     *
     */
	@Override
	public int getErrorCode() {
		return -20002;
	}
}
