package com.community.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.community.entity.User;

@Mapper
public interface UserMapper extends BaseMapper<User>{

}
