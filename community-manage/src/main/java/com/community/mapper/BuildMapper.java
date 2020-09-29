package com.community.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.community.entity.Build;


@Mapper
public interface BuildMapper extends BaseMapper<Build>{

	List<Build> list(Build build);

}
