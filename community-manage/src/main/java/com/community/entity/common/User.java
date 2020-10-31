package com.community.entity.common;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("用户表")
@Data
public class User implements Serializable{
	private static final long serialVersionUID = 1L;

	@TableId
	@ApiModelProperty(value = "Id")
	private String id;
	
	@ApiModelProperty(value = "姓名")
	private String userName;
	
	@NotNull(message = "电话不能为空")
	@ApiModelProperty(value = "电话")
	private String phone;
	
	@ApiModelProperty(value = "角色(1:管理员,2:游客)")
	private Integer roleId;
	
	@ApiModelProperty(value = "头像")
	private String image;
	
	@ApiModelProperty(value = "微信号")
	private String openId;
	
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
	
	@TableField(exist = false)
	@ApiModelProperty(value = "验证码")
	private String code;
	
}
