package com.mutong.mhscowboy.service;

import java.util.List;

import com.mutong.mhscowboy.entity.Supplier;
import com.mutong.mhscowboy.ex.DeleteException;
import com.mutong.mhscowboy.ex.InsertException;
import com.mutong.mhscowboy.ex.RootException;
import com.mutong.mhscowboy.ex.SupplierExistException;
import com.mutong.mhscowboy.ex.SupplierNotFindException;
import com.mutong.mhscowboy.ex.UpdateException;

/**
 * 处理供应商数据的业务层接口
 */
public interface ISupplierService {
	/**
	 * 添加供应商信息
	 * @param supplier 供应商信息
	 * @param uid 供当前登录用户的id
	 * @param username 供当前登录用户的用户名
	 * @throws RootException 权限不足异常
	 * @throws SupplierExistException 供应商已经存在
	 * @throws InsertException 插入异常
	 */
	void add(Supplier supplier,Integer uid,String username) throws RootException,SupplierExistException,InsertException;

	/**
	 * 查询所有供应商信息
	 * @return 供应商集合
	 */
	List<Supplier> getAll();
	
	/**
	 * 根据供应商id删除此条记录
	 * @param sid 供应商id
	 * @param uid 当前登录用户的uid
	 * @throws RootException 权限不足异常
	 * @throws DeleteException 删除异常
	 */
	void remove(Integer sid,Integer uid) 
			throws RootException,SupplierNotFindException,DeleteException;
	
	/**
	 * 根据用户输入的关键词查找相关信息
	 * @param parameter 用户输入的关键词
	 * @return 相关集合
	 * @throws SupplierNotFindException 未找到匹配信息异常
	 */
	List<Supplier> getByParameter(String parameter) throws SupplierNotFindException;
	
	/**
	 * 根据供应商id查询相关信息
	 * @param sid 供应商id
	 * @return 相关信息
	 */
	Supplier getBySid(Integer sid);
	
	/**
	 * 根据sid修改当前供应商信息
	 * @param supplier 供应商信息
	 * @param sid 供应商id
	 * @param uid 当前登录用户id
	 * @param username 当前登录用户用户名
	 * @throws RootException 权限不足异常
	 * @throws UpdateException 更新异常
	 * @throws SupplierNotFindException 供应商不存在异常
	 */
	void updateSupplier(Supplier supplier,Integer sid,Integer uid,String username) 
			throws RootException,UpdateException,SupplierNotFindException;
	
}
