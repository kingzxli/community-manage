package com.community.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.community.entity.Unit;
import com.community.mapper.UnitMapper;
import com.community.service.UnitService;

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
		QueryWrapper<Unit> wrapper = new QueryWrapper<>(unit);
		return UnitMapper.selectList(wrapper);
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

	
	
}
