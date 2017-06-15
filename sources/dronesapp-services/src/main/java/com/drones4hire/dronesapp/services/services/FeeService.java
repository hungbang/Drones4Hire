package com.drones4hire.dronesapp.services.services;

import com.drones4hire.dronesapp.models.db.projects.Bid;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class FeeService
{

	private static final Integer FEE = 20;

	public BigDecimal getFeeAmount(Bid bid)
	{
		return bid.getAmount().multiply(new BigDecimal(FEE)).divide(new BigDecimal(100));
	}
}
