package com.community.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtils {
	
	public static void debug(String msg) {
		Logger logger = LoggerFactory.getLogger(LogUtils.class);
		logger.debug(msg);
	}
	
	public static void info(String msg) {
		Logger logger = LoggerFactory.getLogger(LogUtils.class);
		logger.info(msg);
	}
	
	public static void warn(String msg) {
		Logger logger = LoggerFactory.getLogger(LogUtils.class);
		logger.warn(msg);
	}
	
	public static void error(String msg) {
		Logger logger = LoggerFactory.getLogger(LogUtils.class);
		logger.error(msg);
	}

	
}
