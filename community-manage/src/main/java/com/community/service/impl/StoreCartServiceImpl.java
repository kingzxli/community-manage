package com.community.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.community.entity.StoreCart;
import com.community.mapper.StoreCartMapper;
import com.community.service.StoreCartService;


@Service
public class StoreCartServiceImpl implements StoreCartService{

	@Autowired
	private StoreCartMapper storeCartMapper;

	@Override
	public StoreCart getById(String storeCartId) {		
		return storeCartMapper.selectById(storeCartId);
	}

	@Override
	public List<StoreCart> list(StoreCart storeCart) {		
		QueryWrapper<StoreCart> wrapper = new QueryWrapper<>(storeCart);
		return storeCartMapper.selectList(wrapper);
	}

	@Override
	public void delete(String storeCartId) {		
		storeCartMapper.deleteById(storeCartId);
	}

	@Override
	public void updateById(StoreCart storeCart) {
		storeCartMapper.updateById(storeCart);
	}

	@Override
	public void insert(StoreCart storeCart) {
		storeCartMapper.insert(storeCart);		
	}
	
}
