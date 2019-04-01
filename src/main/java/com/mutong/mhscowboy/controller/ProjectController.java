package com.mutong.mhscowboy.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mutong.mhscowboy.entity.Project;
import com.mutong.mhscowboy.service.IProjectService;
import com.mutong.mhscowboy.util.ResponseResult;
import com.mutong.mhscowboy.vo.UserVO;

@RequestMapping("/project")
@RestController
public class ProjectController extends BaseController{
	
	@Autowired
	private IProjectService projectService;
	
	/**
	 * 添加项目
	 * @param project 项目信息
	 * @param session
	 * @return 状态码
	 */
	@PostMapping("/add")
	public ResponseResult<Void> getByAll(Project project,HttpSession session){
		Integer uid = getUidFromSession(session);
		String username = getUsernameFromSession(session);
		projectService.addNew(project,uid,username);
		return new ResponseResult<>(SUCCESS);
	}
	
	/**
	 * 获取所有的项目集合
	 * @return 项目信息集合
	 */
	@GetMapping("/")
	public ResponseResult<List<Project>> getAllProject(){
		List<Project> data = projectService.getAllProject();
		return new ResponseResult<>(SUCCESS,data);
	}
	
	/**
	 * 根据用户的关键词查找相关的项目信息
	 * @param parameter 用户输入的关键词
	 * @return 相关的项目信息
	 */
	@GetMapping("/{parameter}/select")
	public ResponseResult<List<Project>> getByParameter(@PathVariable("parameter")String parameter){
		List<Project> data = projectService.getByParameter(parameter);
		return new ResponseResult<>(SUCCESS,data);
	}
	
	/**
	 * 根据项目id修改对应的项目信息
	 * @param pid 项目id
	 * @param session
	 * @param project 修改后的项目信息
	 * @return 状态码
	 */
	@PostMapping("/{pid}/update")
	public ResponseResult<Void> updateProject(@PathVariable("pid")Integer pid,
			HttpSession session,Project project){
		String username = getUsernameFromSession(session);
		projectService.updateProject(project, pid, username);
		return new ResponseResult<>(SUCCESS);
	}
	
	/**
	 * 根据项目id删除相对应的项目信息
	 * @param pid 项目id
	 * @param session
	 * @return 状态码
	 */
	@PostMapping("/{pid}/remove")
	public ResponseResult<Void> removeProject(@PathVariable("pid")Integer pid,
			HttpSession session){
		Integer uid = getUidFromSession(session);
		projectService.removeProject(pid, uid);
		return new ResponseResult<>(SUCCESS);
	}
	
	/**
	 * 根据项目id查找相对应的项目信息
	 * @param pid 项目id
	 * @return 相关的项目信息
	 */
	@GetMapping("/{pid}/selectProject")
	public ResponseResult<Project> getByPid(@PathVariable("pid")Integer pid){
		Project data = projectService.getByPid(pid);
		return new ResponseResult<>(SUCCESS,data);
	}
	
	@GetMapping("/download")
	public byte[] download(HttpServletResponse response) throws IOException{
		String file = URLEncoder.encode("项目列表.xlsx","utf-8");
		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		response.setHeader("Content-Disposition","attachment; filename=\""+file+"\"");
		XSSFWorkbook workbook =new XSSFWorkbook();
		XSSFSheet sheet=workbook.createSheet("用户表");
		List<Project> list = projectService.getAllProject();
		XSSFRow rows = sheet.createRow(0);
		String[] title = new String[6];
		title[0] = "项目编号";
		title[1] = "项目方";
		title[2] = "项目名";
		title[3] = "下单时间";
		title[4] = "项目负责人";
		title[5] = "修改执行人";
		XSSFCell cell;
		for(int j=0;j<6;j++) {
			cell = rows.createCell(j);
			cell.setCellValue(title[j]);
		}
		int i=1;
		for(Project data:list) {
			rows = sheet.createRow(i++);
			String[] projectInfo = new String[6];
			projectInfo[0] = data.getPronum()+"";
			projectInfo[1] = data.getApplicant();
			projectInfo[2] = data.getName();
			projectInfo[3] = data.getTime();
			projectInfo[4] = data.getPerson();
			projectInfo[5] = data.getModifiedUser();
			for(int j=0;j<6;j++) {
				cell = rows.createCell(j);
				cell.setCellValue(projectInfo[j]);
			}
		}
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		workbook.write(out);
		workbook.close();
		out.close();
		byte[] bytes = out.toByteArray();
		return bytes;
	}
}
