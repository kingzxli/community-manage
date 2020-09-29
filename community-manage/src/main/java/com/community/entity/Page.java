package com.community.entity;


import org.apache.commons.lang3.StringUtils;
import com.github.pagehelper.PageHelper;
import lombok.Data;

/**
 * 页面参数
 */
@Data
public class Page {
	/**
	 * 页码
	 */
	private Integer pageNum = 1;
	/**
	 * 每页数量
	 */
	private Integer pageSize = 100;
	/**
	 * 排序
	 */
	private String orderBy = "";
	/**
	 * 分页
	 */
	public void paging() {
		if(StringUtils.isBlank(orderBy)) {
			PageHelper.startPage(pageNum, pageSize);
		} else {
			PageHelper.startPage(pageNum, pageSize, orderBy);
		}
	}


	public static void max(){
		PageHelper.startPage(1, 100000000);
	}
}
