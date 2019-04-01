package com.mutong.mhscowboy.service.Impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mutong.mhscowboy.entity.Supplier;
import com.mutong.mhscowboy.ex.DeleteException;
import com.mutong.mhscowboy.ex.InsertException;
import com.mutong.mhscowboy.ex.RootException;
import com.mutong.mhscowboy.ex.SupplierExistException;
import com.mutong.mhscowboy.ex.SupplierNotFindException;
import com.mutong.mhscowboy.ex.UpdateException;
import com.mutong.mhscowboy.mapper.SupplierMapper;
import com.mutong.mhscowboy.service.ISupplierService;
import com.mutong.mhscowboy.service.IUserService;

@Service
public class SupplierService implements ISupplierService {
	
	@Autowired
	private SupplierMapper supplierMapper;
	@Autowired
	private IUserService userService;
	
	@Override
	public void add(Supplier supplier,Integer uid,String username) throws RootException, SupplierExistException, InsertException {
		String name = supplier.getName();
		String supnum = supplier.getSupnum();
		Integer deptno = userService.getByUid(uid).getDeptno();
		if(deptno!=8 && deptno!=0) {
			throw new RootException("非采购部无法添加供应商信息");
		}
		if(findBySupnum(supnum)!=null) {
			throw new SupplierExistException("当前供应商代码已经存在");
		}
		if(findByName(name)!=null) {
			throw new SupplierExistException("当前供应商名字已经存在");
		}
		Date now = new Date();
		supplier.setCreatedUser(name);
		supplier.setCreatedTime(now);
		supplier.setModifiedUser(username);
		supplier.setModifiedTime(now);
		insert(supplier);
	}

	@Override
	public List<Supplier> getAll() {
		return findAll();
	}

	@Override
	public void remove(Integer sid,Integer uid) 
			throws RootException,SupplierNotFindException,DeleteException{
		Integer deptno = userService.getByUid(uid).getDeptno();
		if(deptno!=8 && deptno!=0) {
			throw new RootException("非采购部无法删除供应商信息");
		}
		if(findBySid(sid)==null) {
			throw new SupplierNotFindException("没有此条记录信息");
		}
		deleteBySid(sid);
	}

	@Override
	public List<Supplier> getByParameter(String parameter) throws SupplierNotFindException {
		List<Supplier> result = findByParameter(parameter);
		if(result.size()<1) {
			throw new SupplierNotFindException("未查询到匹配的信息");
		}
		return result;
	}

	@Override
	public Supplier getBySid(Integer sid) {
		return findBySid(sid);
	}

	@Override
	public void updateSupplier(Supplier supplier, Integer sid, Integer uid, String username)
			throws RootException, UpdateException, SupplierNotFindException {
		Integer deptno = userService.getByUid(uid).getDeptno();
		if(deptno!=8 && deptno!=0) {
			throw new RootException("非采购部人员无法修改");
		}
		Supplier result = findBySid(sid);
		if(result==null) {
			throw new SupplierNotFindException("当前供应商信息不存在");
		}
		supplier.setSid(sid);
		supplier.setModifiedUser(username);
		supplier.setModifiedTime(new Date());
		updateBySid(supplier);
	}

	/**
	 * 添加供应商信息
	 * @param supplier 供应商信息
	 * @return 受影响的行数
	 */
	private void insert(Supplier supplier) {
		Integer rows = supplierMapper.insert(supplier);
		if(rows!=1) {
			throw new InsertException("添加失败，出现未知错误");
		}
	}
	
	/**
	 * 根据供应商名字查找相关信息
	 * @param name 供应商名字
	 * @return 相关信息
	 */
	private Supplier findByName(String name) {
		return supplierMapper.findByName(name);
	}
	
	/**
	 * 根据供应商代码查找相关信息
	 * @param supnum 供应商代码
	 * @return 相关信息
	 */
	private Supplier findBySupnum(String supnum) {
		return supplierMapper.findBySupnum(supnum);
	}
	
	/**
	 * 查询所有供应商
	 * @return 供应商集合
	 */
	private List<Supplier> findAll(){
		return supplierMapper.findAll();
	}
	
	/**
	 * 根据供应商id删除
	 * @param sid 供应商id
	 * @return 受影响的行数
	 */
	private void deleteBySid(Integer sid) {
		Integer rows = supplierMapper.deleteBySid(sid);
		if(rows!=1) {
			throw new DeleteException("删除失败，出现未知错误");
		}
	}
	
	/**
	 * 根据sid查询供应商信息
	 * @param sid 供应商信息
	 * @return 相关信息
	 */
	private Supplier findBySid(Integer sid) {
		return supplierMapper.findBySid(sid);
	}
	
	/**
	 * 根据用户输入的关键词 模糊查询
	 * @param parameter 用户输入的关键词
	 * @return 相关集合
	 */
	private List<Supplier> findByParameter(String parameter){
		return supplierMapper.findByParameter(parameter);
	}
	
	/**
	 * 根据sid修改供应商信息
	 * @param sid 供应商id
	 * @param supplier 供应商信息
	 * @return 受影响的行数
	 */
	private void updateBySid(Supplier supplier) {
		Integer rows = supplierMapper.updateBySid(supplier);
		if(rows!=1) {
			throw new UpdateException("更新失败，出现未知错误");
		}
	}
}
