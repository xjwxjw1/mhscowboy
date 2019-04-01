package com.mutong.mhscowboy.ex;

public class MaterielNotFindException extends ServiceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3187578958781454668L;

	public MaterielNotFindException() {
		super();
	}

	public MaterielNotFindException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public MaterielNotFindException(String message, Throwable cause) {
		super(message, cause);
	}

	public MaterielNotFindException(String message) {
		super(message);
	}

	public MaterielNotFindException(Throwable cause) {
		super(cause);
	}

}
