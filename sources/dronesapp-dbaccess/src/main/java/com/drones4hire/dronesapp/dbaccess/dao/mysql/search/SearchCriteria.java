package com.drones4hire.dronesapp.dbaccess.dao.mysql.search;

import javax.validation.constraints.Min;

public class SearchCriteria
{

	public enum SortOrder {ASC, DESC};

	// Pages are zero-based
	@Min(value = 1, message = "Page number should be greater than 1")
	private Integer page = 1;
	// The very default page size, just not to get NPE'd
	@Min(value = 0, message = "Page size should be positive")
	private Integer pageSize = 25;

	private SortOrder sortOrder = SortOrder.ASC;

	public Integer getPage()
	{
		return page;
	}

	public void setPage(Integer page)
	{
		this.page = page;
	}

	public Integer getPageSize()
	{
		return pageSize;
	}

	public void setPageSize(Integer pageSize)
	{
		this.pageSize = pageSize;
	}

	public void setPageSizeFully(Integer page, Integer pageSize)
	{
		this.pageSize = pageSize;
		this.page = getPageSize() * (page - 1);
	}

	public SortOrder getSortOrder()
	{
		return sortOrder;
	}

	public void setSortOrder(SortOrder sortOrder)
	{
		this.sortOrder = sortOrder;
	}
}
