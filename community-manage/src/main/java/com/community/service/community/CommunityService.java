package com.community.service.community;

import java.util.List;

import com.community.entity.community.Community;


public interface CommunityService {

	Community getById(String communityId);

	List<Community> list(Community community);

	void delete(String communityId);

	void updateById(Community community);

	void insert(Community community);
	
	String insertByName(String communityName,Integer communityLevel,String parentId);

}
