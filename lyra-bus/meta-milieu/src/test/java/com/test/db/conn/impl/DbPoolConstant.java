package com.test.db.conn.impl;
/**
 * 
 *
 * @author Lang
 * @see
 */
interface DbPoolConstant {
	/**
	 * 被测试类清单
	 *
	 * @author Lang
	 * @see
	 */
	interface TestClasses{	// NOPMD
		/** **/
		String ADB_POOL = "com.lyra.db.pool.AbstractDbPool";
		/** **/
		String BONE_POOL = "com.lyra.db.pool.BoneCPPool";
	}
	/**
	 * 设置测试方法
	 * @param method
	 */
	void setMethod(String method);
}
