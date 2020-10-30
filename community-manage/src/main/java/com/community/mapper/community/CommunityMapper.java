package com.community.mapper.community;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.community.entity.community.Community;

@Mapper
public interface CommunityMapper extends BaseMapper<Community>{

	List<Community> list(Community community);

}
