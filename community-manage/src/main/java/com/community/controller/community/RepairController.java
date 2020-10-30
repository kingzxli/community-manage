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
import com.community.entity.community.Repair;
import com.community.service.community.RepairService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "报修管理")
@RestController
public class RepairController {
	
	@Autowired
	private RepairService repairService;
	
	@ApiOperation(value = "单记录查询")
	@GetMapping("/repair/{repairId}")
	public Result<Repair> getUserById(@PathVariable(name="repairId") String repairId) {				
		return new Result<>(repairService.getById(repairId));
	}
	
	@ApiOperation(value = "列表查询")
	@GetMapping("/repair")
	public Result<List<Repair>> list(Page page) {	
		page.paging();			
		List<Repair> list = repairService.list();
		return new Result<>(list).total(new PageInfo<Repair>(list).getTotal());
	}
	
	@ApiOperation(value = "修改")
	@PutMapping("/repair")
	public Result<?> update(@RequestBody Repair repair) {
		repairService.updateById(repair);
	 return Result.SUCCESS;
	}
	
	@ApiOperation(value = "删除")
	@DeleteMapping("/repair/{repairId}")
	public Result<?> delete(@PathVariable(name="repairId") String repairId) {
		repairService.delete(repairId);
		return Result.SUCCESS;
	}
	
	@ApiOperation(value = "新增")
	@PostMapping("/repair")
	public Result<?> insert(@RequestBody Repair repair) {
		
		repair.setIsDelete(0);
		repair.setCreatedTime(new Date());
		repairService.insert(repair);
		return Result.SUCCESS;
	}

}
