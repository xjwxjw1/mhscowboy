package com.mutong.mhscowboy.service.Impl;



import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.mutong.mhscowboy.entity.User;
import com.mutong.mhscowboy.ex.DeleteException;
import com.mutong.mhscowboy.ex.InsertException;
import com.mutong.mhscowboy.ex.PasswordNotMatchException;
import com.mutong.mhscowboy.ex.RootException;
import com.mutong.mhscowboy.ex.UpdateException;
import com.mutong.mhscowboy.ex.UserMismatchException;
import com.mutong.mhscowboy.ex.UserNotFoundException;
import com.mutong.mhscowboy.ex.UsernameDuplicateException;
import com.mutong.mhscowboy.mapper.UserMapper;
import com.mutong.mhscowboy.service.IUserService;
import com.mutong.mhscowboy.vo.UserVO;

@Service
public class UserService implements IUserService {
	
	@Autowired
	private UserMapper userMapper;
	

	@Override
	public void reg(User user) throws UsernameDuplicateException, InsertException {
		String username = user.getUsername();
		User result = findByUsername(username);
		if(result!=null) {
			throw new UsernameDuplicateException("您注册的用户名 "+user.getUsername()+" 已经被占用");
		}
		String salt = UUID.randomUUID().toString();
		String password = user.getPassword();
		String name = user.getName();
		String md5Password = md5Password(password, salt);
		user.setPassword(md5Password);
		user.setSalt(salt);
		user.setRoot(1);
		Date now = new Date();
		user.setCreatedUser(username);
		user.setCreatedTime(now);
		user.setModifiedUser(username);
		user.setModifiedTime(now);
		insertUser(user);
	}

	@Override
	public User login(String username, String password) throws UserNotFoundException, PasswordNotMatchException {
		User result = findByUsername(username);
		if(result==null) {
			throw new UserNotFoundException("用户名不存在");
		}
		String salt = result.getSalt();
		String psd = md5Password(password, salt);
		if(!psd.equals(result.getPassword())) {
			throw new PasswordNotMatchException("密码错误");
		}
		result.setPassword(null);
		result.setSalt(null);
		return result;
	}

	@Override
	public void changePassword(Integer uid, String oldPassword, String newPassword, String username)
			throws UserNotFoundException,PasswordNotMatchException, UpdateException {
		User result = findByUid(uid);
		if(result==null) {
			throw new UserNotFoundException("尝试修改的用户不存在");
		}
		String salt = result.getSalt();
		String md5Password = md5Password(oldPassword, salt);
		if(!md5Password.equals(result.getPassword())) {
			throw new PasswordNotMatchException("修改失败，原密码错误");
		}	
		String modifiedUser = username;
		Date modifiedTime = new Date();
		newPassword = md5Password(newPassword, salt);
		updatePassword(uid, newPassword, modifiedUser, modifiedTime);
	}

	@Override
	public List<UserVO> getUser() {
		return findUser();
	}
	
	@Override
	public void changeInfo(User user,Integer loginUid,Integer uid,String username) throws UserNotFoundException,UpdateException {
		if(loginUid!=uid && loginUid!=0) {
			throw new UserMismatchException("无法修改他人的信息");
		}
		if(findByUid(uid)==null) {
			throw new UserNotFoundException("用户信息未找到");
		}
		user.setUid(uid);
		user.setModifiedUser(username);
		user.setModifiedTime(new Date());
		updateInfo(user);
	}

	@Override
	public void deleteUser(Integer uid,Integer rootUid) throws UserNotFoundException,DeleteException {
		User user = findByUid(uid);
		User result = findByUid(rootUid);
		if(result.getRoot()!=0) {
			throw new RootException("权限不足");
		}
		if(user==null) {
			throw new UserNotFoundException("该条用户信息不存在");
		}
		delete(uid);
	}

	
	@Override
	public List<UserVO> getByParameter(String parameter) throws UserNotFoundException {
		List<UserVO> result = findByLetter(parameter);
		if(result.size()==0) {
			throw new UserNotFoundException("未查询到匹配的用户信息");
		}
		return result;
	}
	
	@Override
	public Integer getByDeptno(Integer deptno) {
		return findByDeptno(deptno);
	}

	@Override
	public User getByUid(Integer uid) throws UserNotFoundException {
		User result = findByUid(uid);
		if(result==null) {
			throw new UserNotFoundException("该用户信息不存在");
		}
		return result;
	}

	/**
	 * 插入用户数据
	 * @param user 用户数据
	 * @return 受影响的行数
	 */
	private void insertUser(User user) {
		Integer rows = userMapper.insertUser(user);
		if(rows!=1) {
			throw new InsertException("创建账号出现未知错误，请稍后再试");
		}
	}
	
	/**
	 * 根据用户名查询用户数据
	 * @param username 用户名
	 * @return 匹配的用户数据，如果没有匹配的数据，则返回null
	 */
	private User findByUsername(String username) {
		return userMapper.findByUsername(username);
	}
	
	/**
	 * MD5密码加密
	 * @param password 用户原始密码
	 * @param salt 盐值
	 * @return 加密后的密码
	 */
	private String md5Password(String password,String salt) {
		String md5Password = salt+password+salt;
		for(int i=0;i<10;i++) {
			md5Password = DigestUtils.md5DigestAsHex(md5Password.getBytes());
		}
		return md5Password;
	}
	
	/**
	 * 修改用户密码
	 * @param uid 用户id
	 * @param newPassword 新密码
	 * @param modifiedUser 修改人
	 * @param modifiedTime 修改时间
	 * @return
	 */
	private void updatePassword(Integer uid,String newPassword,String modifiedUser,Date modifiedTime) {
		Integer rows = userMapper.updatePassword(uid, newPassword, modifiedUser, modifiedTime);
		if(rows!=1) {
			throw new UpdateException("更新密码失败，出现位置错误");
		}
	}
	
	/**
	 * 根据用户id查找数据
	 * @param uid 用户id
	 * @return 用户信息
	 */
	public User findByUid(Integer uid) {
		 return userMapper.findByUid(uid);	
	}
	
	/**
	 * 查询所有用户
	 * @return 用户集合
	 */
	private List<UserVO> findUser(){
		return userMapper.findUser();
	}
	
	/**
	 * 根据用户id修改用户信息
	 * @param user 用户信息
	 * @return 受影响的行数
	 */
	private void updateInfo(User user) {
		Integer rows = userMapper.updateInfo(user);
		if(rows!=1) {
			throw new UpdateException("信息更新失败，出现未知错误");
		}
	}
	
	/**
	 * 根据用户id删除此条记录
	 * @param uid 用户id
	 * @return 受影响的行数
	 */
	private void delete(Integer uid) {
		Integer rows = userMapper.delete(uid);
		if(rows!=1) {
			throw new DeleteException("删除失败，出现未知错误");
		}
	}
	
	/**
	 * 模糊查询 根据用户输入的信息查询匹配的姓名用户
	 * @param parameter 用户输入的字母
	 * @return 查找到的对象集合
	 */
	private List<UserVO> findByLetter(String parameter){
		return userMapper.findByLetter(parameter);
	}

	/**
	 * 查询部门人数
	 * @param deptno 部门id
	 * @return 人数
	 */
	private Integer findByDeptno(Integer deptno) {
		return userMapper.findByDeptno(deptno);
	}
}

