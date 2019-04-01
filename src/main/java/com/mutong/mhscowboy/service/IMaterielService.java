package com.mutong.mhscowboy.service;

import java.util.List;

import com.mutong.mhscowboy.entity.Materiel;
import com.mutong.mhscowboy.ex.DeleteException;
import com.mutong.mhscowboy.ex.InsertException;
import com.mutong.mhscowboy.ex.MaterielNotFindException;
import com.mutong.mhscowboy.ex.MatnumExistException;
import com.mutong.mhscowboy.ex.RootException;
import com.mutong.mhscowboy.ex.UpdateException;

/**
 * 处理物料数据的业务层接口
 */
public interface IMaterielService {
	/**
	 * 添加物料信息
	 * @param materiel 物料信息
	 * @param uid 当前登录用户的id
	 * @throws RootException 权限异常
	 * @throws InsertException 插入数据异常
	 */
	void add(Materiel materiel,Integer uid,String username) throws RootException,MatnumExistException,InsertException;
	
	/**
	 * 查询所有物料信息
	 * @return 物料信息集合
	 */
	List<Materiel> getAll();
	
	/**
	 * 根据物料id查找物料信息
	 * @param mid 物料id
	 * @return 物料信息
	 */
	Materiel getByMid(Integer mid);
	
	/**
	 * 根据关键词进行查询
	 * @param parameter 关键词
	 * @return 相关的用户信息
	 * @throws MaterielNotFindException 未找到匹配信息异常
	 */
	List<Materiel> getByParameter(String parameter) throws MaterielNotFindException;
	
	/**
	 * 根据mid修改物料信息
	 * @param materiel 物料信息
	 * @param mid 物料id
	 * @param uid 当前登录的用户id
	 * @param username 当前登录的用户名
	 * @throws MaterielNotFindException 当前物料信息不存在异常
	 * @throws RootException 权限不足异常
	 * @throws UpdateException 更新异常
	 */
	void updateMateriel(Materiel materiel,Integer mid,Integer uid,String username) 
			throws MaterielNotFindException,RootException,UpdateException;
	
	/**
	 * 根据物料id删除物料信息
	 * @param mid 物料id
	 * @param uid 当前登录用户的id
	 * @throws RootException 权限不足异常
	 * @throws MaterielNotFindException 物料信息不存在
	 * @throws DeleteException 删除失败异常
	 */
	void remove(Integer mid,Integer uid) throws RootException,MaterielNotFindException,DeleteException;
}
