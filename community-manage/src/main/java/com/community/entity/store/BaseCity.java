package com.community.entity.store;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("区域表")
@Data
public class BaseCity implements Serializable{
	private static final long serialVersionUID = 1L;

	@TableId
	@ApiModelProperty(value = "Id")
	private String id;
	
	@Length(max = 32, message = "上级id输入超出最大长度(32)")
	@ApiModelProperty(value = "上级id")
	private String parentId;
	
	@Length(max = 32, message = "区域名称输入超出最大长度(32)")
	@ApiModelProperty(value = "区域名称")
	private String cityName;
	
	@NotNull(message = "区域级别不能为空")
	@ApiModelProperty(value = "级别(0:国,1:省,2:市,3区县)")
	private Integer cityLevel;
	
}
