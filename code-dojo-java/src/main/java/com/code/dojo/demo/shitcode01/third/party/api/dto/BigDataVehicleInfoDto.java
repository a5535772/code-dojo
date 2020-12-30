package com.code.dojo.demo.shitcode01.third.party.api.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Builder
@Data
@ToString
public class BigDataVehicleInfoDto {
    private Integer brandCode;
    private String vin;
}
