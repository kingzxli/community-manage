package com.community.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("区域表")
@Data
public class BaseCity implements Serializable{
	private static final long serialVersionUID = 1L;

	@TableId
	@ApiModelProperty(value = "Id")
	private String id;
	
	@ApiModelProperty(value = "上级id")
	private String parentId;
	
	@ApiModelProperty(value = "区域名称")
	private String cityName;
	
	@ApiModelProperty(value = "级别(0:国,1:省,2:市,3区县)")
	private Integer cityLevel;
	
}
