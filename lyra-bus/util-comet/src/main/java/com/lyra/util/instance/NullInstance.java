package com.lyra.util.instance;


/**
 * 创建空实例，释放资源用
 * 
 * @author Lang
 * @see
 */
public final class NullInstance {
	// ~ Static Methods ======================================
	/**
	 * 用于null赋值语句
	 * 
	 * @return
	 */
	public static <T> T nullObj() {
		return null;
	}

	// ~ Constructors ========================================
	private NullInstance() {
	}
	// ~ Private Methods =====================================
}
