package com.mutong.mhscowboy.service.Impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mutong.mhscowboy.entity.Materiel;
import com.mutong.mhscowboy.ex.DeleteException;
import com.mutong.mhscowboy.ex.InsertException;
import com.mutong.mhscowboy.ex.MaterielNotFindException;
import com.mutong.mhscowboy.ex.MatnumExistException;
import com.mutong.mhscowboy.ex.RootException;
import com.mutong.mhscowboy.ex.UpdateException;
import com.mutong.mhscowboy.mapper.MaterielMapper;
import com.mutong.mhscowboy.service.IMaterielService;

@Service
public class MaterielService implements IMaterielService {
	
	@Autowired
	private MaterielMapper materielMapper;
	
	@Autowired
	private UserService userService;
	
	
	@Override
	public void add(Materiel materiel, Integer uid,String username) throws RootException, MatnumExistException,InsertException {
		Integer deptno = userService.findByUid(uid).getDeptno();
		if(deptno!=7 && deptno!=0) {
			throw new RootException("非PMC部无法添加物料信息");
		}
		Materiel result = findByMatnum(materiel.getMatnum());
		if(result!=null) {
			throw new MatnumExistException("物料编号已经存在");
		}
		Date now = new Date();
		materiel.setCreatedUser(username);
		materiel.setCreatedTime(now);
		materiel.setModifiedUser(username);
		materiel.setModifiedTime(now);
		insert(materiel);
	}

	@Override
	public List<Materiel> getAll() {
		return findAll();
	}


	@Override
	public Materiel getByMid(Integer mid){
		return findByMid(mid);
	}

	@Override
	public List<Materiel> getByParameter(String parameter) throws MaterielNotFindException {
		List<Materiel> result = findByParameter(parameter);
		if(result.size()<1) {
			throw new MaterielNotFindException("未找到匹配的物料信息");
		}
		return result;
	}

	@Override
	public void updateMateriel(Materiel materiel,Integer mid,Integer uid,String username)
			throws MaterielNotFindException, RootException, UpdateException {
		Integer deptno = userService.findByUid(uid).getDeptno();
		if(deptno!=7 && deptno!=0) {
			throw new RootException("非PMC部无法更改物料信息");
		}
		Materiel result = findByMid(mid);
		if(result==null) {
			throw new MaterielNotFindException("当前物料信息不存在");
		}
		Date now = new Date();
		materiel.setMid(mid);
		materiel.setModifiedUser(username);
		materiel.setModifiedTime(now);
		updateByMid(materiel);
	}

	@Override
	public void remove(Integer mid,Integer uid) throws RootException, MaterielNotFindException, DeleteException {
		Materiel result = findByMid(mid);
		Integer deptno = userService.findByUid(uid).getDeptno();
		if(deptno!=7 && deptno!=0) {
			throw new RootException("权限不足");
		}
		if(result==null) {
			throw new MaterielNotFindException("物料信息不存在");
		}
		delete(mid);
	}

	/**
	 * 添加物料信息
	 * @param materiel 物料信息
	 * @return 受影响的行数
	 */
	private void insert(Materiel materiel) {
		Integer rows = materielMapper.insert(materiel);
		if(rows!=1) {
			throw new InsertException("插入数据失败，出现未知错误");
		}
	}
	
	/**
	 * 根据物料编号查询
	 * @param matnum 物料编号
	 * @return 物料信息
	 */
	private Materiel findByMatnum(String matnum) {
		return materielMapper.findByMatnum(matnum);
	}
	
	/**
	 * 查询所有物料信息
	 * @return 物料信息集合
	 */
	private List<Materiel> findAll(){
		return materielMapper.findAll();
	}
	
	/**
	 * 根据mid查询物料信息
	 * @param mid 物料id
	 * @return 物料信息
	 */
	private Materiel findByMid(Integer mid) {
		return materielMapper.findByMid(mid);
	}
	
	/**
	 * 根据用户输入的关键字模糊查询
	 * @param parameter 关键字
	 * @return 相关的物料信息
	 */
	private List<Materiel> findByParameter(String parameter){
		return materielMapper.findByParameter(parameter);
	}
	
	/**
	 * 根据物料id更改物料信息
	 * @param mid 物料id
	 * @return 受影响的行数
	 */
	private void updateByMid(Materiel materiel) {
		Integer rows = materielMapper.updateByMid(materiel);
		if(rows!=1) {
			throw new UpdateException("更改失败，出现未知错误");
		}
	}
	
	/**
	 * 根据物料id删除此条记录
	 * @param mid 物料id
	 * @return 受影响的行数
	 */
	private void delete(Integer mid) {
		Integer rows = materielMapper.delete(mid);
		if(rows!=1) {
			throw new DeleteException("删除失败，出现未知错误");
		}
	}
}
