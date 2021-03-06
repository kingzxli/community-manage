package com.community.entity.store;

import java.io.Serializable;
import java.util.Date;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("购物车")
@Data
public class StoreCart implements Serializable{
	private static final long serialVersionUID = 1L;

	@TableId
	@ApiModelProperty(value = "Id")
	private String id;
	
	@ApiModelProperty(value = "用户id")
	private String userId;
	
	@NotNull(message = "商品id不能为空")
	@ApiModelProperty(value = "商品Id")
	private String goodsId;
	
	@Length(max = 32, message = "卖点描述输入超出最大长度(32)")
	@ApiModelProperty(value = "卖点描述")
	private String sellPoint;
	
	@NotNull(message = "商品数量不能为空")
	@ApiModelProperty(value = "数量")
	private Integer num;
	
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
