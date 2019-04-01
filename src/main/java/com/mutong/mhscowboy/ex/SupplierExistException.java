package com.mutong.mhscowboy.ex;

public class SupplierExistException extends ServiceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7989053609252641802L;

	public SupplierExistException() {
		super();
	}

	public SupplierExistException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public SupplierExistException(String message, Throwable cause) {
		super(message, cause);
	}

	public SupplierExistException(String message) {
		super(message);
	}

	public SupplierExistException(Throwable cause) {
		super(cause);
	}

}
