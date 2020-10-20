package com.code.dojo.demo.shitcode01.service;

import java.util.List;

import com.code.dojo.demo.shitcode01.pojo.BindInfo;

public interface IBindCarService {

	BindInfo findVin(String vin);

	List<BindInfo> findBindRecordBy(String vin, String userId, Integer brandCode);

	int saveWeakBind(BindInfo weakBinded);
}
