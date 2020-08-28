package com.community.service;

import java.util.List;
import com.community.entity.Build;

public interface BuildService {

	Build getById(String buildId);

	List<Build> list(Build build);

	void delete(String buildId);

	void updateById(Build build);

	void insert(Build build);
	
    String insertByName(String communityId,String buildName);

}
