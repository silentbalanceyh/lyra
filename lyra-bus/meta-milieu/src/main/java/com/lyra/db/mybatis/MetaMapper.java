package com.lyra.db.mybatis;

import com.lyra.mod.sys.MetaModel;

/**
 * 
 *
 * @author Lang
 * @see
 */
public interface MetaMapper {
	/**
	 * 添加元数据记录
	 * @param meta
	 * @return 传入数据影响行数
	 */
	int insertMeta(MetaModel meta);
	/**
	 * 更新元数据记录
	 * @param meta
	 * @return 更新数据影响行数
	 */
	int updateMeta(MetaModel meta);
	/**
	 * 根据Meta的ID删除Meta记录
	 * @param uniqueId
	 * @return
	 */
	boolean deleteById(String uniqueId);
	/**
	 * 根据Meta的namespace和name删除Meta记录
	 * @param namespace
	 * @param name
	 * @return
	 */
	boolean deleteByModel(String namespace,String name);
	/**
	 * 根据Meta的ID获取Meta记录
	 * @param uniqueId
	 * @return
	 */
	MetaModel selectById(String uniqueId);
	/**
	 * 根据Meta的namespace和name获取Meta记录
	 * @param namespace
	 * @param name
	 * @return
	 */
	MetaModel selectByModel(String namespace, String name);
}
