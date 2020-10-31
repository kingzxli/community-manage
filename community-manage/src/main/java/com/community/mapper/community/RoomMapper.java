package com.community.mapper.community;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.community.entity.community.Room;


@Mapper
public interface RoomMapper extends BaseMapper<Room>{

	List<Room> roomList(Room room);
}
