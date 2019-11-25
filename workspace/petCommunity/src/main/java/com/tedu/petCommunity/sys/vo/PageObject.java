/**
 * 
 */
package com.tedu.petCommunity.sys.vo;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author VictorHe 2019年11月24日 下午3:26:56
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageObject<T> implements Serializable {
	private static final long serialVersionUID = 8924387722922123121L;
	/* 当前页码值 */
	private Integer pageCurrent = 1;
	/* 页面大小 */
	private Integer pageSize = 5;
	/* 总行数(通过查询之后获得) */
	private Integer rowCount = 0;
	/* 总页数 */
	private Integer pageCount = 0;
	/* 当前页记录 */
	private List<T> records;

}
