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
import com.community.entity.store.StoreGoodsCategory;
import com.community.service.store.StoreGoodsCategoryService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "商品分类管理")
@RestController
public class StoreGoodsCategoryController {
	
	@Autowired
	private StoreGoodsCategoryService storeGoodsCategoryService;
	
	@ApiOperation(value = "列表查询")
	@GetMapping("/storeGoodsCategory")
	public Result<List<StoreGoodsCategory>> list(Page page,StoreGoodsCategory storeGoodsCategory) {	
		page.paging();			
		List<StoreGoodsCategory> list = storeGoodsCategoryService.list(storeGoodsCategory);
		return new Result<>(list).total(new PageInfo<StoreGoodsCategory>(list).getTotal());
	}
	
	@ApiOperation(value = "单记录查询")
	@GetMapping("/storeGoodsCategory/{storeGoodsCategoryId}")
	public Result<StoreGoodsCategory> getById(@PathVariable(name="storeGoodsCategoryId") String storeGoodsCategoryId) {				
		return new Result<>(storeGoodsCategoryService.getById(storeGoodsCategoryId));
	}
	
	@ApiOperation(value = "修改")
	@PutMapping("/storeGoodsCategory")
	public Result<?> update(@RequestBody StoreGoodsCategory storeGoodsCategory) {
		storeGoodsCategoryService.updateById(storeGoodsCategory);
	 return Result.SUCCESS;
	}
	
	@ApiOperation(value = "删除")
	@DeleteMapping("/storeGoodsCategory/{storeGoodsCategoryId}")
	public Result<?> delete(@PathVariable(name="storeGoodsCategoryId") String storeGoodsCategoryId) {
		storeGoodsCategoryService.delete(storeGoodsCategoryId);
		return Result.SUCCESS;
	}
	
	@ApiOperation(value = "新增")
	@PostMapping("/storeGoodsCategory")
	public Result<?> insert(@RequestBody StoreGoodsCategory storeGoodsCategory) {	
		storeGoodsCategory.setIsDelete(0);
		storeGoodsCategory.setCreatedTime(new Date());
		storeGoodsCategoryService.insert(storeGoodsCategory);
		return Result.SUCCESS;
	}

}
