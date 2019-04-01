package com.mutong.mhscowboy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mutong.mhscowboy.entity.Need;
import com.mutong.mhscowboy.ex.NeedNotFindException;

/**
 * 处理物料需求计划的持久层接口
 */
public interface NeedMapper {
	/**
	 * 添加物料需求计划信息
	 * @param need 物料需求计划
	 * @return 受影响的行数
	 */
	Integer insert(Need need);
	
	/**
	 * 根据所属项目id和物料编号查询是否重复
	 * @param matnum 物料编号
	 * @param proId 项目id
	 * @return 查询到的相关信息
	 */
	Need findByMatnumAndProId(@Param("matnum")String matnum,@Param("proId")Integer proId);
	
	/**
	 * 根据项目id查询相关的物料需求集合
	 * @param pid 项目id
	 * @return 相关物料需求集合
	 */
	List<Need> findAllByPid(Integer pid);
	
	/**
	 * 根据用户输入的关键词查询相关物料需求信息
	 * @param parameter 用户输入的关键词
	 * @param proId 当前的项目id
	 * @return 相关的物料需求信息
	 */
	List<Need> findByParameter(@Param("parameter")String parameter,@Param("proId")Integer proId);

	/**
	 * 根据物料需求id查找相关的信息
	 * @param nid 物料需求id
	 * @return 相关信息
	 */
	Need findByNid(Integer nid);
	
	/**
	 * 根据物料需求id更改物料需求信息
	 * @param need 物料需求信息
	 */
	Integer updateByNid(Need need);
	
	/**
	 * 根据物料需求id删除对应的记录
	 * @param nid 物料需求id
	 * @return 受影响的行数
	 */
	Integer deleteByNid(Integer nid);
	
	/**
	 * 根据物料需求id更改审核状态
	 * @param nid
	 * @return 受影响的行数
	 */
	Integer updateDemand(Integer nid);
}
