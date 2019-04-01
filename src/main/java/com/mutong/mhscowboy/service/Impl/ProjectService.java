package com.mutong.mhscowboy.service.Impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mutong.mhscowboy.entity.Project;
import com.mutong.mhscowboy.ex.DeleteException;
import com.mutong.mhscowboy.ex.DeptNoException;
import com.mutong.mhscowboy.ex.InsertException;
import com.mutong.mhscowboy.ex.ProjectNotFindException;
import com.mutong.mhscowboy.ex.ProjectNumExistException;
import com.mutong.mhscowboy.ex.RootException;
import com.mutong.mhscowboy.ex.UpdateException;
import com.mutong.mhscowboy.ex.UserMismatchException;
import com.mutong.mhscowboy.mapper.ProjectMapper;
import com.mutong.mhscowboy.mapper.UserMapper;
import com.mutong.mhscowboy.service.IProjectService;
import com.mutong.mhscowboy.service.IUserService;

@Service
public class ProjectService implements IProjectService {
	
	@Autowired
	private ProjectMapper projectMapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private UserService userService;

	@Override
	public void addNew(Project project,Integer uid,String username) throws DeptNoException,ProjectNumExistException,InsertException{
		Project result = findByPronum(project.getPronum());
		Integer deptno = userMapper.findByUid(uid).getDeptno();
		if(deptno!=5 && deptno!=0) {
			throw new DeptNoException("非业务部无法添加项目");
		}
		if(result!=null) {
			throw new ProjectNumExistException("项目编号已经存在");
		}
		Date now = new Date();
		project.setCreatedUser(username);
		project.setCreatedTime(now);
		project.setModifiedUser(username);
		project.setModifiedTime(now);
		insert(project);
	}
	
	@Override
	public List<Project> getAllProject() {
		return findAllProject();
	}

	@Override
	public List<Project> getByParameter(String parameter) throws ProjectNotFindException {
		List<Project> result = findByParameter(parameter);
		if(result.size()<1) {
			throw new ProjectNotFindException("未查到匹配的项目信息");
		}
		return result;
	}
	
	@Override
	public void updateProject(Project project,Integer pid,String username) 
			throws UserMismatchException, ProjectNotFindException,UpdateException {
		Project result = findByPid(pid);
		if(result==null) {
			throw new ProjectNotFindException("更改失败，项目未找到");
		}
		String createUser = result.getCreatedUser();
		if(!username.equals(createUser) && !username.equals("admin")) {
			throw new UserMismatchException("更改失败，无法更改他人的项目信息");
		}
		project.setPid(pid);
		project.setModifiedUser(username);
		project.setModifiedTime(new Date());
		updateByPid(project);
	}

	@Override
	public void removeProject(Integer pid,Integer uid) throws RootException, ProjectNotFindException, DeleteException {
		Project project = findByPid(pid);
		Integer root = userService.findByUid(uid).getRoot();
		if(root!=0) {
			throw new RootException("权限不足，无法删除");
		}
		if(project==null) {
			throw new ProjectNotFindException("删除失败，项目不存在");
		}
		deleteByPid(pid);
	}

	@Override
	public Project getByPid(Integer pid) {
		return findByPid(pid);
	}

	/**
	 * 插入项目数据
	 * @param project 项目信息
	 * @return 受影响的行数
	 */
	private void insert(Project project) {
		Integer rows = projectMapper.insert(project);
		if(rows!=1) {
			throw new InsertException("添加数据失败，出现未知错误");
		}
	}
	
	/**
	 * 根据项目编号查询是否已经存在
	 * @param pronum 项目编号
	 * @return 项目信息 若不存在则为null
	 */
	private Project findByPronum(String pronum) {
		return projectMapper.findByPronum(pronum);
	}
	
	/**
	 * 查询所有项目信息
	 * @return 项目集合
	 */
	private List<Project> findAllProject(){
		return projectMapper.findAllProject();
	}
	
	/**
	 * 模糊查询 根据用户输入的参数查询
	 * @param parameter 用户输入的参数
	 * @return
	 */
	private List<Project> findByParameter(String parameter){
		return projectMapper.findByParameter(parameter);
	}
	
	/**
	 * 根据项目id修改项目信息
	 * @param project 项目信息
	 * @return 受影响的行数
	 */
	private void updateByPid(Project project) {
		Integer rows = projectMapper.updateByPid(project);
		if(rows!=1) {
			throw new UpdateException("更新失败，出现未知错误");
		}
	}
	
	/**
	 * 根据项目标号查询项目信息
	 * @param pid 项目编号
	 * @return 项目信息
	 */
	public Project findByPid(Integer pid) {
		return projectMapper.findByPid(pid);
	}
	
	/**
	 * 根据项目id删除当前项目
	 * @param pid 项目id
	 * @return 受影响的行数
	 */
	private void deleteByPid(Integer pid) {
		Integer rows = projectMapper.deleteByPid(pid);
		if(rows!=1) {
			throw new DeleteException("删除失败，未知错误");
		}
	}
}
