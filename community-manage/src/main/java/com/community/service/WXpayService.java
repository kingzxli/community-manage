package com.community.service;

import java.math.BigDecimal;

public interface WXpayService {

	void wxpay(BigDecimal totalFee,String openId);

	

}
