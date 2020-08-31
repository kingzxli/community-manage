package com.community.service;

import java.math.BigDecimal;
import java.util.Map;

public interface WXpayService {

	Map<String,String> wxpay(BigDecimal totalFee,String openId);

	

}
