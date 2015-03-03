package org.pxcbpmisframework.core.json;

import java.util.Map;

/**
 * $.ajax接受的JSON封装
 * 
 * @author panxiaochao 2013.6.11
 * 
 */
public class AjaxJson {
	private boolean success = true;
	private String msg;
	private Object obj;
	private Map<String, Object> map;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

}
