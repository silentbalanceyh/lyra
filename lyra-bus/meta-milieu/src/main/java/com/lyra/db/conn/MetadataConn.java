package com.lyra.db.conn;

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
	// endregion
}
