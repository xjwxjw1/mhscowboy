package com.mutong.mhscowboy.service;

import java.util.List;

import com.mutong.mhscowboy.entity.Dept;
import com.mutong.mhscowboy.ex.DeleteException;
import com.mutong.mhscowboy.ex.DeptnoNotFindException;
import com.mutong.mhscowboy.ex.RootException;
import com.mutong.mhscowboy.ex.UpdateException;

/**
 * 处理部门数据的业务层接口
 */
public interface IDeptService {
	/**
	 * 查询所有部门信息
	 * @return 部门信息
	 */
	List<Dept> getAllDept();
	
	/**
	 * 根据部门id删除此条记录
	 * @param uid 当前登录用户的id
	 * @param did 部门id
	 * @throws RootException 权限不足异常
	 * @throws DeptnoNotFindException 此条部门不存在异常
	 * @throws DeleteException 删除异常
	 */
	void removeDept(Integer uid,Integer did)throws RootException,DeptnoNotFindException,DeleteException;
}
