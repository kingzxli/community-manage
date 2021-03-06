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

import com.community.entity.common.Bill;
import com.community.entity.common.Page;
import com.community.entity.common.Result;
import com.community.entity.common.vo.BillVo;
import com.community.service.common.BillService;
import com.community.util.IdMaker;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "账单管理")
@RestController
public class BillController {
	
	@Autowired
	private BillService billService;
	
	@ApiOperation(value = "列表查询")
	@GetMapping("/bill")
	public Result<List<BillVo>> selectBill(Page page,BillVo bill) {		
		page.paging();		
		List<BillVo> list = billService.selectBill(bill);	
		return new Result<>(list).total(new PageInfo<BillVo>(list).getTotal());
	}
	
	@ApiOperation(value = "单记录查询")
	@GetMapping("/bill/{billId}")
	public Result<Bill> getUserById(@PathVariable(name="billId") String billId) {				
		return new Result<>(billService.getById(billId));
	}
	
	@ApiOperation(value = "修改")
	@PutMapping("/bill")
	public Result<?> update(@RequestBody Bill bill) {
		billService.updateById(bill);
	 return Result.SUCCESS;
	}
	
	@ApiOperation(value = "删除")
	@DeleteMapping("/bill/{billId}")
	public Result<?> delete(@PathVariable(name="billId") String billId) {
		billService.delete(billId);
		return Result.SUCCESS;
	}
	
	@ApiOperation(value = "新增")
	@PostMapping("/bill")
	public Result<?> insert(@RequestBody Bill bill) {
		bill.setId(IdMaker.get());
		bill.setIsDelete(0);
		bill.setCreatedTime(new Date());
		
		billService.insert(bill);
		return Result.SUCCESS;
	}

}
