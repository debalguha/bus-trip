package org.progressivelifestyle.bustrip.web.dto;

import org.progressivelifestyle.bustrip.web.ERROR_CODE;
import org.progressivelifestyle.bustrip.web.STATUS;

public class APIResponse {
	private ERROR_CODE err;
	private STATUS status;
	
	private Object data;
	
	public APIResponse() {}
	public APIResponse(ERROR_CODE err, STATUS status, Object data) {
		this.err = err;
		this.status = status;
		this.data = data;
	}
	public ERROR_CODE getErr() {
		return err;
	}
	public void setErr(ERROR_CODE err) {
		this.err = err;
	}
	public STATUS getStatus() {
		return status;
	}
	public void setStatus(STATUS status) {
		this.status = status;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	
}
