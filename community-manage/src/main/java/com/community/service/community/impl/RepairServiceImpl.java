package com.community.service.community.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.community.entity.community.Repair;
import com.community.mapper.community.RepairMapper;
import com.community.service.community.RepairService;


@Service
public class RepairServiceImpl implements RepairService{

	@Autowired
	private RepairMapper repairMapper;

	@Override
	public Repair getById(String repairId) {		
		return repairMapper.selectById(repairId);
	}

	@Override
	public List<Repair> list() {		
		return repairMapper.selectList(null);
	}

	@Override
	public void delete(String repairId) {		
		repairMapper.deleteById(repairId);
	}

	@Override
	public void updateById(Repair repair) {
		repairMapper.updateById(repair);
	}

	@Override
	public void insert(Repair repair) {
		repairMapper.insert(repair);		
	}
	
}
