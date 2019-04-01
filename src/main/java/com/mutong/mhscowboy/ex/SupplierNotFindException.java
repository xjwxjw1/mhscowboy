package com.mutong.mhscowboy.ex;

public class SupplierNotFindException extends ServiceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3588038836327690292L;

	public SupplierNotFindException() {
		super();
	}

	public SupplierNotFindException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public SupplierNotFindException(String message, Throwable cause) {
		super(message, cause);
	}

	public SupplierNotFindException(String message) {
		super(message);
	}

	public SupplierNotFindException(Throwable cause) {
		super(cause);
	}

}
