package com.community.service;

import java.util.List;
import com.community.entity.StoreAddress;

public interface StoreAddressService {

	StoreAddress getById(String storeAddressId);

	List<StoreAddress> list(StoreAddress storeAddress);

	void delete(String storeAddressId);

	void updateById(StoreAddress storeAddress);

	void insert(StoreAddress storeAddress);

}
