package com.mutong.mhscowboy.mapper;

import java.util.List;

import com.mutong.mhscowboy.entity.Project;

/**
 * 处理项目数据的持久层接口
 */
public interface ProjectMapper {
	/**
	 * 插入项目数据
	 * @param project 项目信息
	 * @return 受影响的行数
	 */
	Integer insert(Project project);
	
	/**
	 * 根据项目编号查询是否已经存在
	 * @param pronum 项目编号
	 * @return 项目信息 若不存在则为null
	 */
	Project findByPronum(String pronum);
	
	/**
	 * 查询所有项目信息
	 * @return 项目集合
	 */
	List<Project> findAllProject();
	
	/**
	 * 模糊查询 根据用户输入的参数查询
	 * @param parameter 用户输入的参数
	 * @return
	 */
	List<Project> findByParameter(String parameter);
	
	/**
	 * 根据项目id修改项目信息
	 * @param project 项目信息
	 * @return 受影响的行数
	 */
	Integer updateByPid(Project project);
	
	/**
	 * 根据项目标号查询项目信息
	 * @param pid 项目编号
	 * @return 项目信息
	 */
	Project findByPid(Integer pid);
	
	/**
	 * 根据项目id删除当前项目
	 * @param pid 项目id
	 * @return 受影响的行数
	 */
	Integer deleteByPid(Integer pid);
}
