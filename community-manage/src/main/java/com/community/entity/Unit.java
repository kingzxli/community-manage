package com.community.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("单元表")
@Data
public class Unit implements Serializable{
	private static final long serialVersionUID = 1L;

	@TableId
	@ApiModelProperty(value = "Id")
	private String id;
	
	@ApiModelProperty(value = "单元名称")
	private String unit;
	
	@ApiModelProperty(value = "楼栋Id")
	private String buildId;
	
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
