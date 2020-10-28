package com.community.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.community.entity.StoreGoodsCategory;
import com.community.mapper.StoreGoodsCategoryMapper;
import com.community.service.StoreGoodsCategoryService;


@Service
public class StoreGoodsCategoryServiceImpl implements StoreGoodsCategoryService{

	@Autowired
	private StoreGoodsCategoryMapper storeGoodsCategoryMapper;

	@Override
	public StoreGoodsCategory getById(String storeGoodsCategoryId) {		
		return storeGoodsCategoryMapper.selectById(storeGoodsCategoryId);
	}

	@Override
	public List<StoreGoodsCategory> list(StoreGoodsCategory storeGoodsCategory) {	
		QueryWrapper<StoreGoodsCategory> wrapper = new QueryWrapper<>();
		return storeGoodsCategoryMapper.selectList(wrapper);
	}

	@Override
	public void delete(String storeGoodsCategoryId) {		
		storeGoodsCategoryMapper.deleteById(storeGoodsCategoryId);
	}

	@Override
	public void updateById(StoreGoodsCategory storeGoodsCategory) {
		storeGoodsCategoryMapper.updateById(storeGoodsCategory);
	}

	@Override
	public void insert(StoreGoodsCategory storeGoodsCategory) {
		storeGoodsCategoryMapper.insert(storeGoodsCategory);		
	}
	
}
