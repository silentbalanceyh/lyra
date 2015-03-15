package com.lyra.db.mybatis;

import java.util.List;

import com.lyra.mod.sys.KeyModel;

/**
 * 
 *
 * @author Lang
 * @see
 */
public interface KeyMapper {
	/**
	 * 添加记录
	 * @param key
	 * @return 插入数据影响行数
	 */
	int insertKey(KeyModel key);
	/**
	 * 更新记录
	 * @param key
	 * @return 插入数据影响行数
	 */
	int updateKey(KeyModel key);
	/**
	 * 根据Meta的ID删除Key记录
	 * @param metaId
	 * @return
	 */
	boolean deleteByMeta(String metaId);
	/**
	 * 根据Key的ID删除Key记录
	 * @param uniqueId
	 * @return
	 */
	boolean deleteById(String uniqueId);
	/**
	 * 根据Key的ID获取Key记录
	 * @param uniqueId
	 * @return
	 */
	KeyModel selectById(String uniqueId);
	/**
	 * 根据Meta的ID获取Key记录的集合
	 * @param metaId
	 * @return
	 */
	List<KeyModel> selectByMeta(String metaId);
}
