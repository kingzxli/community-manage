package com.community.controller;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.community.entity.Page;
import com.community.entity.Result;
import com.community.entity.Room;
import com.community.entity.vo.RoomVo;
import com.community.service.RoomService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "房间管理")
@RestController
public class RoomController {
	
	@Autowired
	private RoomService roomService;
	
	@ApiOperation(value = "列表查询")
	@GetMapping("/room")
	public Result<List<RoomVo>> list(Page page,RoomVo room) {	
		page.paging();
		List<RoomVo> list = roomService.list(room);
		return new Result<>(list).total(new PageInfo<RoomVo>(list).getTotal());
	}
	
	@ApiOperation(value = "单记录查询")
	@GetMapping("/room/{roomId}")
	public Result<Room> getroomById(@PathVariable(name="roomId") String roomId) {				
		return new Result<>(roomService.getById(roomId));
	}
	
	@ApiOperation(value = "修改")
	@PutMapping("/room")
	public Result<?> update(@RequestBody Room room) {
	 roomService.updateById(room);
	 return Result.SUCCESS;
	}
	
	@ApiOperation(value = "删除")
	@DeleteMapping("/room/{roomId}")
	public Result<?> delete(@PathVariable(name="roomId") String roomId) {
		roomService.delete(roomId);
		return Result.SUCCESS;
	}
	
	@ApiOperation(value = "新增")
	@PostMapping("/room")
	public Result<?> insert(@RequestBody Room room) {
		
		room.setIsDelete(0);
		room.setCreatedTime(new Date());
		
		roomService.insert(room);
		return Result.SUCCESS;
	}

}
