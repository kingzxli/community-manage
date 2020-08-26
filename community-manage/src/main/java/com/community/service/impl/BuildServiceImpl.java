package com.community.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.community.entity.Build;
import com.community.mapper.BuildMapper;
import com.community.service.BuildService;

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
		QueryWrapper<Build> wrapper = new QueryWrapper<>(build);
		return buildMapper.selectList(wrapper);
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

	
	
}
