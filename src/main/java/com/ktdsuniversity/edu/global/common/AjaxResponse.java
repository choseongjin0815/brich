package com.ktdsuniversity.edu.global.common;

import java.util.Map;

public class AjaxResponse {

	private Object body;	// 정상 처리 결과
	private Map<String, Object> error;	// 예외가 있을 때

	public Object getBody() {
		return this.body;
	}

	public void setBody(Object body) {
		this.body = body;
	}

	public Map<String, Object> getError() {
		return this.error;
	}

	public void setError(Map<String, Object> error) {
		this.error = error;
	}
	
}
