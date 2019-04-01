package com.mutong.mhscowboy.ex;

public class DeleteException extends ServiceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2458616804889124224L;

	public DeleteException() {
		super();
	}

	public DeleteException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public DeleteException(String message, Throwable cause) {
		super(message, cause);
	}

	public DeleteException(String message) {
		super(message);
	}

	public DeleteException(Throwable cause) {
		super(cause);
	}
	
}
