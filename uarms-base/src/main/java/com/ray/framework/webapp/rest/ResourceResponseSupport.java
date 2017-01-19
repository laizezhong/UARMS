package com.ray.framework.webapp.rest;

public abstract class ResourceResponseSupport{
	protected RestResultResponse buildErrorRestResultResponse(Exception ex) {
		Throwable reason = ex.getCause();

		if (reason == null) {
			reason = ex;
		}

		RestResultResponse response;

		if (reason instanceof IllegalArgumentException
				|| reason instanceof IllegalStateException) {
			if (reason instanceof IllegalArgumentWithArgumentsException) {
				IllegalArgumentWithArgumentsException typedException = (IllegalArgumentWithArgumentsException) reason;

				response = new RestResultResponse(RestResultStatus.ERROR,
						new ErrorResult(typedException.getFormattedMessage(),
								typedException.getArguments()));
			} else if (reason instanceof IllegalStateWithArgumentsException) {
				IllegalStateWithArgumentsException typedException = (IllegalStateWithArgumentsException) reason;

				response = new RestResultResponse(RestResultStatus.ERROR,
						new ErrorResult(typedException.getMessage(),
								typedException.getArguments()));
			} else {
				response = new RestResultResponse(RestResultStatus.ERROR,
						new ErrorResult(reason.getMessage()));
			}
		} else{
			String msg = ex.getMessage()!=null ? ex.getMessage() : ex.toString();
			response = new RestResultResponse(RestResultStatus.ERROR, new ErrorResult(msg));
		}

		return response;
	}

	protected RestResultResponse buildSuccessRestResultResponse(Object aResult) {
		return new RestResultResponse(RestResultStatus.SUCCESS, aResult);
	}

	protected RestResultResponse buildSuccessRestResultResponse() {
		return new RestResultResponse(RestResultStatus.SUCCESS, "");
	}
}
