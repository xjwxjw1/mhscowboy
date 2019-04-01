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

import com.mutong.mhscowboy.entity.Dept;
import com.mutong.mhscowboy.entity.Supplier;
import com.mutong.mhscowboy.service.IDeptService;
import com.mutong.mhscowboy.util.ResponseResult;

@RequestMapping("/dept")
@RestController
public class DeptController extends BaseController{
	
	@Autowired
	private IDeptService deptService;
	
	/**
	 * 获取所有部门信息
	 * @return 部门信息
	 */
	@GetMapping("/")
	public ResponseResult<List<Dept>> getByAll(){
		List<Dept> data = deptService.getAllDept();
		return new ResponseResult<>(SUCCESS,data);
	}
	
	/**
	 * 删除部门信息
	 * @param did 部门id
	 * @param session 
	 * @return 状态码
	 */
	@PostMapping("/{did}/delete")
	public ResponseResult<Void> remove(@PathVariable("did")Integer did,HttpSession session){
		Integer uid = getUidFromSession(session);
		deptService.removeDept(uid, did);
		return new ResponseResult<>(SUCCESS);
	}
	
	/**
	 * 将部门信息导出到excel表
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@GetMapping("/download")
	public byte[] download(HttpServletResponse response) throws IOException{
		String file = URLEncoder.encode("供应商列表.xlsx","utf-8");
		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		response.setHeader("Content-Disposition","attachment; filename=\""+file+"\"");
		XSSFWorkbook workbook =new XSSFWorkbook();
		XSSFSheet sheet=workbook.createSheet("供应商表");
		List<Dept> list = deptService.getAllDept();
		XSSFRow rows = sheet.createRow(0);
		String[] title = new String[5];
		title[0] = "部门编码";
		title[1] = "部门名字";
		title[2] = "部门领导";
		title[3] = "创建人";
		title[4] = "创建时间";
		XSSFCell cell;
		for(int j=0;j<5;j++) {
			cell = rows.createCell(j);
			cell.setCellValue(title[j]);
		}
		int i=1;
		for(Dept data:list) {
			rows = sheet.createRow(i++);
			String[] Info = new String[5];
			Info[0] = data.getDid()+"";
			Info[1] = data.getDeptname();
			Info[2] = data.getMgr();
			Info[3] = data.getCreatedUser();
			Info[4] = data.getCreatedTime()+"";
			for(int j=0;j<5;j++) {
				cell = rows.createCell(j);
				cell.setCellValue(Info[j]);
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
