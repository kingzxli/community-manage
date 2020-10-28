package com.community.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.community.entity.StoreAddress;
import com.community.mapper.StoreAddressMapper;
import com.community.service.StoreAddressService;


@Service
public class StoreAddressServiceImpl implements StoreAddressService{

	@Autowired
	private StoreAddressMapper storeAddressMapper;

	@Override
	public StoreAddress getById(String storeAddressId) {		
		return storeAddressMapper.selectById(storeAddressId);
	}

	@Override
	public List<StoreAddress> list(StoreAddress storeAddress) {	
		QueryWrapper<StoreAddress> wrapper = new QueryWrapper<>();
		return storeAddressMapper.selectList(wrapper);
	}

	@Override
	public void delete(String storeAddressId) {		
		storeAddressMapper.deleteById(storeAddressId);
	}

	@Override
	public void updateById(StoreAddress storeAddress) {
		storeAddressMapper.updateById(storeAddress);
	}

	@Override
	public void insert(StoreAddress storeAddress) {
		storeAddressMapper.insert(storeAddress);		
	}
	
}
