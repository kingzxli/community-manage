package com.community.controller;

import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.community.entity.Result;
import com.community.util.OssUtil;
import io.swagger.annotations.ApiOperation;

@RestController
public class OssController {
	
	@ApiOperation(value = "上传图片")
	@GetMapping("/upload")
	public Result<String> upload(@RequestParam("file") MultipartFile file) {	
		Assert.notNull(file,"图片不能为空");
		String imgUrl = OssUtil.upload(file,"img");
		return new Result<>(imgUrl);
	}

}
