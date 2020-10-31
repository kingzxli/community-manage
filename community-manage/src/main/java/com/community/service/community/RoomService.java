package com.community.service.community;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import com.community.entity.community.Room;

public interface RoomService {

	Room getById(String roomId);

	List<Room> list(Room room);

	void delete(String roomId);

	void updateById(Room room);

	void insert(Room room);
	
	Integer importRoomData(MultipartFile file);

}
