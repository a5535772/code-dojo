package com.code.dojo.demo.shitcode01.api.dto;

public class BindUserDto {
	private Long userId;
	private Integer brandCode;
	
	public BindUserDto() {
		super();
	}

	public BindUserDto(Integer brandCode) {
		super();
		this.brandCode = brandCode;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getBrandCode() {
		return brandCode;
	}

	public void setBrandCode(Integer brandCode) {
		this.brandCode = brandCode;
	}

}
