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

import com.mutong.mhscowboy.entity.Need;
import com.mutong.mhscowboy.service.INeedService;
import com.mutong.mhscowboy.util.ResponseResult;
import com.mutong.mhscowboy.vo.UserVO;

@RequestMapping("/need")
@RestController
public class NeedController extends BaseController{
	
	@Autowired
	private INeedService needService;
	
	/**
	 * 添加物料需求信息
	 * @param need 物料信息
	 * @param pid 项目id
	 * @param session
	 * @return 状态码
	 */
	@PostMapping("/{pid}/add")
	public ResponseResult<Void> add(Need need,@PathVariable("pid") Integer pid,HttpSession session){
		Integer uid = getUidFromSession(session);
		String username = getUsernameFromSession(session);
		needService.add(need, pid, uid, username);
		return new ResponseResult<>(SUCCESS);
	}
	
	/**
	 * 根据项目id查找相关的物料需求信息
	 * @param pid 项目id
	 * @return 物料需求相关信息
	 */
	@GetMapping("/{pid}/select")
	public ResponseResult<List<Need>> select(@PathVariable("pid") Integer pid){
		List<Need> data = needService.getAllByPid(pid);
		return new ResponseResult<>(SUCCESS,data);
	}
	
	/**
	 * 根据项目id，用户输入的关键词查找改项目相关的物料需求信息
	 * @param pid 项目id
	 * @param parameter 用户输入的关键词
	 * @return 相关的信息
	 */
	@GetMapping("/{pid}/{parameter}/selectParameter")
	public ResponseResult<List<Need>> getByParameter(@PathVariable("pid")Integer pid,
			@PathVariable("parameter") String parameter){
		List<Need> data = needService.getByParameter(parameter,pid);
		return new ResponseResult<>(SUCCESS,data);
	}
	
	/**
	 * 根据物料需求id获取相关的信息
	 * @param nid 物料需求id
	 * @return 相关信息
	 */
	@GetMapping("/{nid}/find")
	public ResponseResult<Need> getByNid(@PathVariable("nid")Integer nid){
		Need data = needService.getByNid(nid);
		return new ResponseResult<>(SUCCESS,data);
	}
	
	/**
	 * 根据nid修改当前物料需求信息
	 * @param need 修改后的物料需求信息
	 * @param nid 物料需求id
	 * @param session
	 * @return 状态码
	 */
	@PostMapping("/{nid}/update")
	public ResponseResult<Need> updateNeed(Need need,
			@PathVariable("nid")Integer nid,HttpSession session){
		Integer uid = getUidFromSession(session);
		String username = getUsernameFromSession(session);
		needService.updateNeed(need, nid, uid, username);
		return new ResponseResult<>(SUCCESS);
	}
	
	/**
	 * 根据物料需求id删除此条记录
	 * @param nid 物料需求id
	 * @param session
	 * @return 状态码
	 */
	@PostMapping("/{nid}/remove")
	public ResponseResult<Need> remove(@PathVariable("nid")Integer nid,HttpSession session){
		Integer uid = getUidFromSession(session);
		String username = getUsernameFromSession(session);
		needService.remove(nid, uid, username);
		return new ResponseResult<>(SUCCESS);
	}
	
	/**
	 * 根据物料需求id更改信息
	 * @param nid 物料需求id
	 * @param session
	 * @return 状态码
	 */
	@PostMapping("/{nid}/updateDemand")
	public ResponseResult<Need> updateDemand(@PathVariable("nid")Integer nid,HttpSession session){
		Integer uid = getUidFromSession(session);
		needService.updateDemand(nid, uid);
		return new ResponseResult<>(SUCCESS);
	}
	
	/**
	 * 将用户信息导出到excel表
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@GetMapping("/{pid}/download")
	public byte[] download(HttpServletResponse response,@PathVariable("pid")Integer pid) throws IOException{
		String file = URLEncoder.encode("物料需求列表.xlsx","utf-8");
		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		response.setHeader("Content-Disposition","attachment; filename=\""+file+"\"");
		XSSFWorkbook workbook =new XSSFWorkbook();
		XSSFSheet sheet=workbook.createSheet("物料需求表");
		List<Need> list = needService.getAllByPid(pid);	
		XSSFRow rows = sheet.createRow(0);
		String[] title = new String[6];
		title[0] = "物料编号";
		title[1] = "物料名";
		title[2] = "申请人";
		title[3] = "申请时间";
		title[4] = "需求(件/套)";
		title[5] = "审核状态";
		XSSFCell cell;
		for(int j=0;j<6;j++) {
			cell = rows.createCell(j);
			cell.setCellValue(title[j]);
		}
		int i=1;
		for(Need data:list) {
			rows = sheet.createRow(i++);
			String[] userInfo = new String[6];
			userInfo[0] = data.getMatnum();
			userInfo[1] = data.getName();
			userInfo[2] = data.getPerson();
			userInfo[3] = data.getTime();
			userInfo[4] = data.getDemand()+"";
			userInfo[5] = data.getAuditing();
			for(int j=0;j<6;j++) {
				cell = rows.createCell(j);
				cell.setCellValue(userInfo[j]);
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
