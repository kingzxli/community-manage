package com.community.entity.vo;

import java.math.BigDecimal;
import com.community.entity.Room;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel("房间表Vo")
@Getter
@Setter
public class RoomVo extends Room{
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "楼盘Id")
	private String communityId;
	
	@ApiModelProperty(value = "楼盘名称")
	private String communityName;
	
	@ApiModelProperty(value = "楼盘地址")
	private String communityAddress;
	
	@ApiModelProperty(value = "楼栋Id")
	private String buildId;
	
	@ApiModelProperty(value = "楼栋名称")
	private String buildName;
	
	@ApiModelProperty(value = "单元Id")
	private String unitId;
	
	@ApiModelProperty(value = "单元名称")
	private String unit;
	
	@ApiModelProperty(value = "应缴费金额")
	private BigDecimal billAmount;
	
	@ApiModelProperty(value = "姓名")
	private String name;
	
	@ApiModelProperty(value = "微信名称")
	private String wxName;
	
	@ApiModelProperty(value = "电话")
	private String phone;
	
}
