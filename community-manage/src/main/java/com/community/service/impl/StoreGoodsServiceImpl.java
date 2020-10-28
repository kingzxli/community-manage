package com.community.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.community.entity.StoreGoods;
import com.community.mapper.StoreGoodsMapper;
import com.community.service.StoreGoodsService;


@Service
public class StoreGoodsServiceImpl implements StoreGoodsService{

	@Autowired
	private StoreGoodsMapper storeGoodsMapper;

	@Override
	public StoreGoods getById(String storeGoodsId) {		
		return storeGoodsMapper.selectById(storeGoodsId);
	}

	@Override
	public List<StoreGoods> list(StoreGoods storeGoods) {	
		QueryWrapper<StoreGoods> wrapper = new QueryWrapper<>();
		return storeGoodsMapper.selectList(wrapper);
	}

	@Override
	public void delete(String storeGoodsId) {		
		storeGoodsMapper.deleteById(storeGoodsId);
	}

	@Override
	public void updateById(StoreGoods storeGoods) {
		storeGoodsMapper.updateById(storeGoods);
	}

	@Override
	public void insert(StoreGoods storeGoods) {
		storeGoodsMapper.insert(storeGoods);		
	}
	
}
