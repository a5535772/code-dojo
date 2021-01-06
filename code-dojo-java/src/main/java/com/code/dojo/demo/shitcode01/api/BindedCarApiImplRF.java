package com.code.dojo.demo.shitcode01.api;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.code.dojo.demo.shitcode01.api.dto.BindUserDto;
import com.code.dojo.demo.shitcode01.common.ApiException;
import com.code.dojo.demo.shitcode01.common.ErrorEnum;
import com.code.dojo.demo.shitcode01.service.IBindCarService;
import com.code.dojo.demo.shitcode01.service.pojo.BindInfoPojo;
import com.code.dojo.demo.shitcode01.third.party.api.IBigDataApi;
import com.code.dojo.demo.shitcode01.third.party.api.IMQApi;
import com.code.dojo.demo.shitcode01.third.party.api.dto.BigDataVehicleInfoDto;
import com.google.common.annotations.VisibleForTesting;
import com.google.gson.Gson;

public class BindedCarApiImplRF implements IBindedCarApiRF {
    private static final Logger logger = LoggerFactory.getLogger(BindedCarApiImplRF.class);

    private IBindCarService bindCarService;
    private IBigDataApi bigDataApi;
    private IMQApi mqApi;


    /**
     * <ul>
     * <li>思考：重构后的代码仍存在一些问题，当并发绑定同一辆车的时候，有可能会发生什么情况？</li>
     * <li>思考：可以怎么做？</li>
     * </ul>
     */
    @Override
    public BindUserDto bindCar(Long userId, Integer brandCode, String vin) throws ApiException {
        /** 参数校验 */
        if (ObjectUtils.isEmpty(userId) || userId <= 0 || ObjectUtils.isEmpty(brandCode) || StringUtils.isEmpty(vin)) {
            throw new IllegalArgumentException("...");
        }
        /** 业务逻辑校验 */
        if (ObjectUtils.isNotEmpty(bindCarService.findVin(vin))) {
            throw new ApiException(ErrorEnum.CAR_HAS_BEEN_BIND.getValue(), ErrorEnum.CAR_HAS_BEEN_BIND.getMsg());
        }
        /** 根据vin获得大数据车辆实体，用于弱绑车数据落地 */
        BigDataVehicleInfoDto bigDataVehicleInfoDto = getVehicleInfoDtoFromBigData(brandCode, vin);

        /** 进行弱绑车 */
        BindInfoPojo extractedBindInfoPojo = extractedBindInfoPojo(userId, bigDataVehicleInfoDto);
        bindCarService.saveWeakBind(extractedBindInfoPojo);

        /** 发送绑车成功的MQ */
        mqApi.send(new Gson().toJson(extractedBindInfoPojo));

        return BindUserDto.builder().userId(userId).vin(vin).brandCode(brandCode).build();
    }


    private BindInfoPojo extractedBindInfoPojo(Long userId, BigDataVehicleInfoDto bigDataVehicleInfoDto)
            throws ApiException {
        BindInfoPojo currentBindInfoPojo = BindInfoPojo.builder().build();
        try {
            BeanUtils.copyProperties(bigDataVehicleInfoDto, currentBindInfoPojo);
            Date date = new Date();
            currentBindInfoPojo.setUserId(userId);
            currentBindInfoPojo.setCreateDate(date);
            currentBindInfoPojo.setLastUpdateDate(date);
            currentBindInfoPojo.setUserId(userId);
        } catch (IllegalAccessException | InvocationTargetException e) {
            logger.error(e.getMessage());
            throw new ApiException(ErrorEnum.BIND_CAR_SYS_ERROR.getValue(), e.getMessage());
        }
        return currentBindInfoPojo;
    }

    @VisibleForTesting
    protected BigDataVehicleInfoDto getVehicleInfoDtoFromBigData(Integer brandCode, String vin) throws ApiException {
        BigDataVehicleInfoDto bigDataVehicleInfoDto = bigDataApi.findUserByVinRF(vin);
        if (ObjectUtils.isEmpty(bigDataVehicleInfoDto)) {
            throw new ApiException(ErrorEnum.VIN_INVAILDE.getValue(), ErrorEnum.VIN_INVAILDE.getMsg());
        }
        if (brandCodeUnEqual(brandCode, bigDataVehicleInfoDto.getBrandCode())) {
            throw new ApiException(ErrorEnum.BRANDCODE_INVALIDE.getValue(), ErrorEnum.BRANDCODE_INVALIDE.getMsg());
        }
        return bigDataVehicleInfoDto;
    }

    private boolean brandCodeUnEqual(Integer brandCode, int clubBrandCode) {
        return !brandCode.equals(clubBrandCode);
    }

}
