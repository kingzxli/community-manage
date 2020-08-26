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
import com.community.entity.Build;
import com.community.entity.Result;
import com.community.service.BuildService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "楼栋管理")
@RestController
public class BuildController {
	
	@Autowired
	private BuildService buildService;
	
	@ApiOperation(value = "列表查询")
	@GetMapping("/build")
	public Result<List<Build>> list(Build build) {		
		List<Build> list = buildService.list(build);
		return new Result<>(list);
	}
	
	@ApiOperation(value = "单记录查询")
	@GetMapping("/build/{buildId}")
	public Result<Build> getUserById(@PathVariable(name="buildId") String buildId) {				
		return new Result<>(buildService.getById(buildId));
	}
	
	@ApiOperation(value = "修改")
	@PutMapping("/build")
	public Result<?> update(@RequestBody Build build) {
		buildService.updateById(build);
	 return Result.SUCCESS;
	}
	
	@ApiOperation(value = "删除")
	@DeleteMapping("/build/{buildId}")
	public Result<?> delete(@PathVariable(name="buildId") String buildId) {
		buildService.delete(buildId);
		return Result.SUCCESS;
	}
	
	@ApiOperation(value = "新增")
	@PostMapping("/build")
	public Result<?> insert(@RequestBody Build build) {
		
		build.setIsDelete(0);
		build.setCreatedTime(new Date());
		
		buildService.insert(build);
		return Result.SUCCESS;
	}

}
