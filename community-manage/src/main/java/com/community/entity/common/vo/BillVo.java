package com.community.entity.common.vo;

import java.math.BigDecimal;
import java.util.Date;

import com.community.entity.common.Bill;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;


@ApiModel("账单表Vo")
@Getter
@Setter
public class BillVo extends Bill{
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "楼盘名称")
	private String communityName;
	
	@ApiModelProperty(value = "楼盘地址")
	private String communityAddress;
	
	@ApiModelProperty(value = "楼栋名称")
	private String buildName;
	
	@ApiModelProperty(value = "单元名称")
	private String unit;
	
	@ApiModelProperty(value = "房间名称")
	private String roomName;
	
	@ApiModelProperty(value = "面积(m2)")
	private BigDecimal area;
	
	@ApiModelProperty(value = "类型(1:住宅,,2:商铺,3:车库)")
	private Integer type;
	
	@ApiModelProperty(value = "楼盘Id")
	private String communityId;
	
	@ApiModelProperty(value = "楼栋Id")
	private String buildId;
	
	@ApiModelProperty(value = "单元Id")
	private String unitId;
	
	@ApiModelProperty(value = "开始日期")
	private Date startDate;
	
	@ApiModelProperty(value = "结束日期")
	private Date endDate;
	
}
