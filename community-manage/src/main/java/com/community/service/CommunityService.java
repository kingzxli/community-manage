package com.community.service;

import java.util.List;
import com.community.entity.Community;


public interface CommunityService {

	Community getById(String communityId);

	List<Community> list(Community community);

	void delete(String communityId);

	void updateById(Community community);

	void insert(Community community);
	
	String insertByName(String communityName);

}
