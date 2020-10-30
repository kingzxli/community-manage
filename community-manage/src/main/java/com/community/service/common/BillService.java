package com.community.service.common;

import java.util.List;

import com.community.entity.common.Bill;
import com.community.entity.common.vo.BillVo;


public interface BillService {

	Bill getById(String billId);

	List<BillVo> selectBill(BillVo bill);

	void delete(String billId);

	void updateById(Bill bill);

	void insert(Bill bill);

}
