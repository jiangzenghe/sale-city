package com.tiger.billmall.entity;

import java.util.ArrayList;

public class ProductLay {
	private String deviceGroupId;
	private ArrayList<Product> deviceGroup;
	public String getDeviceGroupId() {
		return deviceGroupId;
	}
	public void setDeviceGroupId(String deviceGroupId) {
		this.deviceGroupId = deviceGroupId;
	}
	public ArrayList<Product> getDeviceGroup() {
		return deviceGroup;
	}
	public void setDeviceGroup(ArrayList<Product> deviceGroup) {
		this.deviceGroup = deviceGroup;
	}
	
	
}
