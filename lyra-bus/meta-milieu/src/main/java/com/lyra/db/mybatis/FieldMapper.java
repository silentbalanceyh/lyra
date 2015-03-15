package com.lyra.db.mybatis;

import com.lyra.mod.sys.FieldModel;

/**
 * 
 *
 * @author Lang
 * @see
 */
public interface FieldMapper {
	/**
	 * 添加Field字段记录
	 * @param field
	 * @return 插入Field数据影响行数
	 */
	int insertField(FieldModel field);
	/**
	 * 更新Field字段记录
	 * @param field
	 * @return 更新Field数据影响行数
	 */
	int updateField(FieldModel field);
	/**
	 * 根据Field的ID删除记录
	 * @param uniqueId
	 * @return
	 */
	boolean deleteById(String uniqueId);
	/**
	 * 根据Field的metaId删除记录
	 * @param metaId
	 * @return
	 */
	boolean deleteByMeta(String metaId);
	/**
	 * 根据Field的ID获取Field记录
	 * @param uniqueId
	 * @return
	 */
	FieldModel selectById(String uniqueId);
	/**
	 * 根据Field的metaId获取Field记录
	 * @param metaId
	 * @return
	 */
	FieldModel selectByMeta(String metaId);
}
