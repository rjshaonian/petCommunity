package com.tedu.petCommunity.common.vo;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommObject<T> implements Serializable {

	private static final long serialVersionUID = 3207883241773934542L;
	/** 总记录数 */
	private Integer rowCount;
	/** 当前页记录 */
	private List<T> records;
	/** 总页数 */
	private Integer pageCount;
	/** 当前页码值 */
	private Integer pageCurrent;
	/** 页面大小(每页最多显示多少条记录) */
	private Integer pageSize;

	public CommObject(List<T> records, Integer rowCount, Integer pageCurrent, Integer pageSize) {
		super();
		this.records = records;
		this.rowCount = rowCount;
		this.pageCurrent = pageCurrent;
		this.pageSize = pageSize;

		// 计算总页数
		this.pageCount = (rowCount - 1) / pageSize + 1;
	}

}
