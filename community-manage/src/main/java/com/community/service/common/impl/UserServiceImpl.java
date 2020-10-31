package com.community.service.common.impl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.community.entity.common.User;
import com.community.mapper.common.UserMapper;
import com.community.service.common.UserService;
import com.community.util.IdMaker;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserMapper userMapper;

	@Override
	public User getById(String userId) {		
		return userMapper.selectById(userId);
	}

	@Override
	public List<User> list() {		
		return userMapper.selectList(null);
	}

	@Override
	public void delete(String userId) {		
		userMapper.deleteById(userId);
	}

	@Override
	public void updateById(User user) {
		userMapper.updateById(user);
	}

	@Override
	public void insert(User user) {
		userMapper.insert(user);		
	}
	
	public String insertByPhone(String phone,String userName) {
		User user = new User();
		user.setPhone(phone);		
		User dbUser = userMapper.selectOne(user);
		
		user.setUserName(userName);		
		if(dbUser == null) {
			user.setId(IdMaker.get());
			user.setRoleId(2);
			user.setIsDelete(0);
			user.setCreatedUser("system");
			user.setCreatedTime(new Date());
			userMapper.insert(user);					
		}else {
			user.setId(dbUser.getId());
			userMapper.updateById(user);
		}
		
		return user.getId();
	}
	
}
