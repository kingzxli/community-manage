package com.community.service.impl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.community.entity.Unit;
import com.community.mapper.UnitMapper;
import com.community.service.UnitService;
import com.community.util.IdMaker;

@Service
public class UnitServiceImpl implements UnitService{

	@Autowired
	private UnitMapper UnitMapper;

	@Override
	public Unit getById(String unitId) {		
		return UnitMapper.selectById(unitId);
	}

	@Override
	public List<Unit> list(Unit unit) {			
		return UnitMapper.list(unit);
	}

	@Override
	public void delete(String unitId) {		
		UnitMapper.deleteById(unitId);
	}

	@Override
	public void updateById(Unit unit) {
		UnitMapper.updateById(unit);
	}

	@Override
	public void insert(Unit unit) {
		UnitMapper.insert(unit);
		
	}

	@Override
	public String insertByName(String buildId, String unitName) {
		Unit unit = new Unit();
		unit.setBuildId(buildId);
		unit.setUnit(unitName);
		QueryWrapper<Unit> wrapper = new QueryWrapper<>(unit);
		List<Unit> list = UnitMapper.selectList(wrapper);
		
		if(list == null || list.isEmpty()) {
			unit.setId(IdMaker.get());
			unit.setIsDelete(0);
			unit.setCreatedUser("system");
			unit.setCreatedTime(new Date());
			UnitMapper.insert(unit);
			
			return unit.getId();
		}else {
			return list.get(0).getId();
		}
	}

	
	
}
