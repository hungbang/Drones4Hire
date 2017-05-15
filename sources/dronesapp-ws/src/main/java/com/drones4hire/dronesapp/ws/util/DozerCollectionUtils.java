package com.drones4hire.dronesapp.ws.util;

import java.util.ArrayList;
import java.util.List;

import org.dozer.Mapper;

public class DozerCollectionUtils
{
	public static <T, U> List<U> mapListToList(List<T> sourceList, Class<U> destinationClass, Mapper mapper)
	{
		List<U> destinationList = new ArrayList<>();
		for (T sourceObject : sourceList)
		{
			destinationList.add(mapper.map(sourceObject, destinationClass));
		}
		return destinationList;
	}
}
