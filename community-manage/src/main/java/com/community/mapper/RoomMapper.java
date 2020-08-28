package com.community.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.community.entity.Room;
import com.community.entity.vo.RoomVo;


@Mapper
public interface RoomMapper extends BaseMapper<Room>{

	List<RoomVo> roomList(RoomVo room);
}
