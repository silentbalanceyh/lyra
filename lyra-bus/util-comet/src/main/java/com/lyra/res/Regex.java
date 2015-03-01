package com.lyra.res;
/**
 * 正则表达式
 *
 * @author Lang
 * @see
 */
public final class Regex {
	// ~ Static Fields =======================================
	/**
	 * 浮点数的正则表达式，包括带+和-前缀，以及小数点
	 */
	public static final String REG_NUMBER = "^(\\-|\\+){0,1}[0-9]+\\.{0,1}[0-9]*$";
	/**
	 * 整数的正则表达式，包括带+和-前缀
	 */
	public static final String REG_INTEGER = "^(\\-|\\+){0,1}[0-9]+$";
	// ~ Constructors ========================================
	private Regex(){}
	// ~ Private Methods =====================================
}
