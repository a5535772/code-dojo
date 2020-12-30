package com.code.dojo.demo.shitcode01.third.party.api;

import com.code.dojo.demo.shitcode01.api.dto.BindUserDto;
import com.code.dojo.demo.shitcode01.third.party.api.dto.BigDataVehicleInfoDto;

public interface IBigDataApi {

    BindUserDto findUserByVin(String vin);

    BigDataVehicleInfoDto findUserByVinRF(String vin);
}
