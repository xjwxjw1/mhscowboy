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

import com.mutong.mhscowboy.entity.Supplier;
import com.mutong.mhscowboy.service.ISupplierService;
import com.mutong.mhscowboy.util.ResponseResult;
import com.mutong.mhscowboy.vo.UserVO;

@RequestMapping("/supplier")
@RestController
public class SupplierController extends BaseController{
	
	@Autowired
	private ISupplierService supplierService;
	
	/**
	 * 添加供应商信息
	 * @param supplier 供应商信息
	 * @param session
	 * @return 状态码
	 */
	@PostMapping("/add")
	public ResponseResult<Void> add(Supplier supplier,HttpSession session){
		Integer uid = getUidFromSession(session);
		String username = getUsernameFromSession(session);
		supplierService.add(supplier, uid, username);
		return new ResponseResult<>(SUCCESS);
	}
	
	/**
	 * 获取所有供应商信息
	 * @return 供应商集合
	 */
	@GetMapping("/")
	public ResponseResult<List<Supplier>> getAll(){
		List<Supplier> data = supplierService.getAll();
		return new ResponseResult<>(SUCCESS,data);
	}
	
	/**
	 * 根据供应商id删除此条记录
	 * @param session 
	 * @param sid 供应商id
	 * @return 状态码
	 */
	@PostMapping("/{sid}/remove")
	public ResponseResult<Void> remove(HttpSession session,@PathVariable("sid")Integer sid){
		Integer uid = getUidFromSession(session);
		supplierService.remove(sid, uid );
		return new ResponseResult<>(SUCCESS);
	}
	
	/**
	 * 根据用户输入的关键词查询
	 * @param parameter 用户输入的关键词
	 * @return 
	 */
	@GetMapping("/{parameter}/find")
	public ResponseResult<List<Supplier>> findByParameter(@PathVariable("parameter")String parameter){
		List<Supplier> data = supplierService.getByParameter(parameter);
		return new ResponseResult<>(SUCCESS,data);
	}
	
	/**
	 * 根据供应商id查询相关信息
	 * @param sid 供应商id
	 * @return 相关信息
	 */
	@GetMapping("/{sid}/findSid")
	public ResponseResult<Supplier> getBySid(@PathVariable("sid")Integer sid){
		Supplier data = supplierService.getBySid(sid);
		return new ResponseResult<>(SUCCESS,data);
	}
	
	/**
	 * 根据sid修改当前信息
	 * @param supplier 修改后的供应商信息
	 * @param sid 供应商id
	 * @param session
	 * @return 状态码
	 */
	@PostMapping("{sid}/update")
	public ResponseResult<Void> updateSupplie(Supplier supplier,@PathVariable("sid")Integer sid,HttpSession session){
		Integer uid = getUidFromSession(session);
		String username = getUsernameFromSession(session);
		supplierService.updateSupplier(supplier, sid, uid, username);
		return new ResponseResult<>(SUCCESS);
	}
	
	/**
	 * 将供应商信息导出到excel表
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
		List<Supplier> list = supplierService.getAll();	
		XSSFRow rows = sheet.createRow(0);
		String[] title = new String[6];
		title[0] = "供应商代码";
		title[1] = "供应商名称";
		title[2] = "联系人";
		title[3] = "联系地址";
		title[4] = "联系电话";
		title[5] = "传真";
		XSSFCell cell;
		for(int j=0;j<6;j++) {
			cell = rows.createCell(j);
			cell.setCellValue(title[j]);
		}
		int i=1;
		for(Supplier data:list) {
			rows = sheet.createRow(i++);
			String[] Info = new String[6];
			Info[0] = data.getSupnum()+"";
			Info[1] = data.getName();
			Info[2] = data.getContacts();
			Info[3] = data.getAddress();
			Info[4] = data.getPhone();
			Info[5] = data.getFax();
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
