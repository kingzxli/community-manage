package com.community.entity;

import java.io.Serializable;
import java.util.Date;

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
	private String name;
	
	@ApiModelProperty(value = "微信名称")
	private String wxName;
	
	@ApiModelProperty(value = "电话")
	private String phone;
	
	@ApiModelProperty(value = "年龄")
	private Integer age;
	
	@ApiModelProperty(value = "性别(1:男，2:女)")
	private Integer gender;
	
	@ApiModelProperty(value = "角色Id")
	private String roleId;
	
	@ApiModelProperty(value = "房间Id")
	private String roomId;
	
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
