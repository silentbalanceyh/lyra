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
	// endregion
}
