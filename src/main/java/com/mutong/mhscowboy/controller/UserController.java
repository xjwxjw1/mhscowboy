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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mutong.mhscowboy.entity.User;
import com.mutong.mhscowboy.service.IUserService;
/**
 * 控制器层
 * @author Administrator
 *
 */
import com.mutong.mhscowboy.util.ResponseResult;
import com.mutong.mhscowboy.vo.UserVO;
@RequestMapping("/user")
@RestController
public class UserController extends BaseController{
	
	@Autowired
	private IUserService userService;
	
	/**
	 * 用户注册
	 * @param user
	 * @return
	 */
	@PostMapping("/reg")
	public ResponseResult<Void> reg(User user){
		userService.reg(user);
		return new ResponseResult<>(SUCCESS);
	}
	
	/**
	 * 用户登录
	 * @param username 用户名
	 * @param password 密码
	 * @param session 登陆成功在session中存入uid和username
	 * @return
	 */
	@PostMapping("/login")
	public ResponseResult<User> login(@RequestParam("username")String username,@RequestParam("password")String password,HttpSession session){
		User data = userService.login(username, password);
		session.setAttribute("uid", data.getUid());
		session.setAttribute("username", username);
		return new ResponseResult<>(SUCCESS,data);
	}
	
	/**
	 * 修改密码
	 * @param oldPassword 老密码
	 * @param newPassword 新密码
	 * @param session 从session获取uid
	 * @return
	 */
	@PostMapping("/change_password")
	public ResponseResult<User> changePassword(@RequestParam("old_password") String oldPassword,
			@RequestParam("new_password") String newPassword,
			HttpSession session){
		Integer uid = getUidFromSession(session);
		String username = getUsernameFromSession(session);
		userService.changePassword(uid, oldPassword, newPassword, username);
		return new ResponseResult<>(SUCCESS);
	}
	
	/**
	 * 获取所有用户列表
	 * @return
	 */
	@GetMapping("/userList")
	public ResponseResult<List<UserVO>> getUser(){
		List<UserVO> data = userService.getUser();
		return new ResponseResult<>(SUCCESS,data);
	}
	
	/**
	 * 修改指定的用户信息
	 * @param user 修改的资料
	 * @param uid 当前登录用户的id
	 * @return
	 */
	@PostMapping("/{uid}/changeInfo")
	public ResponseResult<Void> changeInfo(User user,@PathVariable("uid")Integer uid,HttpSession session){
		Integer loginUid = getUidFromSession(session);
		String username = getUsernameFromSession(session);
		userService.changeInfo(user, loginUid, uid,username );
		return new ResponseResult<>(SUCCESS);
	}
	
	/**
	 * 删除用户 只有管理员可以删除
	 * @param session
	 * @param uid
	 * @return
	 */
	@PostMapping("/{uid}/delete")
	public ResponseResult<Void> deleteUser(HttpSession session,@PathVariable("uid")Integer uid){
		Integer rootUid = getUidFromSession(session);
		userService.deleteUser(uid, rootUid);
		return new ResponseResult<>(SUCCESS);
	}
	
	/**
	 * 模糊查询 查找匹配的用户信息
	 * @param parameter
	 * @return
	 */
	@GetMapping("/{parameter}/select")
	public ResponseResult<List<UserVO>> getByparameter(@PathVariable("parameter")String parameter){
		List<UserVO> data = userService.getByParameter(parameter);
		return new ResponseResult<>(SUCCESS,data);
	}

	/**
	 * 根据部门id 统计部门人数
	 * @param deptno 部门id
	 * @return 人数
	 */
	@GetMapping("/{deptno}/deptno")
	public ResponseResult<Integer> getByparameter(@PathVariable("deptno")Integer deptno){
		Integer data = userService.getByDeptno(deptno);
		return new ResponseResult<>(SUCCESS,data);
	}
	
	/**
	 * 根据用户id查找对应的用户信息
	 * @param uid 用户id
	 * @return 用户信息
	 */
	@GetMapping("/{uid}/selectInfo")
	public ResponseResult<User> select(@PathVariable("uid")Integer uid){
		User data = userService.getByUid(uid);
		return new ResponseResult<>(SUCCESS,data);
	}
	
	/**
	 * 用户退出 清除session
	 * @param session
	 * @return
	 */
	@PostMapping("/exit")
	public ResponseResult<Void> exit(HttpSession session){
		session.invalidate();
		return new ResponseResult<>(SUCCESS);
	}
	
	/**
	 * 将用户信息导出到excel表
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@GetMapping("/download")
	public byte[] download(HttpServletResponse response) throws IOException{
		String file = URLEncoder.encode("用户列表.xlsx","utf-8");
		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		response.setHeader("Content-Disposition","attachment; filename=\""+file+"\"");
		XSSFWorkbook workbook =new XSSFWorkbook();
		XSSFSheet sheet=workbook.createSheet("用户表");
		List<UserVO> list = userService.getUser();		
		XSSFRow rows = sheet.createRow(0);
		String[] title = new String[6];
		title[0] = "用户id";
		title[1] = "用户姓名";
		title[2] = "用户性别";
		title[3] = "用户电话";
		title[4] = "用户邮箱";
		title[5] = "所属部门";
		XSSFCell cell;
		for(int j=0;j<6;j++) {
			cell = rows.createCell(j);
			cell.setCellValue(title[j]);
		}
		int i=1;
		for(UserVO data:list) {
			rows = sheet.createRow(i++);
			String[] userInfo = new String[6];
			userInfo[0] = data.getUid()+"";
			userInfo[1] = data.getName();
			userInfo[2] = data.getGender()==1?"男":"女";
			userInfo[3] = data.getPhone();
			userInfo[4] = data.getEmail();
			userInfo[5] = data.getDeptname();
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
