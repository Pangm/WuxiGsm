package com.x2.gsm.dao;

import java.util.Date;

public class PhoneRecordDAO {
  
	private Date time;
	private String deviceID;
	private String firstPartOfNum;
	private String cellID;
	private String areaID;
	
	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getDeviceID() {
		return deviceID;
	}

	public void setDeviceID(String deviceID) {
		this.deviceID = deviceID;
	}

	public String getFirstPartOfNum() {
		return firstPartOfNum;
	}

	public void setFirstPartOfNum(String firstPartOfNum) {
		this.firstPartOfNum = firstPartOfNum;
	}

	public String getCellID() {
		return cellID;
	}

	public void setCellID(String cellID) {
		this.cellID = cellID;
	}

	public String getAreaID() {
		return areaID;
	}

	public void setAreaID(String areaID) {
		this.areaID = areaID;
	}

	public PhoneRecordDAO(Date time,String deviceID,
			String firstPartOfNum,String cellID,String areaID){
		this.time = time;
		this.deviceID = deviceID;
		this.firstPartOfNum = firstPartOfNum;
		this.cellID = cellID;
		this.areaID = areaID;
	}	
}
