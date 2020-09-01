package com.community.entity;


import org.apache.commons.lang3.StringUtils;
import com.github.pagehelper.PageHelper;
import lombok.Data;

/**
 * 页面参数
 * 
 * @author Rex.Tan
 * @date 2019年7月29日 下午2:33:01
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
	 * 
	 * @author Rex.Tan
	 * @date 2019年10月12日 上午11:12:33
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
