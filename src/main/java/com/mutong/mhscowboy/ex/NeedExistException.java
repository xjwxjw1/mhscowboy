package com.mutong.mhscowboy.ex;

public class NeedExistException extends ServiceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 876626959966777103L;

	public NeedExistException() {
		super();
	}

	public NeedExistException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public NeedExistException(String message, Throwable cause) {
		super(message, cause);
	}

	public NeedExistException(String message) {
		super(message);
	}

	public NeedExistException(Throwable cause) {
		super(cause);
	}
	
}
