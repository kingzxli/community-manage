package com.community.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.community.entity.Unit;


@Mapper
public interface UnitMapper extends BaseMapper<Unit>{

	List<Unit> list(Unit unit);

}
