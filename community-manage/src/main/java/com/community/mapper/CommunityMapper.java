package com.community.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.community.entity.Community;

@Mapper
public interface CommunityMapper extends BaseMapper<Community>{

	List<Community> list(Community community);

}
