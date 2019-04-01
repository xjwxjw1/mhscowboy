package com.mutong.mhscowboy.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mutong.mhscowboy.entity.User;
import com.mutong.mhscowboy.vo.UserVO;

/**
 * 处理用户数据的持久层接口
 */
public interface UserMapper {
	/**
	 * 插入用户数据
	 * @param user 用户数据
	 * @return 受影响的行数
	 */
	Integer insertUser(User user);
	
	/**
	 * 根据用户名查询用户数据
	 * @param username 用户名
	 * @return 匹配的用户数据，如果没有匹配的数据，则返回null
	 */
	User findByUsername(String username);
	
	/**
	 * 修改用户密码
	 * @param uid 用户id
	 * @param newPassword 新密码
	 * @param modifiedUser 修改人
	 * @param modifiedTime 修改时间
	 * @return 受影响的行数
	 */
	Integer updatePassword(
				@Param("uid")Integer uid,@Param("newPassword")String newPassword,
				@Param("modifiedUser")String modifiedUser,@Param("modifiedTime")Date modifiedTime);
	
	/**
	 * 根据用户id查找数据
	 * @param uid 用户id
	 * @return 用户信息
	 */
	User findByUid(Integer uid);
	
	/**
	 * 查询所有用户
	 * @return 用户集合
	 */
	List<UserVO> findUser();
	
	/**
	 * 根据用户id修改用户信息
	 * @param user 用户信息
	 * @return 受影响的行数
	 */
	Integer updateInfo(User user);
	
	/**
	 * 根据用户id删除此条记录
	 * @param uid 用户id
	 * @return 受影响的行数
	 */
	Integer delete(Integer uid);
	
	/**
	 * 模糊查询 根据用户输入的信息查询匹配的姓名用户
	 * @param parameter 用户输入的字母
	 * @return 查找到的对象集合
	 */
	List<UserVO> findByLetter(String parameter);
	
	/**
	 * 查询部门人数
	 * @param deptno 部门id
	 * @return 人数
	 */
	Integer findByDeptno(Integer deptno);
}
