package com.community.controller;

import java.math.BigDecimal;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.community.service.WXpayService;
import io.swagger.annotations.Api;

@Api(tags = "微信支付")
@RestController
public class WXpayController {
	
	@Autowired
	private WXpayService wxpayService;
	
	@GetMapping("/wxpay")
	public Map<String,String> wxpay(BigDecimal totalFee,String openId) {
				
		return wxpayService.wxpay(totalFee,openId);
		
	}

}
