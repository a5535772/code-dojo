package com.code.dojo.demo.shitcode01.api.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;


@Builder
@Data
@ToString
public class BindUserDto {
    private Long userId;
    private Integer brandCode;
    private String vin;


}
