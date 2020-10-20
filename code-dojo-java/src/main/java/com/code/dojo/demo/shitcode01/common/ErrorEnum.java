package com.code.dojo.demo.shitcode01.common;

public enum ErrorEnum {

	CAR_HAS_BEEN_BIND("1", "never mind,just some msg"), VIN_INVAILDE("3", "never mind,just some msg"),
	BRANDCODE_INVALIDE("4", "never mind,just some msg"),;
	private String value;
	private String msg;

	ErrorEnum(String value, String msg) {
		this.value = value;
		this.msg = msg;
	}

	public String getValue() {
		return value;
	}

	public String getMsg() {
		return msg;
	}

}
