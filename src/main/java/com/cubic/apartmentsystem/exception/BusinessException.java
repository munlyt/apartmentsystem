package com.cubic.apartmentsystem.exception;

public class BusinessException extends Exception {

	private static final long serialVersionUID = 1L;
	public String errorCode;
	public String message;

	public String getErrorCode() {
		return errorCode;
	}

	public String getMessage() {
		return message;
	}

	public BusinessException(String errorCode, String exceptionCause) {
		super(errorCode);
		this.errorCode = errorCode;
		this.message = exceptionCause;
	}

	public BusinessException(String exceptionCause) {
		this.message = exceptionCause;
	}

	public BusinessException(String errorCode, Throwable e) {
		super(errorCode, e);
		this.errorCode = errorCode;
		this.message = e.getLocalizedMessage();
	}

}
