package com.community.controller.community;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.community.entity.common.Page;
import com.community.entity.common.Result;
import com.community.entity.community.Room;
import com.community.service.community.RoomService;
import com.community.util.UserContext;
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
	public Result<List<Room>> list(Page page,Room room) {	
		page.paging();			
		List<Room> list = roomService.list(room);		
		return new Result<>(list).total(new PageInfo<Room>(list).getTotal());
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
		room.setCreatedUser(UserContext.get().getUserName());
		roomService.insert(room);
		return Result.SUCCESS;
	}
	
	/**
	 * 导入
	 * @param file
	 */
	@PostMapping("/import/room")
	public Result<Integer> importRoomDate(@RequestParam("file") MultipartFile file) {
		Integer num =roomService.importRoomData(file);
		return new Result<Integer>(num);
	}

}
