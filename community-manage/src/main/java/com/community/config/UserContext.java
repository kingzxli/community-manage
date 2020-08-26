package com.community.config;

import com.community.entity.User;

/**
 * 用户上下文
 */
public class UserContext {
	private static final ThreadLocal<User> CONTEXT = new InheritableThreadLocal<>();

	/**
	 * 设置用户信息
	 */
	public static void set(User user) {
		CONTEXT.remove();
		CONTEXT.set(user);
	}

	/**
	 * 获取用户信息
	 */
	public static User get() {
		return CONTEXT.get();
	}

	/**
	 * 移除用户信息
	 */
	public static void remove() {
		CONTEXT.remove();
	}
	
	public static void clear() {
		CONTEXT.remove();
	}
}
