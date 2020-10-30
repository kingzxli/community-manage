package com.community.service.store.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.community.entity.store.BaseCity;
import com.community.mapper.store.BaseCityMapper;
import com.community.service.store.BaseCityService;



@Service
public class BaseCityServiceImpl implements BaseCityService{

	@Autowired
	private BaseCityMapper baseCityMapper;

	@Override
	public BaseCity getById(String baseCityId) {		
		return baseCityMapper.selectById(baseCityId);
	}

	@Override
	public List<BaseCity> list(BaseCity baseCity) {	
		QueryWrapper<BaseCity> wrapper = new QueryWrapper<>(baseCity);
		return baseCityMapper.selectList(wrapper);
	}

	@Override
	public void delete(String baseCityId) {		
		baseCityMapper.deleteById(baseCityId);
	}

	@Override
	public void updateById(BaseCity baseCity) {
		baseCityMapper.updateById(baseCity);
	}

	@Override
	public void insert(BaseCity baseCity) {
		baseCityMapper.insert(baseCity);		
	}
	
}
