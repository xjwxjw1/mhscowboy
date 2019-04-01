package com.mutong.mhscowboy.service;

import java.util.List;

import com.mutong.mhscowboy.entity.User;
import com.mutong.mhscowboy.ex.DeleteException;
import com.mutong.mhscowboy.ex.InsertException;
import com.mutong.mhscowboy.ex.PasswordNotMatchException;
import com.mutong.mhscowboy.ex.RootException;
import com.mutong.mhscowboy.ex.UpdateException;
import com.mutong.mhscowboy.ex.UserMismatchException;
import com.mutong.mhscowboy.ex.UserNotFoundException;
import com.mutong.mhscowboy.ex.UsernameDuplicateException;
import com.mutong.mhscowboy.vo.UserVO;


/**
 * 处理用户数据的业务层接口
 */
public interface IUserService {
	/**
	 * 创建用户
	 * @param user 用户数据
	 * @throws UsernameDuplicateException 用户名已经存在异常
	 * @throws InsertException 创建账号发生的未知异常
	 */
	void reg(User user) throws UsernameDuplicateException,InsertException;
	
	/**
	 * 用户登录
	 * @param username 用户名
	 * @param password 密码
	 * @return 成功登录的用户信息
	 * @throws UserNotFoundException 用户名不存在异常
	 * @throws PasswordNotMatchException 密码错误异常
	 */
	User login(String username,String password) 
			throws UserNotFoundException,PasswordNotMatchException;

	/**
	 * 修改用户密码
	 * @param uid 用户id
	 * @param oldPassword 用户原密码
	 * @param newPassword 用户新密码
	 * @param username 用户名
	 * @throws PasswordNotMatchException 密码错误异常
	 * @throws UpdateException 更新异常
	 */
	void changePassword(Integer uid,String oldPassword,String newPassword,String username) 
			throws UserNotFoundException,PasswordNotMatchException,UpdateException;
	
	/**
	 * 查询所有用户信息
	 * @return 用户集合
	 */
	List<UserVO> getUser();
	
	/**
	 * 根据用户id修改 用户资料
	 * @param user 用户信息
	 * @param uid 地址栏中传回的uid
	 * @param loginUid 当前登录用户的uid
	 * @param username 当前登录用户的用户名
	 * @throws UpdateException 更新失败异常
	 * @throws UserNotFoundException 用户未找到异常
	 * @throws UserMismatchException 用户不匹配异常
	 */
	void changeInfo(User user,Integer loginUid,Integer uid,String username) 
			throws UserNotFoundException,UserMismatchException,UpdateException;
	
	/**
	 * 根据uid删除用户信息
	 * @param uid 用户id
	 * @throws DeleteException 删除失败异常
	 */
	void deleteUser(Integer uid,Integer rootUid) throws UserNotFoundException,RootException,DeleteException;

	/**
	 * 模糊查询 根据用户输入的内容查找对应的用户信息
	 * @param parameter 用户输入的内容
	 * @return 查询到的用户信息
	 * @throws UserNotFoundException 未查询到用户异常
	 */
	List<UserVO> getByParameter(String parameter) throws UserNotFoundException;
	
	/**
	 * 查询部门人数
	 * @param deptno 部门id
	 * @return 人数
	 */
	Integer getByDeptno(Integer deptno);
	
	/**
	 * 根据用户id查找相关信息
	 * @param uid 用户id
	 * @return 用户信息
	 */
	User getByUid(Integer uid)throws UserNotFoundException;
}
