package com.mutong.mhscowboy.ex;

public class NeedNotFindException extends ServiceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4134653216761298719L;

	public NeedNotFindException() {
		super();
	}

	public NeedNotFindException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public NeedNotFindException(String message, Throwable cause) {
		super(message, cause);
	}

	public NeedNotFindException(String message) {
		super(message);
	}

	public NeedNotFindException(Throwable cause) {
		super(cause);
	}

}
