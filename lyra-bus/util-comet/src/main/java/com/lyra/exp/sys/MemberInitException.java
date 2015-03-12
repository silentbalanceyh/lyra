package com.lyra.exp.sys;

import com.lyra.exp.AbstractSystemException;

/**
 * 【Runtime异常】在类文件中初始化成员失败时候会抛出此异常，该调用时不会启动AOP的防御网
 *
 * @author Lang
 * @package com.lyra.orb.strategy.exp.sys
 * @name MemberInitException
 * @class com.lyra.orb.strategy.exp.sys.MemberInitException
 * @date Oct 10, 2014 4:20:24 PM
 * @see
 */
public class MemberInitException extends AbstractSystemException {

	// ~ Static Fields =======================================
	/**
     *
     */
	private static final long serialVersionUID = -1899652229369453366L;

	// ~ Constructors ========================================

	/**
	 * @param clazz
	 * @param memberName
	 */
	public MemberInitException(final Class<?> clazz, final String memberName) {
		super("[E] Class -> " + clazz.getName() + ",Member -> " + memberName
				+ " could not be initialized!");
	}

	// ~ Override Methods ====================================

	/**
     *
     */
	@Override
	public int getErrorCode() {
		return -20001;
	}
}
