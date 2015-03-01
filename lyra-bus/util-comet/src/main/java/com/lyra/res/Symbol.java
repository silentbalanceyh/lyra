package com.lyra.res;

/**
 * 系统级别符号常量
 *
 * @author Lang
 * @see
 */
public final class Symbol {
	// ~ Static Fields =======================================
	/**
	 * Type：字符串数组常量
	 */
	public static final String[] T_STR_ARR = new String[] {};
	/**
	 * Type: 类数组常量
	 */
	public static final Class<?>[] T_CLASS_ARR = new Class<?>[] {};
	/**
	 * Number：StringBuffer和StringBuilder默认长度 *
	 */
	public static final int I_STR_SIZE = 16;
	/**
	 * Number：默认HashCode常量 *
	 */
	public static final int I_HASH_CODE = 31;
	/**
	 * Number：系统默认0 *
	 */
	public static final int I_ZERO = 0;
	/**
	 * Number：系统默认1 *
	 */
	public static final int I_ONE = 1;
	/**
	 * Number：系统默认-1 *
	 */
	public static final int I_NEG_ONE = -1;
	/**
	 * Number: 系统默认范围检查值，配合索引值. 0,1的索引有值代替
	 */
	public static final int[] I_RANGE = new int[] { I_ZERO, I_ONE, 2, 3, 4, 5,
			6, 7, 8, 9 };

	/**
	 * String: 空字符串 *
	 */
	public static final String STR_EMPTY = "";
	/**
	 * String：null字符串字面量
	 */
	public static final String STR_NULL_LV = "null";
	/**
	 * String: null字符串引用 *
	 */
	public static final String STR_NULL = null;

	/**
	 * Character: 斜线 *
	 */
	public static final char C_SLASH = '/';
	/**
	 * Character: 反斜线 *
	 */
	public static final char C_BACKLASH = '\\';
	/**
	 * Character: 美元符号
	 */
	public static final char C_DOLLAR = '$';
	/**
	 * Character: 人民币 *
	 */
	public static final char C_RMB = '￥';
	/**
	 * Character: 逗号 *
	 */
	public static final char C_COMMA = ',';
	/**
	 * Character: 分号 *
	 */
	public static final char C_SEMICOLON = ';';
	/**
	 * Character: 百分号 *
	 */
	public static final char C_PERCENT = '%';
	/**
	 * Character: 空白字符 *
	 */
	public static final char C_WHITESPACE = ' ';
	/**
	 * Character: 单引号 *
	 */
	public static final char C_S_QUOTE = '\'';
	/**
	 * Character: 双引号 *
	 */
	public static final char C_D_QUOTE = '"';
	/**
	 * Character: 左小括号 *
	 */
	public static final char C_L_BRACKET = '(';
	/**
	 * Character: 右小括号 *
	 */
	public static final char C_R_BRACKET = ')';
	/**
	 * Character: 左方括号 *
	 */
	public static final char C_LS_BRACKET = '[';
	/**
	 * Character: 右方括号 *
	 */
	public static final char C_RS_BRACKET = ']';
	/**
	 * Character: 左花括号 *
	 */
	public static final char C_L_BRACE = '{';
	/**
	 * Character: 右花括号 *
	 */
	public static final char C_R_BRACE = '}';

	// ~ Constructors ========================================
	private Symbol() {
	}
}
