package com.code.dojo.demo.shitcode01.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import com.code.dojo.demo.shitcode01.common.ApiException;
import com.code.dojo.demo.shitcode01.common.ErrorEnum;
import com.code.dojo.demo.shitcode01.service.IBindCarService;
import com.code.dojo.demo.shitcode01.service.pojo.BindInfoPojo;
import com.code.dojo.demo.shitcode01.third.party.api.IBigDataApi;
import com.code.dojo.demo.shitcode01.third.party.api.IMQApi;
import com.code.dojo.demo.shitcode01.third.party.api.dto.BigDataVehicleInfoDto;

@RunWith(MockitoJUnitRunner.class)
public class BindedCarApiImplRFTest {
    @Mock
    IBigDataApi bigDataApi;
    @Mock
    IBindCarService bindCarService;
    @Mock
    IMQApi mqApi;
    @InjectMocks
    BindedCarApiImplRF bindedCarApiImplRF;

    private static final int BRAND_CODE_TESLA = 1;

    private static final int BRAND_CODE_BMW = 2;


    /* getBigDataVehicleInfoDto_tests */

    @Test(expected = ApiException.class)
    public void test_getBigDataVehicleInfoDto_when_no_vehicle_exists_error() throws ApiException {
        /** given */
        String vin = "ABCDEFG-HIJKLMN";
        given(bigDataApi.findUserByVinRF(anyString())).willReturn(null);
        /** when */
        bindedCarApiImplRF.getVehicleInfoDtoFromBigData(BRAND_CODE_TESLA, vin);
    }

    @Test(expected = ApiException.class)
    public void test_getBigDataVehicleInfoDto_when_vehicle_returns_different_brand_against_given_brand_error()
            throws ApiException {
        /** given */
        String vin = "ABCDEFG-HIJKLMN";
        given(bigDataApi.findUserByVinRF(anyString()))
                .willReturn(BigDataVehicleInfoDto.builder().brandCode(BRAND_CODE_TESLA).build());
        /** when */
        bindedCarApiImplRF.getVehicleInfoDtoFromBigData(BRAND_CODE_BMW, vin);
    }

    @Test
    public void test_getBigDataVehicleInfoDto_happy_pass() throws ApiException {
        /** given */
        String vin = "ABCDEFG-HIJKLMN";
        given(bigDataApi.findUserByVinRF(anyString()))
                .willReturn(BigDataVehicleInfoDto.builder().brandCode(BRAND_CODE_TESLA).vin(vin).build());
        /** when */
        BigDataVehicleInfoDto bigDataVehicleInfoDto =
                bindedCarApiImplRF.getVehicleInfoDtoFromBigData(BRAND_CODE_TESLA, vin);
        assertThat(vin).isEqualTo(bigDataVehicleInfoDto.getVin());
        assertThat(BRAND_CODE_TESLA).isEqualTo(bigDataVehicleInfoDto.getBrandCode());
    }


    /* bindCar_tests */


    @Test()
    public void test_bindCar_when_args_error() throws ApiException {
        /** when */
        try {
            bindedCarApiImplRF.bindCar(null, null, null);
        } catch (Exception exception) {
            assertThat(exception).isExactlyInstanceOf(IllegalArgumentException.class);
        }
    }

    @Test()
    public void test_bindCar_when_car_has_been_binded_error() throws ApiException {
        /** given */
        Long userId = 1L;
        String vin = "ABCDEFG-HIJKLMN";
        // stub car has been binded
        given(bindCarService.findVin(anyString())).willReturn(BindInfoPojo.builder().build());
        /** when */
        try {
            bindedCarApiImplRF.bindCar(userId, BRAND_CODE_TESLA, vin);
        } catch (ApiException exception) {
            assertThat(exception.getCode()).isEqualTo(ErrorEnum.CAR_HAS_BEEN_BIND.getValue());
        }
    }

    @Test()
    public void test_bindCar_happy_pass() throws ApiException {
        /** given */
        Long userId = 1L;
        String vin = "ABCDEFG-HIJKLMN";
        Integer brandCode = BRAND_CODE_TESLA;
        // stub no one bind the car
        given(bindCarService.findVin(anyString())).willReturn(null);
        stub_bigdata_vehicle_retruns(brandCode, vin);

        /** when */
        bindedCarApiImplRF.bindCar(userId, brandCode, vin);

        /** then */
        verify(bindCarService, times(1)).saveWeakBind(any());
        verify(mqApi, times(1)).send(anyString());;
    }

    /**
     * return good
     * 
     * @param brandCode
     * @param vin
     */
    void stub_bigdata_vehicle_retruns(Integer brandCode, String vin) {
        given(bigDataApi.findUserByVinRF(vin))
                .willReturn(BigDataVehicleInfoDto.builder().brandCode(brandCode).vin(vin).build());
    }


}
