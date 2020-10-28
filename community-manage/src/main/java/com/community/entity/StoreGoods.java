package com.community.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("商品分类")
@Data
public class StoreGoods implements Serializable{
	private static final long serialVersionUID = 1L;

	@TableId
	@ApiModelProperty(value = "Id")
	private String id;
	
	@ApiModelProperty(value = "商品分类id")
	private String goodsCategoryId;
	
	@ApiModelProperty(value = "商品名称")
	private String title;
	
	@ApiModelProperty(value = "卖点描述")
	private String sellPoint;
	
	@ApiModelProperty(value = "单价")
	private BigDecimal price;
	
	@ApiModelProperty(value = "数量")
	private Integer num;
	
	@ApiModelProperty(value = "图片")
	private String image;
	
	@ApiModelProperty(value = "状态(1:上架,0:下架)")
	private Integer status;
	
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
