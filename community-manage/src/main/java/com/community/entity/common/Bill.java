package com.community.entity.common;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;

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
	
	@NotNull(message = "商品Id不能为空")
	@ApiModelProperty(value = "房屋id/商品id")
	private String itemId;
	
	@NotNull(message = "账单类型Id不能为空")
	@ApiModelProperty(value = "账单类型(1:物业缴费2:购物)")
	private BigDecimal billType;
	
	@NotNull(message = "缴纳金额不能为空")
	@ApiModelProperty(value = "缴纳金额")
	private BigDecimal payAmount;
	
	@ApiModelProperty(value = "备注")
	private String remark;
	
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
