package com.code.dojo.demo.shitcode01.third.party.api;

import java.util.List;

import com.code.dojo.demo.shitcode01.pojo.BindInfo;

public interface IPointApi {
	void addPoint(String userId, Integer brandCode, List<BindInfo> bindRecord);
}
