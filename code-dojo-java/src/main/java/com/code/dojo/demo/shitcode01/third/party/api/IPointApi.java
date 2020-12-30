package com.code.dojo.demo.shitcode01.third.party.api;

import java.util.List;
import com.code.dojo.demo.shitcode01.service.pojo.BindInfoPojo;

public interface IPointApi {
	void addPoint(String userId, Integer brandCode, List<BindInfoPojo> bindRecord);
}
