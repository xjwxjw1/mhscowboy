package com.mutong.mhscowboy.ex;

public class DeptNoException extends ServiceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7593845872768636463L;

	public DeptNoException() {
		super();
	}

	public DeptNoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public DeptNoException(String message, Throwable cause) {
		super(message, cause);
	}

	public DeptNoException(String message) {
		super(message);
	}

	public DeptNoException(Throwable cause) {
		super(cause);
	}

}
