package com.drones4hire.admin.dto.json;

public class AbstractListResult
{
	private int size;

	public AbstractListResult(int size)
	{
		this.size = size;
	}

	public int getSize()
	{
		return size;
	}

	public void setSize(int size)
	{
		this.size = size;
	}
}
