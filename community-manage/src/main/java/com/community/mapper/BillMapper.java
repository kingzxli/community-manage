package com.community.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.community.entity.Bill;
import com.community.entity.vo.BillVo;


@Mapper
public interface BillMapper extends BaseMapper<Bill>{
	
	List<BillVo> selectBill(BillVo bill);

}
