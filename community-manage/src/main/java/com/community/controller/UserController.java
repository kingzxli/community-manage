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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.community.entity.Result;
import com.community.entity.User;
import com.community.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "用户管理")
@RestController
public class UserController {
	
	
	@Autowired
	private UserService userService;
	
	@ApiOperation(value = "单记录查询")
	@GetMapping("/user/{userId}")
	public Result<User> getUserById(@PathVariable(name="userId") String userId) {				
		return new Result<>(userService.getById(userId));
	}
	
	@ApiOperation(value = "列表查询")
	@GetMapping("/user")
	public Result<List<User>> list() {		
		List<User> list = userService.list();
		return new Result<>(list);
	}
	
	@ApiOperation(value = "修改")
	@PutMapping("/user")
	public Result<?> update(@RequestBody User user) {
	 userService.updateById(user);
	 return Result.SUCCESS;
	}
	
	@ApiOperation(value = "删除")
	@DeleteMapping("/user/{userId}")
	public Result<?> delete(@PathVariable(name="userId") String userId) {
		userService.delete(userId);
		return Result.SUCCESS;
	}
	
	@ApiOperation(value = "新增")
	@PostMapping("/user")
	public Result<?> insert(@RequestBody User user) {
		
		user.setIsDelete(0);
		user.setCreatedTime(new Date());
		
		userService.insert(user);
		return Result.SUCCESS;
	}
	
	/**
	 * 导入
	 * @param file
	 */
	@PostMapping("/import")
	public Result<Integer> importUserDate(@RequestParam("file") MultipartFile file) {
		Integer num =userService.importUserData(file);
		return new Result<Integer>(num);
	}

}
