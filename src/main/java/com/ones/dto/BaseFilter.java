/**
 * 
 */
package com.ones.dto;

public class BaseFilter {

	private SortDTO[] sortDTOs;
	private Integer page;
	private Integer limit;
	private FieldOption[] options;

	public SortDTO[] getSortDTOs() {
		return this.sortDTOs;
	}

	public void setSortDTOs(SortDTO[] sortDTOs) {
		this.sortDTOs = sortDTOs;
	}

	public Integer getPage() {
		return this.page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getLimit() {
		return this.limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public FieldOption[] getOptions() {
		return options;
	}

	public void setOptions(FieldOption[] options) {
		this.options = options;
	}
}
