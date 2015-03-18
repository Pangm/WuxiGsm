package com.x2.gsm.client;

import com.x2.gsm.dao.PhoneRecordDAO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GsmResponse {
	private String ak;
	private int status;
	private Map<String, String> map = new HashMap<String, String>();
	private ArrayList<PhoneRecordDAO> list = new ArrayList<PhoneRecordDAO>();
	public String getAk() {
		return ak;
	}
	public void setAk(String ak) {
		this.ak = ak;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Map<String, String> getMap() {
		return map;
	}
	public void setMap(Map<String, String> map) {
		this.map = map;
	}
	public ArrayList<PhoneRecordDAO> getList() {
		return list;
	}
	public void setList(ArrayList<PhoneRecordDAO> list) {
		this.list = list;
	}

}
