package com.mutong.mhscowboy.mapper;

import java.util.List;

import com.mutong.mhscowboy.entity.Dept;

/**
 * 处理部门数据的持久层接口
 */
public interface DeptMapper {
	/**
	 * 查询所有部门信息
	 * @return 部门集合
	 */
	List<Dept> findAllDept();
	
	/**
	 * 根据部门id查询
	 * @param did 部门id
	 * @return 部门信息
	 */
	Dept findByDid(Integer did);
	
	/**
	 * 根据部门id删除当前部门记录
	 * @param did 部门id
	 * @return 受影响的行数
	 */
	Integer deleteByDeptno(Integer did);
}
