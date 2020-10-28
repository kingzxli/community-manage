package com.community.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("地址")
@Data
public class StoreAddress implements Serializable{
	private static final long serialVersionUID = 1L;

	@TableId
	@ApiModelProperty(value = "Id")
	private String id;
	
	@ApiModelProperty(value = "用户id")
	private String userId;
	
	@ApiModelProperty(value = "收货人姓名")
	private String recvName;
	
	@ApiModelProperty(value = "收货人城市")
	private String recvCityId;
	
	@ApiModelProperty(value = "收货人地址")
	private String recvAddress;
	
	@ApiModelProperty(value = "收货人电话")
	private String recvPhone;
	
	@ApiModelProperty(value = "是否默认")
	private Integer isDefault;
	
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
