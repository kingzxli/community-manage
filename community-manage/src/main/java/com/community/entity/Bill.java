package com.community.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("账单表")
@Data
public class Bill implements Serializable{
	private static final long serialVersionUID = 1L;

	@TableId
	@ApiModelProperty(value = "Id")
	private String id;
	
	@ApiModelProperty(value = "房间Id")
	private String roomId;
	
	@ApiModelProperty(value = "年份")
	private String billYear;
	
	@ApiModelProperty(value = "账单金额")
	private BigDecimal billAmount;
	
	@ApiModelProperty(value = "缴纳金额")
	private BigDecimal payAmount;
	
	@ApiModelProperty(value = "缴费状态(1:已缴,2:未缴)")
	private Integer payStatus;
	
	@ApiModelProperty(value = "备注")
	private String remark;
	
	@ApiModelProperty(value = "缴费人微信Id")
	private String payWxId;
	
	@ApiModelProperty(value = "缴费人电话")
	private String payPhone;
	
	@ApiModelProperty(value = "缴费人姓名")
	private String payName;
	
	@ApiModelProperty(value = "是否删除(1:是，0:否)")
	@TableLogic
	private Integer isDelete;
	
	@ApiModelProperty(value = "创建人")
	private String createdUser;
	
	@ApiModelProperty(value = "创建时间")
	private Date createdTime;
	
	@ApiModelProperty(value = "修改人")
	private String modifiedUser;
	
	@ApiModelProperty(value = "修改时间")
	private Date modifiedTime;
	
}
