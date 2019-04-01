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

import com.mutong.mhscowboy.entity.Materiel;
import com.mutong.mhscowboy.entity.Supplier;
import com.mutong.mhscowboy.service.IMaterielService;
import com.mutong.mhscowboy.util.ResponseResult;

@RequestMapping("/materiel")
@RestController
public class MaterielController extends BaseController{
	
	@Autowired
	private IMaterielService materielService;
	
	/**
	 * 添加物料信息
	 * @param materiel 物料信息
	 * @param session
	 * @return 状态码
	 */
	@PostMapping("/add")
	public ResponseResult<Void> add(Materiel materiel,HttpSession session){
		Integer uid = getUidFromSession(session);
		String username = getUsernameFromSession(session);
		materielService.add(materiel, uid,username);
		return new ResponseResult<>(SUCCESS);
	}
	
	/**
	 * 获取所有物料信息集合
	 * @return 物料信息集合
	 */
	@GetMapping("/")
	public ResponseResult<List<Materiel>> getAll(){
		List<Materiel> data = materielService.getAll();
		return new ResponseResult<>(SUCCESS,data);
	}
	
	/**
	 * 根据物料id 获得对应的物料信息
	 * @param mid 物料id
	 * @return 物料信息
	 */
	@GetMapping("/{mid}/select")
	public ResponseResult<Materiel> getByMid(@PathVariable("mid")Integer mid){
		Materiel data = materielService.getByMid(mid);
		return new ResponseResult<>(SUCCESS,data);
	}
	
	/**
	 * 根据用户输入的关键词查找相关的物料信息
	 * @param parameter 用户输入的关键词
	 * @return 相关的物料信息
	 */
	@GetMapping("/{parameter}/find")
	public ResponseResult<List<Materiel>> getByParameter(@PathVariable("parameter")String parameter){
		List<Materiel> data = materielService.getByParameter(parameter);
		return new ResponseResult<>(SUCCESS,data);
	}
	
	/**
	 * 根据物料id修改属性
	 * @param materiel 修改后的物料信息
	 * @param mid 物料id
	 * @param session
	 * @return 状态吗
	 */
	@PostMapping("/{mid}/update")
	public ResponseResult<List<Materiel>> updateMateriel(Materiel materiel,
			@PathVariable("mid")Integer mid,HttpSession session){
		Integer uid = getUidFromSession(session);
		String username = getUsernameFromSession(session);
		materielService.updateMateriel(materiel, mid, uid, username);
		return new ResponseResult<>(SUCCESS);
	}
	
	/**
	 * 根据物料id删除对应的物料信息
	 * @param mid 物料id
	 * @param session
	 * @return 状态码
	 */
	@PostMapping("/{mid}/delete")
	public ResponseResult<List<Materiel>> remove(@PathVariable("mid")Integer mid,HttpSession session){
		Integer uid = getUidFromSession(session);
		materielService.remove(mid, uid);
		return new ResponseResult<>(SUCCESS);
	}
	
	/**
	 * 将物料信息导出到excel表
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@GetMapping("/download")
	public byte[] download(HttpServletResponse response) throws IOException{
		String file = URLEncoder.encode("物料列表.xlsx","utf-8");
		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		response.setHeader("Content-Disposition","attachment; filename=\""+file+"\"");
		XSSFWorkbook workbook =new XSSFWorkbook();
		XSSFSheet sheet=workbook.createSheet("物料表");
		List<Materiel> list = materielService.getAll();
		XSSFRow rows = sheet.createRow(0);
		String[] title = new String[6];
		title[0] = "物料编号";
		title[1] = "物料名";
		title[2] = "计量单位";
		title[3] = "库存";
		title[4] = "创建人";
		title[5] = "创建时间";
		XSSFCell cell;
		for(int j=0;j<6;j++) {
			cell = rows.createCell(j);
			cell.setCellValue(title[j]);
		}
		int i=1;
		for(Materiel data:list) {
			rows = sheet.createRow(i++);
			String[] Info = new String[6];
			Info[0] = data.getMatnum()+"";
			Info[1] = data.getName();
			Info[2] = data.getUnit();
			Info[3] = data.getNum()+"";
			Info[4] = data.getCreatedUser();
			Info[5] = data.getCreatedTime()+"";
			for(int j=0;j<6;j++) {
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
