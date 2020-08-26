package com.community.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.community.entity.Community;
import com.community.mapper.CommunityMapper;
import com.community.service.CommunityService;

@Service
public class CommunityServiceImpl implements CommunityService{

	@Autowired
	private CommunityMapper communityMapper;

	@Override
	public Community getById(String communityId) {		
		return communityMapper.selectById(communityId);
	}

	@Override
	public List<Community> list() {		
		return communityMapper.selectList(null);
	}

	@Override
	public void delete(String communityId) {		
		communityMapper.deleteById(communityId);
	}

	@Override
	public void updateById(Community community) {
		communityMapper.updateById(community);
	}

	@Override
	public void insert(Community community) {
		communityMapper.insert(community);
		
	}

	
	
}
