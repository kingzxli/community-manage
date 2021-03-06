package com.community.controller.common;

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
import com.community.entity.common.Page;
import com.community.entity.common.Result;
import com.community.entity.common.User;
import com.community.service.common.UserService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "用户管理")
@RestController
public class UserController {
	
	
	@Autowired
	private UserService userService;

	@ApiOperation(value = "列表查询")
	@GetMapping("/user")
	public Result<List<User>> list(Page page) {	
		page.paging();			
		List<User> list = userService.list();
		return new Result<>(list).total(new PageInfo<User>(list).getTotal());
	}
	
	@ApiOperation(value = "单记录查询")
	@GetMapping("/user/{userId}")
	public Result<User> getUserById(@PathVariable(name="userId") String userId) {				
		return new Result<>(userService.getById(userId));
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

}
