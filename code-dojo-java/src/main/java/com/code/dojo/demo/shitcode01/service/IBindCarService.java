package com.code.dojo.demo.shitcode01.service;

import java.util.List;
import com.code.dojo.demo.shitcode01.service.pojo.BindInfoPojo;

public interface IBindCarService {

    BindInfoPojo findVin(String vin);

    List<BindInfoPojo> findBindRecordBy(String vin, Long userId, Integer brandCode);

    int saveWeakBind(BindInfoPojo weakBinded);
}
