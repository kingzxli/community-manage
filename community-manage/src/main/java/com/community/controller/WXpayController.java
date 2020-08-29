package com.community.controller;

import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.community.entity.Result;
import com.community.service.WXpayService;
import io.swagger.annotations.Api;

@Api(tags = "微信支付")
@RestController
public class WXpayController {
	
	@Autowired
	private WXpayService wxpayService;
	
	@PostMapping("/wxpay")
	public Result<?> wxpay(BigDecimal totalFee,String openId) {
		wxpayService.wxpay(totalFee,openId);		
		return Result.SUCCESS;
		
	}

}
