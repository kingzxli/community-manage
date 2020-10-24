package com.community.service;

import java.math.BigDecimal;
import java.util.List;
import com.community.entity.Room;
import com.community.entity.vo.RoomVo;

public interface RoomService {

	Room getById(String roomId);

	List<RoomVo> list(RoomVo room);

	void delete(String roomId);

	void updateById(Room room);

	void insert(Room room);
	
	void insertByName(String communityId,String roomName,BigDecimal area,Integer type,String userId);

}
