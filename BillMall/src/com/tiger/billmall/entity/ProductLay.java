package com.tiger.billmall.entity;

public class ProductLay {
private String deviceId;
	
	private String deviceLabel;
	//Picture字节流
	private byte[] picBytes;

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getDeviceLabel() {
		return deviceLabel;
	}

	public void setDeviceLabel(String deviceLabel) {
		this.deviceLabel = deviceLabel;
	}

	public byte[] getPicBytes() {
		return picBytes;
	}

	public void setPicBytes(byte[] picBytes) {
		this.picBytes = picBytes;
	}
}
