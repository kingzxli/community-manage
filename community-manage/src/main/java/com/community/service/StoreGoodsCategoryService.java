package com.community.service;

import java.util.List;
import com.community.entity.StoreGoodsCategory;

public interface StoreGoodsCategoryService {

	StoreGoodsCategory getById(String storeGoodsCategoryId);

	List<StoreGoodsCategory> list(StoreGoodsCategory storeGoodsCategory);

	void delete(String storeGoodsCategoryId);

	void updateById(StoreGoodsCategory storeGoodsCategory);

	void insert(StoreGoodsCategory storeGoodsCategory);

}
