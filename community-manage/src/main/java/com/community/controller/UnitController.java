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
import com.community.entity.Result;
import com.community.entity.Unit;
import com.community.service.UnitService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "单元管理")
@RestController
public class UnitController {
	
	
	@Autowired
	private UnitService UnitService;
	
	@ApiOperation(value = "单记录查询")
	@GetMapping("/unit/{unitId}")
	public Result<Unit> getUnitById(@PathVariable(name="unitId") String unitId) {				
		return new Result<>(UnitService.getById(unitId));
	}
	
	@ApiOperation(value = "列表查询")
	@GetMapping("/unit")
	public Result<List<Unit>> list(Unit unit) {		
		List<Unit> list = UnitService.list(unit);
		return new Result<>(list);
	}
	
	@ApiOperation(value = "修改")
	@PutMapping("/unit")
	public Result<?> update(@RequestBody Unit unit) {
	 UnitService.updateById(unit);
	 return Result.SUCCESS;
	}
	
	@ApiOperation(value = "删除")
	@DeleteMapping("/unit/{unitId}")
	public Result<?> delete(@PathVariable(name="unitId") String unitId) {
		UnitService.delete(unitId);
		return Result.SUCCESS;
	}
	
	@ApiOperation(value = "新增")
	@PostMapping("/unit")
	public Result<?> insert(@RequestBody Unit unit) {
		
		unit.setIsDelete(0);
		unit.setCreatedTime(new Date());
		
		UnitService.insert(unit);
		return Result.SUCCESS;
	}

}
