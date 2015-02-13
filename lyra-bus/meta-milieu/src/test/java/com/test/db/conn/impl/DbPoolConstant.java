package com.test.db.conn.impl;
/**
 * 
 *
 * @author Lang
 * @see
 */
public interface DbPoolConstant {
	/**
	 * 被测试类清单
	 *
	 * @author Lang
	 * @see
	 */
	interface TestClasses{	// NOPMD
		/** **/
		String ADB_POOL = "com.lyra.db.conn.impl.AbstractDbPool";
	}
	/**
	 * 设置测试方法
	 * @param method
	 */
	void setMethod(String method);
}
