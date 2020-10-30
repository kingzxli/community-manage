package com.community.mapper.common;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.community.entity.common.Bill;
import com.community.entity.common.vo.BillVo;


@Mapper
public interface BillMapper extends BaseMapper<Bill>{
	
	List<BillVo> selectBill(BillVo bill);

}
