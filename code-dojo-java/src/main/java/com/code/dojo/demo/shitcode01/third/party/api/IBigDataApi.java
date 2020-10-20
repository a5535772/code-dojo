package com.code.dojo.demo.shitcode01.third.party.api;

import com.code.dojo.demo.shitcode01.api.dto.BindUserDto;

public interface IBigDataApi {

	BindUserDto findUserByVin(String vin);
}
