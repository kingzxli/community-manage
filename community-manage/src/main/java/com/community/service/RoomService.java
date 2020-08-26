package com.community.service;

import java.util.List;
import com.community.entity.Room;

public interface RoomService {

	Room getById(String roomId);

	List<Room> list(Room room);

	void delete(String roomId);

	void updateById(Room room);

	void insert(Room room);

}
