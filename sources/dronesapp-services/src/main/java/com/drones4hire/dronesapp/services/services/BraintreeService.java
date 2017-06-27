package com.drones4hire.dronesapp.services.services;

import java.math.BigDecimal;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.braintreegateway.BraintreeGateway;
import com.braintreegateway.ClientTokenRequest;
import com.braintreegateway.Customer;
import com.braintreegateway.CustomerRequest;
import com.braintreegateway.Environment;
import com.braintreegateway.Result;
import com.braintreegateway.Transaction;
import com.braintreegateway.TransactionRequest;
import com.drones4hire.dronesapp.models.db.users.User;
import com.drones4hire.dronesapp.services.exceptions.PaymentException;
import com.drones4hire.dronesapp.services.exceptions.ServiceException;

@Service
public class BraintreeService
{

	private static enum EnvironmentType
	{
		SANDBOX, DEVELOPMENT, PRODUCTION
	};

	@Value("${drones4hire.braintree.environment}")
	private String environment;

	@Value("${drones4hire.braintree.merchantId}")
	private String merchantId;

	@Value("${drones4hire.braintree.publicKey}")
	private String publicKey;

	@Value("${drones4hire.braintree.privateKey}")
	private String privateKey;

	@Value("${drones4hire.customer.id}")
	private String merchantName;

	private BraintreeGateway braintreeGateway;

	@PostConstruct
	public void afterPropertiesSet()
	{
		Environment env = null;

		switch (EnvironmentType.valueOf(environment))
		{
		case SANDBOX:
			env = Environment.SANDBOX;
			break;
		case DEVELOPMENT:
			env = Environment.DEVELOPMENT;
			break;
		case PRODUCTION:
			env = Environment.PRODUCTION;
			break;
		default:
			break;
		}
		braintreeGateway = new BraintreeGateway(env, merchantId, publicKey, privateKey);
	}

	public String generateClientToken(String customerId) throws ServiceException
	{
		String clientToken = null;
		ClientTokenRequest request = null;
		try
		{
			request = new ClientTokenRequest()
					.customerId(customerId);
			clientToken = braintreeGateway.clientToken().generate(request);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return clientToken;
	}

	public Customer createCustomer(User user) throws ServiceException
	{
		Result<Customer> result = null;
		try
		{
			CustomerRequest request = new CustomerRequest()
					.firstName(user.getFirstName())
					.lastName(user.getLastName())
					.email(user.getEmail())
					.company(merchantName);
			result = braintreeGateway.customer().create(request);
			if (!result.isSuccess())
			{
				throw new Exception(result.getMessage());
			}
		} catch (Exception e)
		{
			throw new ServiceException("Can't create customer: " + e.getMessage());
		}
		return result.getTarget();
	}

	public Result<Transaction> sale(String paymentMethod, BigDecimal amount) throws PaymentException
	{
		Result<Transaction> result = null;
		try
		{
			TransactionRequest request = new TransactionRequest()
				    .amount(amount)
				    .paymentMethodNonce(paymentMethod)
				    .options()
				      .submitForSettlement(true)
				      .done();
			
			result = braintreeGateway.transaction().sale(request);
		} catch (Exception e)
		{
			throw new PaymentException("Unable to process payment: " + e.getMessage());
		}
		return result;
	}
	
	public Result<Transaction> authrorize(String paymentMethod, BigDecimal amount) throws PaymentException
	{
		Result<Transaction> result = null;
		try
		{
			TransactionRequest request = new TransactionRequest()
					.amount(amount)
					.paymentMethodNonce(paymentMethod);
							
			// authorize
			result = braintreeGateway.transaction().sale(request);
		} catch (Exception e)
		{
			throw new PaymentException("Unable to authorize payment: " + e.getMessage());
		}
		return result;
	}
	
	public void settle(String transactionId) throws PaymentException
	{
		try
		{
			if (!StringUtils.isEmpty(transactionId))
			{
				Result<Transaction> result = braintreeGateway.transaction().submitForSettlement(transactionId);
				if (!result.isSuccess())
				{
					throw new Exception(result.getMessage());
				}
			}
		} catch (Exception e)
		{
			throw new PaymentException("Can't void transaction: " + e.getMessage());
		}
	}

	public Result<Transaction> release(String transactionId) throws PaymentException
	{
		Result<Transaction> result = null;
		try
		{
			if (!StringUtils.isEmpty(transactionId))
			{
				result = braintreeGateway.transaction().voidTransaction(transactionId);
				if (!result.isSuccess())
				{
					throw new Exception(result.getMessage());
				}
			}
		} catch (Exception e)
		{
			throw new PaymentException("Can't void transaction: " + e.getMessage());
		}
		return result;
	}
}
