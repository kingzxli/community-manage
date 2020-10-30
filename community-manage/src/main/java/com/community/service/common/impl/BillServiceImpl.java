package com.community.service.common.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.community.entity.common.Bill;
import com.community.entity.common.vo.BillVo;
import com.community.mapper.common.BillMapper;
import com.community.service.common.BillService;

@Service
public class BillServiceImpl implements BillService{

	@Autowired
	private BillMapper billMapper;

	@Override
	public Bill getById(String billId) {		
		return billMapper.selectById(billId);
	}

	@Override
	public List<BillVo> selectBill(BillVo bill) {	
		return billMapper.selectBill(bill);
	}

	@Override
	public void delete(String billId) {		
		billMapper.deleteById(billId);
	}

	@Override
	public void updateById(Bill bill) {
		billMapper.updateById(bill);
	}

	@Override
	public void insert(Bill bill) {
		billMapper.insert(bill);
		
	}

	
	
}
