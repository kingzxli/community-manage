package com.community.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.community.entity.Room;
import com.community.entity.vo.RoomVo;
import com.community.mapper.RoomMapper;
import com.community.service.RoomService;
import com.community.util.IdMaker;


@Service
public class RoomServiceImpl implements RoomService{

	@Autowired
	private RoomMapper roomMapper;

	@Override
	public Room getById(String roomId) {		
		return roomMapper.selectById(roomId);
	}

	@Override
	public List<RoomVo> list(RoomVo room) {			
		List<RoomVo> list = roomMapper.roomList(room);
		
		/**
		 * 物业服务费：0.5*area
		 * 公共水电费 120/年
		 * 生活垃圾处理费 96/年
		 * 车位费 360/年
		 */
		for(RoomVo dbRoom : list) {
			BigDecimal billAmount = new BigDecimal(0);
			//1:住宅,2:车库,3:商铺
			Integer type = dbRoom.getType();
			BigDecimal area = dbRoom.getArea();
			
			if(type == 3) {
				billAmount = new BigDecimal(360);
			}else {
				billAmount = area.multiply(new BigDecimal(0.5));
				billAmount = billAmount.add(new BigDecimal(120 + 96));
			}
			
			dbRoom.setBillAmount(billAmount);
		}
		
		return list;
	}

	@Override
	public void delete(String roomId) {		
		roomMapper.deleteById(roomId);
	}

	@Override
	public void updateById(Room room) {
		roomMapper.updateById(room);
	}

	@Override
	public void insert(Room room) {
		roomMapper.insert(room);
		
	}

	@Override
	public void insertByName(String unitId, String roomName,BigDecimal area,Integer type, String userId) {
		Room room = new Room();
		room.setUnitId(unitId);
		room.setRoom(roomName);
		QueryWrapper<Room> wrapper = new QueryWrapper<>(room);
		List<Room> list = roomMapper.selectList(wrapper);
		
		room.setUserId(userId);
		room.setType(type);
		if(list == null || list.isEmpty()) {
			room.setArea(area);
			room.setId(IdMaker.get());
			room.setIsDelete(0);
			room.setCreatedUser("system");
			room.setCreatedTime(new Date());
			roomMapper.insert(room);						
		}else {
			room.setId(list.get(0).getId());			
			roomMapper.updateById(room);			
		}
	}

	
	
}
