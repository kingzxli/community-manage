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
import com.community.entity.store.StoreGoods;
import com.community.service.store.StoreGoodsService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "商品管理")
@RestController
public class StoreGoodsController {
	
	@Autowired
	private StoreGoodsService storeGoodsService;
	
	@ApiOperation(value = "列表查询")
	@GetMapping("/storeGoods")
	public Result<List<StoreGoods>> list(Page page,StoreGoods storeGoods) {	
		page.paging();			
		List<StoreGoods> list = storeGoodsService.list(storeGoods);
		return new Result<>(list).total(new PageInfo<StoreGoods>(list).getTotal());
	}
	
	@ApiOperation(value = "单记录查询")
	@GetMapping("/storeGoods/{storeGoodsId}")
	public Result<StoreGoods> getById(@PathVariable(name="storeGoodsId") String storeGoodsId) {				
		return new Result<>(storeGoodsService.getById(storeGoodsId));
	}
	
	@ApiOperation(value = "修改")
	@PutMapping("/storeGoods")
	public Result<?> update(@RequestBody StoreGoods storeGoods) {
		storeGoodsService.updateById(storeGoods);
	 return Result.SUCCESS;
	}
	
	@ApiOperation(value = "删除")
	@DeleteMapping("/storeGoods/{storeGoodsId}")
	public Result<?> delete(@PathVariable(name="storeGoodsId") String storeGoodsId) {
		storeGoodsService.delete(storeGoodsId);
		return Result.SUCCESS;
	}
	
	@ApiOperation(value = "新增")
	@PostMapping("/storeGoods")
	public Result<?> insert(@RequestBody StoreGoods storeGoods) {	
		storeGoods.setIsDelete(0);
		storeGoods.setCreatedTime(new Date());
		storeGoodsService.insert(storeGoods);
		return Result.SUCCESS;
	}

}
