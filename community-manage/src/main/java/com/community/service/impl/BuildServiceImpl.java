package com.community.service.impl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.community.entity.Build;
import com.community.mapper.BuildMapper;
import com.community.service.BuildService;
import com.community.util.IdMaker;

@Service
public class BuildServiceImpl implements BuildService{

	@Autowired
	private BuildMapper buildMapper;

	@Override
	public Build getById(String buildId) {		
		return buildMapper.selectById(buildId);
	}

	@Override
	public List<Build> list(Build build) {			
		return buildMapper.list(build);
	}

	@Override
	public void delete(String buildId) {		
		buildMapper.deleteById(buildId);
	}

	@Override
	public void updateById(Build build) {
		buildMapper.updateById(build);
	}

	@Override
	public void insert(Build build) {
		buildMapper.insert(build);
		
	}

	@Override
	public String insertByName(String communityId,String buildName) {
		Build build = new Build();
		build.setCommunityId(communityId);
		build.setBuildName(buildName);
		QueryWrapper<Build> wrapper = new QueryWrapper<>(build);
		List<Build> list = buildMapper.selectList(wrapper);
		
		if(list == null || list.isEmpty()) {
			build.setId(IdMaker.get());
			build.setIsDelete(0);
			build.setCreatedUser("system");
			build.setCreatedTime(new Date());
			buildMapper.insert(build);
			
			return build.getId();
		}else {
			return list.get(0).getId();
		}
	}
	
	
}
