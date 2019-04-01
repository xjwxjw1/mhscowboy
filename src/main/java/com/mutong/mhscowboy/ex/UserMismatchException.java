package com.mutong.mhscowboy.ex;

public class UserMismatchException extends ServiceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6067167156471606348L;

	public UserMismatchException() {
		super();
	}

	public UserMismatchException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public UserMismatchException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserMismatchException(String message) {
		super(message);
	}

	public UserMismatchException(Throwable cause) {
		super(cause);
	}

}
