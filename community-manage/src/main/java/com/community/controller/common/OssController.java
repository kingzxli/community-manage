package com.community.controller.common;

import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.community.entity.common.Result;
import com.community.exception.CustomException;
import com.community.util.Assert;
import com.community.util.OssUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "文件上传")
@RestController
public class OssController {
	
	@ApiOperation(value = "上传图片")
	@PostMapping("/upload/images")
	public Result<Map<String, String>> upload(@RequestParam("file") MultipartFile file) {	
		Assert.notNull(file,"文件不能为空");
		try {
			Map<String, String> map = new HashMap<>();
			map.put("url", OssUtil.upload(file,"img"));
			return new Result<>(map);
		} catch (Exception e) {
			throw new CustomException("上传图片出错, " + e.getMessage());
		}
		
	}

}
