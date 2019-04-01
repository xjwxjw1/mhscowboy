package com.mutong.mhscowboy.controller;

import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.mutong.mhscowboy.ex.DeleteException;
import com.mutong.mhscowboy.ex.DeptNoException;
import com.mutong.mhscowboy.ex.InsertException;
import com.mutong.mhscowboy.ex.MaterielNotFindException;
import com.mutong.mhscowboy.ex.MatnumExistException;
import com.mutong.mhscowboy.ex.NeedExistException;
import com.mutong.mhscowboy.ex.PasswordNotMatchException;
import com.mutong.mhscowboy.ex.ProjectNotFindException;
import com.mutong.mhscowboy.ex.ProjectNumExistException;
import com.mutong.mhscowboy.ex.RootException;
import com.mutong.mhscowboy.ex.ServiceException;
import com.mutong.mhscowboy.ex.SupplierExistException;
import com.mutong.mhscowboy.ex.UpdateException;
import com.mutong.mhscowboy.ex.UserMismatchException;
import com.mutong.mhscowboy.ex.UserNotFoundException;
import com.mutong.mhscowboy.ex.UsernameDuplicateException;
import com.mutong.mhscowboy.util.ResponseResult;

/**
 * 控制器类的基类
 */
public abstract class BaseController {
	
	/**
	 * 响应结果的状态：成功
	 */
	public static final Integer SUCCESS = 200;
	
	/**
	 * 从Session获取当前登录的用户id
	 * @param session HttpSession对象
	 * @return 当前登录的用户id
	 */
	protected final Integer getUidFromSession(HttpSession session) {
		return Integer.valueOf(session.getAttribute("uid").toString());
	}
	
	protected final String getUsernameFromSession(HttpSession session) {
		return session.getAttribute("username").toString();
	}

	/**
	 * 统一处理异常
	 * @param e
	 * @return
	 */
	@ExceptionHandler({ServiceException.class
		, FileUploadException.class})
	public ResponseResult<Void> handleException(Throwable e) {
		ResponseResult<Void> rr
			= new ResponseResult<>();
		rr.setMessage(e.getMessage());
		
		if (e instanceof UsernameDuplicateException) {
			// 400-用户名冲突
			rr.setState(400);
		}else if (e instanceof UserNotFoundException) {
			// 401-用户名不存在
			rr.setState(401);
		}else if (e instanceof PasswordNotMatchException) {
			// 402-密码错误
			rr.setState(402);
		}else if (e instanceof RootException) {
			// 403-权限不足
			rr.setState(403);
		}else if (e instanceof DeptNoException) {
			// 404-部门错误
			rr.setState(404);
		}else if (e instanceof ProjectNumExistException) {
			// 405-项目id已经存在
			rr.setState(405);
		}else if (e instanceof ProjectNotFindException) {
			// 406-未找到匹配的项目信息
			rr.setState(406);
		}else if (e instanceof UserMismatchException) {
			// 407-用户名不匹配
			rr.setState(407);
		}else if (e instanceof MatnumExistException) {
			// 408-物料id已经存在
			rr.setState(408);
		}else if (e instanceof MaterielNotFindException) {
			// 409-物料不存在
			rr.setState(409);
		}else if (e instanceof InsertException) {
			// 500-创建异常
			rr.setState(500);
		}else if (e instanceof UpdateException) {
			// 501-更新异常
			rr.setState(501);
		}else if (e instanceof DeleteException) {
			// 502-删除异常
			rr.setState(502);
		}else if (e instanceof NeedExistException) {
			// 503-物料信息已经存在
			rr.setState(503);
		}else if (e instanceof NeedExistException) {
			// 504-未找到匹配的物料信息
			rr.setState(504);
		}else if (e instanceof SupplierExistException) {
			// 505-供应商已经存在
			rr.setState(505);
		}else if (e instanceof SupplierExistException) {
			// 506-供应商不存在
			rr.setState(506);
		}
		return rr;
	}
	
}
