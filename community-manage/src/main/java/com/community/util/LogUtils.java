package com.community.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.alibaba.fastjson.JSONObject;

public class LogUtils {
	
	static final Logger LOG = LoggerFactory.getLogger(LogUtils.class);
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public static void printLog(String msg) {
		JSONObject json = new JSONObject();
		json.put("visitTime", sdf.format(new Date()));
		json.put("message", msg);		
		
		LOG.info("\r\n" + json.toJSONString());
	}
	
	public static void printException(String msg, Exception e) {
		try {
			JSONObject json = new JSONObject();		
			json.put("visitTime", sdf.format(new Date()));
			StringWriter stringWriter = new StringWriter();
			e.printStackTrace(new PrintWriter(stringWriter));
			json.put("message", stringWriter.toString());
			
			LOG.info("\r\n" + msg + json.toJSONString());
		} catch(Exception ex) {
			printLog("输出异常信息失败, " + e.getMessage());
		}
	}
	
}
