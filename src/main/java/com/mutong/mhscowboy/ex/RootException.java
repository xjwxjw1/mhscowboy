package com.mutong.mhscowboy.ex;

public class RootException extends ServiceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3373388725164781819L;

	public RootException() {
		super();
	}

	public RootException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public RootException(String message, Throwable cause) {
		super(message, cause);
	}

	public RootException(String message) {
		super(message);
	}

	public RootException(Throwable cause) {
		super(cause);
	}

}
