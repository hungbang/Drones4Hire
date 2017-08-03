package com.drones4hire.dronesapp.services.services;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.braintreegateway.Result;
import com.drones4hire.dronesapp.models.db.commons.Currency;
import com.drones4hire.dronesapp.services.exceptions.PaymentException;
import com.drones4hire.dronesapp.services.exceptions.ServiceException;

@Service
public class PaymentService
{
	@Autowired
	private BraintreeService braintreeService;

	@Transactional(readOnly = true)
	public String generateClientToken(String customerId) throws ServiceException
	{
		return braintreeService.generateClientToken(customerId);
	}
	
	@Transactional(rollbackFor = Exception.class)
	public String makePayment(String paymentMethod, BigDecimal amount, Currency currency, String email) throws PaymentException
	{
		if(!Currency.USD.equals(currency))
		{
			throw new PaymentException("Supported currencies: USD");
		}
		
		Result<com.braintreegateway.Transaction> transaction  = braintreeService.sale(paymentMethod, amount, email);
		if(!transaction.isSuccess())
		{
			throw new PaymentException("Unable to process payment: " + transaction.getMessage());
		}
		
		return transaction.getTarget().getId();
	}
	
	@Transactional(rollbackFor = Exception.class)
	public String authorizePayment(String paymentMethod, BigDecimal amount, Currency currency, String email) throws PaymentException
	{
		if(!Currency.USD.equals(currency))
		{
			throw new PaymentException("Supported currencies: USD");
		}
		
		Result<com.braintreegateway.Transaction> transaction  = braintreeService.authrorize(paymentMethod, amount, email);
		if(!transaction.isSuccess())
		{
			throw new PaymentException("Unable to process payment: " + transaction.getMessage());
		}
		
		return transaction.getTarget().getId();
	}
	
	@Transactional(rollbackFor = Exception.class)
	public void release(String transactionId) throws PaymentException
	{
		braintreeService.release(transactionId);
	}
	
	@Transactional(rollbackFor = Exception.class)
	public void settle(String transactionId) throws PaymentException
	{
		braintreeService.settle(transactionId);
	}
}