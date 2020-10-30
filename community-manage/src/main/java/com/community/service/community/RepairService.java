package com.community.service.community;

import java.util.List;

import com.community.entity.community.Repair;

public interface RepairService {

	Repair getById(String repairId);

	List<Repair> list();

	void delete(String repairId);

	void updateById(Repair repair);

	void insert(Repair repair);

}
