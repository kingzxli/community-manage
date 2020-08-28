package com.community.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("房间表")
@Data
public class Room implements Serializable{
	private static final long serialVersionUID = 1L;

	@TableId
	@ApiModelProperty(value = "Id")
	private String id;
	
	@ApiModelProperty(value = "房间名称")
	private String room;
	
	@ApiModelProperty(value = "租户Id")
	private String userId;
	
	@ApiModelProperty(value = "单元Id")
	private String unitId;
	
	@ApiModelProperty(value = "面积(m2)")
	private BigDecimal area;
	
	@ApiModelProperty(value = "类型(1:住宅,,2:商铺,3:车库)")
	private Integer type;
	
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
