package com.mutong.mhscowboy.mapper;

import java.util.List;

import com.mutong.mhscowboy.entity.Supplier;

/**
 * 处理供应商数据的持久层接口
 */
public interface SupplierMapper {
	/**
	 * 添加供应商信息
	 * @param supplier 供应商信息
	 * @return 受影响的行数
	 */
	Integer insert(Supplier supplier);
	
	/**
	 * 根据供应商名字查找相关信息
	 * @param name 供应商名字
	 * @return 相关信息
	 */
	Supplier findByName(String name);
	
	/**
	 * 根据供应商代码查找相关信息
	 * @param supnum 供应商代码
	 * @return 相关信息
	 */
	Supplier findBySupnum(String supnum);
	
	/**
	 * 查询所有供应商
	 * @return 供应商集合
	 */
	List<Supplier> findAll();
	
	/**
	 * 根据供应商id删除
	 * @param sid 供应商id
	 * @return 受影响的行数
	 */
	Integer deleteBySid(Integer sid);
	
	/**
	 * 根据sid查询供应商信息
	 * @param sid 供应商信息
	 * @return 相关信息
	 */
	Supplier findBySid(Integer sid);
	
	/**
	 * 根据用户输入的关键词 模糊查询
	 * @param parameter 用户输入的关键词
	 * @return 相关集合
	 */
	List<Supplier> findByParameter(String parameter);
	
	/**
	 * 根据sid修改供应商信息
	 * @param sid 供应商id
	 * @param supplier 供应商信息
	 * @return 受影响的行数
	 */
	Integer updateBySid(Supplier supplier);
}
