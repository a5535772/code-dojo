package com.code.dojo.demo.shitcode01.api;

import com.code.dojo.demo.shitcode01.api.dto.BindUserDto;
import com.code.dojo.demo.shitcode01.common.ApiException;

public interface IBindedCarApiRF {

	BindUserDto bindCar(Long userId, Integer brandCode, String vin) throws ApiException;

}
