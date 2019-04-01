package com.mutong.mhscowboy.ex;

public class MatnumExistException extends ServiceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4586583694015500330L;

	public MatnumExistException() {
		super();
	}

	public MatnumExistException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public MatnumExistException(String message, Throwable cause) {
		super(message, cause);
	}

	public MatnumExistException(String message) {
		super(message);
	}

	public MatnumExistException(Throwable cause) {
		super(cause);
	}

}
