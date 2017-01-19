package com.ray.framework.webapp.rest;

public class RestResultResponse {
	private RestResultStatus status;
	private Object result;

	public RestResultResponse(RestResultStatus status, Object result) {
		this.status = status;
		this.result = result;
	}

	public RestResultStatus getStatus() {
		return status;
	}

	public Object getResult() {
		return result;
	}
}
