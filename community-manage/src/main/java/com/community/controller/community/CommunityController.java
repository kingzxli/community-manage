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
import org.springframework.web.bind.annotation.RestController;

import com.community.entity.common.Page;
import com.community.entity.common.Result;
import com.community.entity.community.Community;
import com.community.service.community.CommunityService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "楼盘管理")
@RestController
public class CommunityController {
	
	@Autowired
	private CommunityService communityService;
	
	@ApiOperation(value = "列表查询(level必填 1:楼盘2:楼栋3:单元)")
	@GetMapping("/community")
	public Result<List<Community>> list(Page page,Community community) {
		page.paging();	
		List<Community> list = communityService.list(community);
		return new Result<>(list).total(new PageInfo<Community>(list).getTotal());
	}
	
	@ApiOperation(value = "单记录查询")
	@GetMapping("/community/{communityId}")
	public Result<Community> getUserById(@PathVariable(name="communityId") String communityId) {				
		return new Result<>(communityService.getById(communityId));
	}
	
	@ApiOperation(value = "修改")
	@PutMapping("/community")
	public Result<?> update(@RequestBody Community community) {
		communityService.updateById(community);
	 return Result.SUCCESS;
	}
	
	@ApiOperation(value = "删除")
	@DeleteMapping("/community/{communityId}")
	public Result<?> delete(@PathVariable(name="communityId") String communityId) {
		communityService.delete(communityId);
		return Result.SUCCESS;
	}
	
	@ApiOperation(value = "新增")
	@PostMapping("/community")
	public Result<?> insert(@RequestBody Community community) {
		
		community.setIsDelete(0);
		community.setCreatedTime(new Date());
		
		communityService.insert(community);
		return Result.SUCCESS;
	}

}
