package com.code.dojo.demo.shitcode01.api;

import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.code.dojo.demo.shitcode01.api.dto.BindUserDto;
import com.code.dojo.demo.shitcode01.common.ApiException;
import com.code.dojo.demo.shitcode01.common.ErrorEnum;
import com.code.dojo.demo.shitcode01.pojo.BindInfo;
import com.code.dojo.demo.shitcode01.service.IBindCarService;
import com.code.dojo.demo.shitcode01.third.party.api.IBigDataApi;
import com.code.dojo.demo.shitcode01.third.party.api.IPointApi;
import com.code.dojo.demo.shitcode01.third.party.api.IRabbitMQApi;
import com.google.gson.Gson;

public class BindedCarApiImpl implements IBindedCarApi {

	private IBindCarService carService;
	private IBigDataApi dataApi;
	private IPointApi pApi;
	private IRabbitMQApi mqApi;
	
	@Override
	public BindUserDto bindCar(String userId, Integer brandCode, String vin) throws ApiException {

		BindInfo bindedFromCarService = carService.findVin(vin);
		List<BindInfo> bindRecord = carService.findBindRecordBy(vin, userId, brandCode);
		if (bindedFromCarService != null) {
			throw new ApiException(ErrorEnum.CAR_HAS_BEEN_BIND.getValue(), ErrorEnum.CAR_HAS_BEEN_BIND.getMsg());
		}
		// 好烦，又tm加需求
		BindUserDto findUserFromClub = dataApi.findUserByVin(vin);
		if (findUserFromClub == null) {
			throw new ApiException(ErrorEnum.VIN_INVAILDE.getValue(), ErrorEnum.VIN_INVAILDE.getMsg());
		}
		findUserFromClub.setUserId(Long.parseLong(userId));
		if (!brandCode.equals(findUserFromClub.getBrandCode())) {
			throw new ApiException(ErrorEnum.BRANDCODE_INVALIDE.getValue(), ErrorEnum.BRANDCODE_INVALIDE.getMsg());
		}

		// beanCopy后进行弱绑,成功后返回WeakBindUserDto
		BindInfo bi = new BindInfo();
		try {
			BeanUtils.copyProperties(findUserFromClub, bi);
		} catch (Exception e) {

		}
		Date date = new Date();
			bi.setCreateDate(date);
			bi.setLastUpdateDate(date);
		int insertNum = carService.saveWeakBind(bi);
		if (insertNum > 0) {
			if (insertNum == 1) {
				mqApi.send(new Gson().toJson(bi));
			} else if (insertNum > 1) {
				throw new ApiException(ErrorEnum.CAR_HAS_BEEN_BIND.getValue(), ErrorEnum.CAR_HAS_BEEN_BIND.getMsg());
			}
		}
		if (insertNum == 0) {
			throw new ApiException(ErrorEnum.CAR_HAS_BEEN_BIND.getValue(), ErrorEnum.CAR_HAS_BEEN_BIND.getMsg());
		}
		try {
			if (bindRecord.isEmpty() && String.valueOf(brandCode).equals("1")) {
				teslaFirstBindBounsPoint(userId, brandCode, bindRecord);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return findUserFromClub;
	}

	private void teslaFirstBindBounsPoint(String userId, Integer brandCode, List<BindInfo> bindRecord) {
		pApi.addPoint(userId, brandCode, bindRecord);
	}
}
