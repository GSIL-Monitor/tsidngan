package cn.dingan.tsdingan.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DataSet<T> {

	/**
	 * 第N页
	 */
	private Integer pageNo = 1;
	
	/**
	 * 每页N个
	 */
	private Integer pageSize = 10;
	
	/**
	 * 总页数
	 */
	private Integer pageCount;
	
	/**
	 * 总记录数
	 */
	private Integer totalCount;
	
	/**
	 * 数据
	 */
	private List<T> rows;
	
}
