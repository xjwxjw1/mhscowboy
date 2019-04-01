package com.mutong.mhscowboy.service;

import java.util.List;

import com.mutong.mhscowboy.entity.Project;
import com.mutong.mhscowboy.ex.DeleteException;
import com.mutong.mhscowboy.ex.DeptNoException;
import com.mutong.mhscowboy.ex.InsertException;
import com.mutong.mhscowboy.ex.ProjectNotFindException;
import com.mutong.mhscowboy.ex.ProjectNumExistException;
import com.mutong.mhscowboy.ex.RootException;
import com.mutong.mhscowboy.ex.UpdateException;
import com.mutong.mhscowboy.ex.UserMismatchException;

/**
 * 处理项目数据的业务层接口
 */
public interface IProjectService {
	/**
	 * 添加项目
	 * @param project 项目信息
	 * @param uid 当前用户id
	 * @param username 当前用户用户名
	 * @throws DeptNoException 部门不符合异常
	 * @throws ProjectNumExistException 项目名已经存在异常
	 * @throws InsertException 插入异常
	 */
	void addNew(Project project,Integer uid,String username) throws DeptNoException,ProjectNumExistException,InsertException;

	/**
	 * 获取全部项目信息
	 * @return 项目集合
	 */
	List<Project> getAllProject();
	
	/**
	 * 模糊查询 根据用户输入的参数查询
	 * @param parameter 用户输入的参数
	 * @return 项目信息
	 * @throws ProjectNotFindException 项目未找到异常
	 */
	List<Project> getByParameter(String parameter) throws ProjectNotFindException;
	
	/**
	 * 修改用户信息
	 * @param project 
	 * @throws UserMismatchException 用户名不匹配异常
	 * @throws UpdateException 更新异常
	 */
	void updateProject(Project project,Integer pid,String username) 
			throws UserMismatchException,ProjectNotFindException,UpdateException;
	
	/**
	 * 根据pid删除当前项目
	 * @param pid 项目id
	 * @param uid 当前登录的用户id
	 * @throws RootException 权限不足异常
	 * @throws ProjectNotFindException 项目未找到异常
	 * @throws DeleteException 删除异常
	 */
	void removeProject(Integer pid,Integer uid)throws RootException,ProjectNotFindException,DeleteException;

	/**
	 * 根据pid查找相关信息
	 * @param pid 项目id
	 * @return 项目信息
	 */
	Project getByPid(Integer pid);
}
