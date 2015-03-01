package com.lyra.util.internal;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.lyra.res.Regex;
import com.lyra.res.Symbol;

/**
 * 基本验证工具箱
 * 
 * @author Lang
 * @see
 */
@SuppressWarnings("unchecked")
public final class Validator {
	// ~ Static Methods ======================================
	/**
	 * 检查传入String是否数字格式
	 * @param literal
	 * @return
	 */
	public static <T> boolean numeric(final String literal){
		boolean ret = false;
		if(null != literal){
			final Pattern pattern = Pattern.compile(Regex.REG_NUMBER);
			final Matcher matcher = pattern.matcher(literal.trim());
			ret = matcher.matches();
		}
		return ret;
	}
	/**
	 * 验证引用是否为null
	 *
	 * @param obj
	 * @return
	 */
	public static <T> boolean nullable(final T obj) {
		return (null == obj) ? true : false;
	}

	/**
	 * 验证数组是否长度为0：变长参数的arr不可能为null
	 *
	 * @param arr
	 * @return
	 */
	public static <T> boolean zero(final T... arr) {
		return Symbol.I_ZERO == arr.length;
	}

	/**
	 * 检查字符串是否为null或""
	 *
	 * @param str
	 * @return
	 */
	public static boolean empty(final String str) {
		boolean ret = false;
		if (nullable(str)) {
			ret = true;
		} else {
			// Create target for PMD:InefficientEmptyStringCheck
			final String target = str.trim();
			if (Symbol.I_ZERO == target.length()) {
				ret = true;
			}
		}
		return ret;
	}

	// ~ Constructors ========================================
	private Validator() {
	}
}
