package com.lyra.db.conn;

import java.io.InputStream;

import com.lyra.meta.database.Metadata;

/**
 * 元数据操作
 * @author Lang
 */
public interface MetadataConn {
	// region Metadata: Database Information
	/**
	 * 获取当前数据库连接的元数据
	 * @return
	 */
	Metadata getMetadata();
	/**
	 * 导入SQL文件
	 * @param in
	 * @return
	 */
	boolean loadSqlFile(InputStream in);
	/**
	 * H2 Database中创建对应的表结构
	 * @return
	 */
	boolean initMeta(InputStream in);
	/**
	 * H2 元数据数据库用户名
	 */
	String H2_USERNAME = "h2lyra";
	/**
	 * H2 元数据数据库密码
	 */
	String H2_PWD = "h2lyra";
	/**
	 * H2 初始化SQL语句
	 */
	String H2_SQL = "INIT.sql";
	/**
	 * H2 数据库驱动类
	 */
	String H2_DRIVER = "org.h2.Driver";
	// endregion
}
