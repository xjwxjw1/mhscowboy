package com.mutong.mhscowboy.service.Impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mutong.mhscowboy.entity.Need;
import com.mutong.mhscowboy.entity.Project;
import com.mutong.mhscowboy.ex.DeleteException;
import com.mutong.mhscowboy.ex.InsertException;
import com.mutong.mhscowboy.ex.NeedExistException;
import com.mutong.mhscowboy.ex.NeedNotFindException;
import com.mutong.mhscowboy.ex.ProjectNotFindException;
import com.mutong.mhscowboy.ex.RootException;
import com.mutong.mhscowboy.ex.UpdateException;
import com.mutong.mhscowboy.mapper.NeedMapper;
import com.mutong.mhscowboy.service.INeedService;
import com.mutong.mhscowboy.service.IUserService;

@Service
public class NeedService implements INeedService {
	
	@Autowired
	private NeedMapper needMapper;
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private IUserService userService;
	
	@Override
	public void add(Need need,Integer pid,Integer uid,String username) 
			throws RootException,ProjectNotFindException,NeedExistException, InsertException {
		Project project = projectService.findByPid(pid);
		if(project==null) {
			throw new ProjectNotFindException("当前项目不存在");
		}
		String user = project.getCreatedUser();
		if(!username.equals(user) && !"admin".equals(username)) {
			throw new RootException("无法添加他人项目的物料信息");
		}
		String matnum = need.getMatnum();
		Need result = findByMatnumAndProId(matnum, pid);
		if(result!=null) {
			throw new NeedExistException("此项目已经存在该物料编码");
		}
		Date now = new Date();
		need.setAuditing("未审核");
		need.setProId(pid);
		need.setCreatedUser(username);
		need.setCreatedTime(now);
		need.setModifiedUser(username);
		need.setModifiedTime(now);
		insert(need);
	}

	@Override
	public List<Need> getAllByPid(Integer pid) {
		return findAllByPid(pid);
	}

	@Override
	public List<Need> getByParameter(String parameter,Integer pid) throws NeedNotFindException {
		List<Need> result = findByParameter(parameter,pid);
		if(result.size()<1) {
			throw new NeedNotFindException("未匹配到相关的信息");
		}
		return result;
	}

	@Override
	public Need getByNid(Integer nid) throws NeedNotFindException {
		Need result = findByNid(nid);
		if(result==null) {
			throw new NeedNotFindException("没有此条物料需求信息");
		}
		return result;
	}

	@Override
	public void updateNeed(Need need, Integer nid, Integer uid, String username)
			throws NeedNotFindException, RootException, UpdateException {
		Integer deptno = userService.getByUid(uid).getDeptno();
		if(deptno!=5 && deptno!=0) {
			throw new RootException("非业务部无法修改物料需求");
		}
		if(!username.equals(findByNid(nid).getCreatedUser()) && !"admin".equals(username)) {
			throw new RootException("无法修改他人项目的物料需求");
		}
		if(findByNid(nid)==null) {
			throw new NeedNotFindException("该物料需求不存在");
		}
		need.setNid(nid);
		need.setModifiedUser(username);
		need.setModifiedTime(new Date());
		updateByNid(need);
	}

	@Override
	public void remove(Integer nid,Integer uid,String username) 
			throws DeleteException, RootException, NeedNotFindException {
		if(findByNid(nid)==null) {
			throw new NeedNotFindException("该物料需求不存在");
		}
		Integer deptno = userService.getByUid(uid).getDeptno();
		if(deptno!=5 && deptno!=0) {
			throw new RootException("非业务部无法删除物料需求");
		}
		Integer root = userService.getByUid(uid).getRoot();
		if(!username.equals(findByNid(nid).getCreatedUser()) && root!=0) {
			throw new RootException("无法删除他人的物料需求");
		}
		deleteByNid(nid);
	}

	@Override
	public void updateDemand(Integer nid, Integer uid) throws RootException, NeedNotFindException, UpdateException {
		if(findByNid(nid)==null) {
			throw new NeedNotFindException("该物料需求不存在");
		}
		Integer deptno = userService.getByUid(uid).getDeptno();
		if(deptno!=7 && deptno!=0) {
			throw new RootException("非PMC部无法修改审核状态");
		}
		updateDemand(nid);
	}

	/**
	 * 添加物料需求计划信息
	 * @param need 物料需求计划
	 * @return 受影响的行数
	 */
	private void insert(Need need) {
		Integer rows = needMapper.insert(need);
		if(rows!=1) {
			throw new InsertException("添加失败，出现未知错误");
		}
	}
	
	/**
	 * 根据所属项目id和物料编号查询是否重复
	 * @param matnum 物料编号
	 * @param proId 项目id
	 * @return 查询到的相关信息
	 */
	private Need findByMatnumAndProId(String matnum,Integer proId) {
		return needMapper.findByMatnumAndProId(matnum, proId);
	}
	
	/**
	 * 根据项目id查询相关的物料需求集合
	 * @param pid 项目id
	 * @return 相关物料需求集合
	 */
	private List<Need> findAllByPid(Integer pid){
		return needMapper.findAllByPid(pid);
	}
	
	/**
	 * 根据用户输入的关键词查询相关物料需求信息
	 * @return 物料需求信息
	 */
	private List<Need> findByParameter(String parameter,Integer pid) {
		return needMapper.findByParameter(parameter,pid);
	}
	
	/**
	 * 根据物料需求id查找相关的信息
	 * @param nid 物料需求id
	 * @return 相关信息
	 */
	private Need findByNid(Integer nid) {
		return needMapper.findByNid(nid);
	}
	
	/**
	 * 根据物料需求id更改物料需求信息
	 * @param need 物料需求信息
	 */
	private void updateByNid(Need need) {
		Integer rows = needMapper.updateByNid(need);
		if(rows!=1) {
			throw new UpdateException("更新失败，出现未知错误");
		}
	}
	
	/**
	 * 根据物料需求id删除对应的记录
	 * @param nid 物料需求id
	 * @return 受影响的行数
	 */
	private void deleteByNid(Integer nid) {
		Integer rows = needMapper.deleteByNid(nid);
		if(rows!=1) {
			throw new UpdateException("删除失败，出现未知错误");
		}
	}
	
	/**
	 * 根据物料需求id更改审核状态
	 * @param nid
	 * @return 受影响的行数
	 */
	private void updateDemand(Integer nid) {
		Integer rows = needMapper.updateDemand(nid);
		if(rows!=1) {
			throw new UpdateException("更改失败，出现未知错误");
		}
	}
}
