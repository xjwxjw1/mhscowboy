package com.mutong.mhscowboy.service;

import java.util.List;

import com.mutong.mhscowboy.entity.Need;
import com.mutong.mhscowboy.ex.DeleteException;
import com.mutong.mhscowboy.ex.InsertException;
import com.mutong.mhscowboy.ex.NeedExistException;
import com.mutong.mhscowboy.ex.NeedNotFindException;
import com.mutong.mhscowboy.ex.ProjectNotFindException;
import com.mutong.mhscowboy.ex.RootException;
import com.mutong.mhscowboy.ex.UpdateException;

/**
 * 处理物料需求计划的业务层接口
 */
public interface INeedService {
	/**
	 * 添加物料需求计划信息
	 * @param need 物料需求计划信息
	 * @throws InsertException 权限异常
	 * @throws NeedExistException 物料需求计划信息已经存在异常
	 * @throws InsertException 添加异常
	 */
	void add(Need need,Integer pid,Integer uid,String username) 
			throws RootException,ProjectNotFindException,NeedExistException,InsertException;
	
	/**
	 * 根据项目id 查询项目所需要的物料信息
	 * @param pid 项目id
	 * @return 相关的物料信息
	 */
	List<Need> getAllByPid(Integer pid);
	
	/**
	 * 根据用户输入的关键词查找相关物料信息
	 * @param parameter 用户输入的关键词
	 * @param pid 当前项目id
	 * @return 相关的物料信息
	 * @throws NeedNotFindException 未找到匹配的物料需求信息
	 */
	List<Need> getByParameter(String parameter,Integer pid) throws NeedNotFindException;
	
	/**
	 * 根据nid查找相信物料需求信息
	 * @param nid 物料需求id
	 * @return 相关信息
	 * @throws NeedNotFindException 没有该物料需求异常
	 */
	Need getByNid(Integer nid)throws NeedNotFindException;
	
	/**
	 * 根据物料需求id修改对应的信息
	 * @param need 修改后的物料需求信息
	 * @param nid 物料需求id
	 * @param uid 当前登录用户的id
	 * @param username 当前登录用户的用户名
	 * @throws NeedNotFindException 没有该条物料信息异常
	 * @throws RootException 权限不足异常
	 * @throws UpdateException 更新异常
	 */
	void updateNeed(Need need,Integer nid,Integer uid,String username)
			throws NeedNotFindException,RootException,UpdateException;
	
	/**
	 * 根据物料需求id删除对应的记录
	 * @param nid 物料需求id
	 * @param uid 当前登录用户的id
	 * @param username 当前登录用户的用户名
	 * @throws DeleteException 删除异常
	 * @throws RootException 权限不足异常
	 * @throws NeedNotFindException 物料需求不存在异常
	 */
	void remove(Integer nid,Integer uid,String username) throws DeleteException,RootException,NeedNotFindException;
	
	/**
	 * 根据物料需求id更改审核状态
	 * @param nid 物料需求id
	 */
	void updateDemand(Integer nid,Integer uid) throws RootException,NeedNotFindException,UpdateException;
	
}
