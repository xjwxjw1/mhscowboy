package com.mutong.mhscowboy.ex;

public class ProjectNotFindException extends ServiceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 729175431263062030L;

	public ProjectNotFindException() {
		super();
	}

	public ProjectNotFindException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ProjectNotFindException(String message, Throwable cause) {
		super(message, cause);
	}

	public ProjectNotFindException(String message) {
		super(message);
	}

	public ProjectNotFindException(Throwable cause) {
		super(cause);
	}
	
}
