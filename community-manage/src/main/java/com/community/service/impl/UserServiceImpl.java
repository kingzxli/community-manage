package com.community.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.community.entity.User;
import com.community.mapper.UserMapper;
import com.community.service.UserService;

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

	
	
}
