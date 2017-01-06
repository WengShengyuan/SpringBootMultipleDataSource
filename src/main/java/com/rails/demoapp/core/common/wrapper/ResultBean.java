package com.rails.demoapp.core.common.wrapper;

import java.io.Serializable;
import java.util.HashMap;

public class ResultBean implements Serializable{

	private static final long serialVersionUID = -4914023321787403540L;
	
	private Integer code=0;
	private String errorMsg="";
	private HashMap<String, Object> data = new HashMap<String,Object>();
	
	public Integer getStateId() {
		return code;
	}
	public void setStateId(Integer stateId) {
		this.code = stateId;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public HashMap<String, Object> getData() {
		return data;
	}
	public void setData(HashMap<String, Object> data) {
		this.data = data;
	}
	
	public void add(String key,Object value){
		data.put(key, value);
	}

	public void error(String msg){
		code = -1;
		errorMsg = msg;
	}
	
	public void error(Integer code, String msg){
		code = code;
		errorMsg = msg;
	}
	
}
