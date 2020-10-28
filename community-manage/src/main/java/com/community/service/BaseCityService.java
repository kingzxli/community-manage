package com.community.service;

import java.util.List;
import com.community.entity.BaseCity;

public interface BaseCityService {

	BaseCity getById(String baseCityId);

	List<BaseCity> list(BaseCity baseCity);

	void delete(String baseCityId);

	void updateById(BaseCity baseCity);

	void insert(BaseCity baseCity);

}
