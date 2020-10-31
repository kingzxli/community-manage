package com.community.service.common;

import java.util.List;
import com.community.entity.common.User;

public interface UserService {

	User getById(String userId);

	List<User> list();

	void delete(String userId);

	void updateById(User user);

	void insert(User user);

}
