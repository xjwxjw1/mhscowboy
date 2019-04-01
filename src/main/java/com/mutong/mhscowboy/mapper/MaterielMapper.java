package com.mutong.mhscowboy.mapper;

import java.util.List;

import com.mutong.mhscowboy.entity.Materiel;

/**
 * 处理物料数据的持久层接口
 */
public interface MaterielMapper {
	/**
	 * 添加物料信息
	 * @param materiel 物料信息
	 * @return 受影响的行数
	 */
	Integer insert(Materiel materiel);
	
	/**
	 * 根据物料编号查询
	 * @param matnum 物料编号
	 * @return 物料信息
	 */
	Materiel findByMatnum(String matnum);
	
	/**
	 * 查询所有物料信息
	 * @return 物料信息集合
	 */
	List<Materiel> findAll();
	
	/**
	 * 根据mid查询物料信息
	 * @param mid 物料id
	 * @return 物料信息
	 */
	Materiel findByMid(Integer mid);
	
	/**
	 * 根据用户输入的关键字模糊查询
	 * @param parameter 关键字
	 * @return 相关的物料信息
	 */
	List<Materiel> findByParameter(String parameter);
	
	/**
	 * 根据物料id更改物料信息
	 * @param mid 物料id
	 * @return 受影响的行数
	 */
	Integer updateByMid(Materiel materiel);
	
	/**
	 * 根据物料id删除此条记录
	 * @param mid 物料id
	 * @return 受影响的行数
	 */
	Integer delete(Integer mid);
}
