package com.community.controller.store;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.community.entity.common.Page;
import com.community.entity.common.Result;
import com.community.entity.store.BaseCity;
import com.community.service.store.BaseCityService;
import com.community.util.Assert;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "区域管理")
@RestController
public class BaseCityController {
	
	@Autowired
	private BaseCityService baseCityService;
	
	@ApiOperation(value = "列表查询")
	@GetMapping("/baseCity")
	public Result<List<BaseCity>> list(Page page,BaseCity baseCity) {	
		page.paging();	
		Assert.notNull(baseCity.getCityLevel(), "级别不能为空");
		List<BaseCity> list = baseCityService.list(baseCity);
		return new Result<>(list).total(new PageInfo<BaseCity>(list).getTotal());
	}
	
	@ApiOperation(value = "单记录查询")
	@GetMapping("/baseCity/{baseCityId}")
	public Result<BaseCity> getById(@PathVariable(name="baseCityId") String baseCityId) {				
		return new Result<>(baseCityService.getById(baseCityId));
	}
	
//	@ApiOperation(value = "修改")
//	@PutMapping("/baseCity")
//	public Result<?> update(@RequestBody BaseCity BaseCity) {
//		baseCityService.updateById(BaseCity);
//	 return Result.SUCCESS;
//	}
//	
//	@ApiOperation(value = "删除")
//	@DeleteMapping("/baseCity/{baseCityId}")
//	public Result<?> delete(@PathVariable(name="baseCityId") String baseCityId) {
//		baseCityService.delete(baseCityId);
//		return Result.SUCCESS;
//	}
//	
//	@ApiOperation(value = "新增")
//	@PostMapping("/baseCity")
//	public Result<?> insert(@RequestBody BaseCity baseCity) {				
//		baseCityService.insert(baseCity);
//		return Result.SUCCESS;
//	}

}
