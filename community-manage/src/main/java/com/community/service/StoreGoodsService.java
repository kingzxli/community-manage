package com.community.service;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.community.entity.StoreGoods;


@Mapper
public interface StoreGoodsService {

	StoreGoods getById(String storeGoodsId);

	List<StoreGoods> list(StoreGoods storeGoods);

	void delete(String storeGoodsId);

	void updateById(StoreGoods storeGoods);

	void insert(StoreGoods storeGoods);
}
