package com.community.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

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
import com.community.entity.StoreCart;
import com.community.service.StoreCartService;
import com.community.util.IdMaker;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "购物车管理")
@RestController
public class StoreCartController {
	
	@Autowired
	private StoreCartService storeCartService;
	
	@ApiOperation(value = "列表查询")
	@GetMapping("/storeCart")
	public Result<List<StoreCart>> list(Page page,StoreCart storeCart) {	
		page.paging();			
		List<StoreCart> list = storeCartService.list(storeCart);
		return new Result<>(list).total(new PageInfo<StoreCart>(list).getTotal());
	}
	
	@ApiOperation(value = "单记录查询")
	@GetMapping("/storeCart/{storeCartId}")
	public Result<StoreCart> getById(@PathVariable(name="storeCartId") String storeCartId) {				
		return new Result<>(storeCartService.getById(storeCartId));
	}
	
	@ApiOperation(value = "修改")
	@PutMapping("/storeCart")
	public Result<?> update(@RequestBody StoreCart storeCart) {
		storeCartService.updateById(storeCart);
	 return Result.SUCCESS;
	}
	
	@ApiOperation(value = "删除")
	@DeleteMapping("/storeCart/{storeCartId}")
	public Result<?> delete(@PathVariable(name="storeCartId") String storeCartId) {
		storeCartService.delete(storeCartId);
		return Result.SUCCESS;
	}
	
	@ApiOperation(value = "新增")
	@PostMapping("/storeCart")
	public Result<?> insert(@Valid @RequestBody StoreCart storeCart) {	
		storeCart.setId(IdMaker.get());
		storeCart.setIsDelete(0);
		storeCart.setCreatedTime(new Date());
		storeCartService.insert(storeCart);
		return Result.SUCCESS;
	}

}
