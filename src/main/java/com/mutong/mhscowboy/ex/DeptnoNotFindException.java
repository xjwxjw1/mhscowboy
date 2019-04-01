package com.mutong.mhscowboy.ex;

public class DeptnoNotFindException extends ServiceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3773894422575696057L;

	public DeptnoNotFindException() {
		super();
	}

	public DeptnoNotFindException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public DeptnoNotFindException(String message, Throwable cause) {
		super(message, cause);
	}

	public DeptnoNotFindException(String message) {
		super(message);
	}

	public DeptnoNotFindException(Throwable cause) {
		super(cause);
	}
	
}
