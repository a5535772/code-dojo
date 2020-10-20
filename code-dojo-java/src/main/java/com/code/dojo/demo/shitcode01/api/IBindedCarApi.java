package com.code.dojo.demo.shitcode01.api;

import com.code.dojo.demo.shitcode01.api.dto.BindUserDto;
import com.code.dojo.demo.shitcode01.common.ApiException;

public interface IBindedCarApi {

	BindUserDto bindCar(String userId, Integer brandCode, String vin) throws ApiException;

}
