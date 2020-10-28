package com.community.service;

import java.util.List;
import com.community.entity.StoreCart;

public interface StoreCartService {

	StoreCart getById(String storeCartId);

	List<StoreCart> list(StoreCart storeCart);

	void delete(String storeCartId);

	void updateById(StoreCart storeAddress);

	void insert(StoreCart storeAddress);

}
