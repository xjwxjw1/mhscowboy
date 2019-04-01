package com.mutong.mhscowboy.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mutong.mhscowboy.entity.Dept;
import com.mutong.mhscowboy.ex.DeleteException;
import com.mutong.mhscowboy.ex.DeptnoNotFindException;
import com.mutong.mhscowboy.ex.RootException;
import com.mutong.mhscowboy.mapper.DeptMapper;
import com.mutong.mhscowboy.service.IDeptService;

@Service
public class DeptService implements IDeptService {
	
	@Autowired
	private DeptMapper deptMapper;
	@Autowired
	private UserService userService;

	@Override
	public List<Dept> getAllDept() {
		return findAllDept();
	}
	
	
	@Override
	public void removeDept(Integer uid,Integer did) throws RootException, DeptnoNotFindException, DeleteException {
		Dept result = findByDid(did);
		if(result==null) {
			throw new DeptnoNotFindException("没有此条部门记录");
		}
		if(userService.findByUid(uid).getRoot()!=0) {
			throw new RootException("权限不足");
		}
		deleteByDeptno(did);
	}



	/**
	 * 查询所有部门信息
	 * @return 部门集合
	 */
	private List<Dept> findAllDept(){
		return deptMapper.findAllDept();
	}
	
	/**
	 * 根据部门id查询
	 * @param did 部门id
	 * @return 部门信息
	 */
	private Dept findByDid(Integer did) {
		return deptMapper.findByDid(did);
	}
	
	/**
	 * 根据部门id删除当前部门记录
	 * @param did 部门id
	 * @return 受影响的行数
	 */
	private void deleteByDeptno(Integer did) {
		Integer rows = deptMapper.deleteByDeptno(did);
		if(rows!=1) {
			throw new DeleteException("删除失败，出现未知错误");
		}
	}
}
