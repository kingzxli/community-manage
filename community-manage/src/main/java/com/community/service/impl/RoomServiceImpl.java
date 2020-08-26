package com.community.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.community.entity.Room;
import com.community.mapper.RoomMapper;
import com.community.service.RoomService;


@Service
public class RoomServiceImpl implements RoomService{

	@Autowired
	private RoomMapper roomMapper;

	@Override
	public Room getById(String roomId) {		
		return roomMapper.selectById(roomId);
	}

	@Override
	public List<Room> list(Room room) {	
		QueryWrapper<Room> wrapper = new QueryWrapper<>(room);
		return roomMapper.selectList(wrapper);
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

	
	
}
