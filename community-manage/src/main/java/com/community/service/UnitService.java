package com.community.service;

import java.util.List;

import com.community.entity.Unit;

public interface UnitService {

	Unit getById(String unitId);

	List<Unit> list(Unit unit);

	void delete(String unitId);

	void updateById(Unit unit);

	void insert(Unit unit);
	
	String insertByName(String buildId,String unitName);

}
