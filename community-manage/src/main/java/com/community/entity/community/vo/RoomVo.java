package com.community.entity.community.vo;

import java.math.BigDecimal;

import com.community.entity.community.Room;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel("房间表Vo")
@Getter
@Setter
public class RoomVo extends Room{
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "应缴费金额")
	private BigDecimal billAmount;
	
	@ApiModelProperty(value = "姓名")
	private String name;
	
	@ApiModelProperty(value = "电话")
	private String phone;
	
}
