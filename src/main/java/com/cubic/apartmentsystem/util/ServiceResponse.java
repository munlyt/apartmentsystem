package com.cubic.apartmentsystem.util;

import java.lang.reflect.InvocationTargetException;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import com.cubic.apartmentsystem.exception.BusinessException;

public class ServiceResponse {
	private Boolean success;
	private String message;
	private Map<String, Object> params;

	public ServiceResponse(String message) {
		this(true, message);
	}

	public ServiceResponse(Boolean success, String message) {
		this.success = success;
		this.message = message;
		this.params = new HashMap<String, Object>();
	}

	public void addParam(String key, Object value) {
		params.put(key, value);
	}

	public static HttpHeaders generateApplicationErrors(BusinessException e) {
		HttpHeaders httpHeaders = new HttpHeaders();

		httpHeaders.add("errorCode", e.getErrorCode());
		httpHeaders.add("errorMessage", MessageBundle.getErrorMsg(e.errorCode));
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		return httpHeaders;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Map<String, Object> getParams() {
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}

	public static HttpHeaders generateRuntimeErrors(Exception e) {
		String statusCode = "ERR001";
		String message = MessageBundle.getErrorMsg(statusCode);

		if (e instanceof BusinessException) {
			statusCode = ((BusinessException) e).getErrorCode();
			message = e.getMessage();
		} else if (e instanceof GeneralSecurityException) {
			statusCode = "RUN002";
			message = MessageBundle.getErrorMsg(statusCode);
		} else if (e instanceof InvocationTargetException) {
			statusCode = "RUN003";
			message = MessageBundle.getErrorMsg(statusCode);
		} else if (e instanceof NoSuchMethodException) {
			statusCode = "RUN004";
			message = MessageBundle.getErrorMsg(statusCode);
		} else if (e instanceof Exception) {
			statusCode = "RUN001";
			// message = e.getMessage();
			message = MessageBundle.getErrorMsg(statusCode);
		}

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("errorCode", statusCode);
		httpHeaders.add("errorMessage", message);
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);

		return httpHeaders;
	}
}
