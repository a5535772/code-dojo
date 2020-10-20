package com.code.dojo.demo.shitcode01.api;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.code.dojo.demo.shitcode01.api.dto.BindUserDto;
import com.code.dojo.demo.shitcode01.common.ApiException;
import com.code.dojo.demo.shitcode01.pojo.BindInfo;
import com.code.dojo.demo.shitcode01.service.IBindCarService;
import com.code.dojo.demo.shitcode01.third.party.api.IBigDataApi;
import com.code.dojo.demo.shitcode01.third.party.api.IPointApi;
import com.code.dojo.demo.shitcode01.third.party.api.IRabbitMQApi;

@SuppressWarnings("unused")
@RunWith(MockitoJUnitRunner.class)
public class BindedCarApiImplTest {
	@Mock
	IBigDataApi bigDataApi;
	@Mock
	IPointApi pointApi;
	@Mock
	IRabbitMQApi rabbitMQApi;
	@Mock
	IBindCarService bindCarService;
	@InjectMocks
	BindedCarApiImpl bindedCarApiImpl;

	private static final int BRAND_CODE_TESLA = 1;

	private static final int BRAND_CODE_BMW = 2;

	@Test(expected = ApiException.class)
	public void when_car_has_been_bind_then_car_has_been_bind_will_throw_api_exception() throws ApiException {
		/** given */
		String userId = "2020102000001";
		String vin = "ABCDEFG-HIJKLMN";
		// 设置车辆绑定情况为已绑定
		given(bindCarService.findVin(anyString())).willReturn(new BindInfo());
		/** when */
		bindedCarApiImpl.bindCar(userId, BRAND_CODE_TESLA, vin);
	}

	@Test(expected = ApiException.class)
	public void when_big_data_contains_no_car_will_throw_api_exception() throws ApiException {
		/** given */
		String userId = "2020102000001";
		String vin = "ABCDEFG-HIJKLMN";
		// 设置车辆在大数据不存在
		given(bigDataApi.findUserByVin(anyString())).willReturn(null);

		/** when */
		bindedCarApiImpl.bindCar(userId, BRAND_CODE_TESLA, vin);
	}

	@Test(expected = ApiException.class)
	public void when_big_data_return_different_brand_against_input_brand_will_throw_api_exception()
			throws ApiException {
		/** given */
		String userId = "2020102000001";
		String vin = "ABCDEFG-HIJKLMN";
		// 设置车辆绑定情况为未已绑定
		given(bindCarService.findVin(anyString())).willReturn(null);
		// 设置车辆在大数据的平台为BRAND_CODE_BMW，和入参BRAND_CODE_TESLA不一样
		given(bigDataApi.findUserByVin(anyString())).willReturn(new BindUserDto(BRAND_CODE_BMW));

		/** when */
		bindedCarApiImpl.bindCar(userId, BRAND_CODE_TESLA, vin);
	}

	@Test()
	public void abcd() throws ApiException {
		/** given */
		String userId = "2020102000001";
		String vin = "ABCDEFG-HIJKLMN";
		// 设置车辆绑定情况为未已绑定
		given(bindCarService.findVin(anyString())).willReturn(null);
		// 设置入参品牌与大数据平台一致
		given(bigDataApi.findUserByVin(anyString())).willReturn(new BindUserDto(BRAND_CODE_TESLA));
		// 设置绑定
		given(bindCarService.findBindRecordBy(anyString(), anyString(), anyInt()))
				.willReturn(new ArrayList<BindInfo>());
		// 设置数据插入响应条数为1
		given(bindCarService.saveWeakBind(any(BindInfo.class))).willReturn(1);

		/** when */
		bindedCarApiImpl.bindCar(userId, BRAND_CODE_TESLA, vin);

		/** then */
		verify(bindCarService, times(1)).saveWeakBind(any(BindInfo.class));
		verify(pointApi, times(1)).addPoint(anyString(), anyInt(), any());
	}

}
