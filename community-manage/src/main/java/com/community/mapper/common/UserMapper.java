package com.community.mapper.common;


import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.community.entity.common.User;

@Mapper
public interface UserMapper extends BaseMapper<User>{

	/**
	 * 根据OpenId和phone查询
	 * @param user
	 * @return
	 */
	User selectOne(User user);

}
