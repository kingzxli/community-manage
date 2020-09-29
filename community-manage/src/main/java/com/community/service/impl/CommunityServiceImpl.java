package com.community.service.impl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.community.entity.Community;
import com.community.mapper.CommunityMapper;
import com.community.service.CommunityService;
import com.community.util.IdMaker;

@Service
public class CommunityServiceImpl implements CommunityService{

	@Autowired
	private CommunityMapper communityMapper;

	@Override
	public Community getById(String communityId) {		
		return communityMapper.selectById(communityId);
	}

	@Override
	public List<Community> list(Community community) {		
		return communityMapper.list(community);
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
	
	@Override
	public String insertByName(String communityName) {
		Community community = new Community();
		community.setCommunityName(communityName);
		QueryWrapper<Community> wrapper = new QueryWrapper<>(community);
		List<Community> list = communityMapper.selectList(wrapper);
				
		if(list == null || list.isEmpty()) {
			community.setId(IdMaker.get());
			community.setIsDelete(0);
			community.setCreatedUser("system");
			community.setCreatedTime(new Date());
			communityMapper.insert(community);
			
			return community.getId();
		}else {
			return list.get(0).getId();
		}
	}
	
}
