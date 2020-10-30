package com.community.controller.store;

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
import com.community.entity.store.StoreAddress;
import com.community.service.store.StoreAddressService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "地址管理")
@RestController
public class StoreAddressController {
	
	@Autowired
	private StoreAddressService storeAddressService;
	
	@ApiOperation(value = "列表查询")
	@GetMapping("/storeAddress")
	public Result<List<StoreAddress>> list(Page page,StoreAddress storeAddress) {	
		page.paging();			
		List<StoreAddress> list = storeAddressService.list(storeAddress);
		return new Result<>(list).total(new PageInfo<StoreAddress>(list).getTotal());
	}
	
	@ApiOperation(value = "单记录查询")
	@GetMapping("/storeAddress/{storeAddressId}")
	public Result<StoreAddress> getById(@PathVariable(name="storeAddressId") String storeAddressId) {				
		return new Result<>(storeAddressService.getById(storeAddressId));
	}
	
	@ApiOperation(value = "修改")
	@PutMapping("/storeAddress")
	public Result<?> update(@RequestBody StoreAddress storeAddress) {
		storeAddressService.updateById(storeAddress);
	 return Result.SUCCESS;
	}
	
	@ApiOperation(value = "删除")
	@DeleteMapping("/storeAddress/{storeAddressId}")
	public Result<?> delete(@PathVariable(name="storeAddressId") String storeAddressId) {
		storeAddressService.delete(storeAddressId);
		return Result.SUCCESS;
	}
	
	@ApiOperation(value = "新增")
	@PostMapping("/storeAddress")
	public Result<?> insert(@RequestBody StoreAddress storeAddress) {	
		storeAddress.setIsDelete(0);
		storeAddress.setCreatedTime(new Date());
		storeAddressService.insert(storeAddress);
		return Result.SUCCESS;
	}

}
