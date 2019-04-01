package com.mutong.mhscowboy.ex;

public class ProjectNumExistException extends ServiceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1212667487967809572L;

	public ProjectNumExistException() {
		super();
	}

	public ProjectNumExistException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ProjectNumExistException(String message, Throwable cause) {
		super(message, cause);
	}

	public ProjectNumExistException(String message) {
		super(message);
	}

	public ProjectNumExistException(Throwable cause) {
		super(cause);
	}

}
