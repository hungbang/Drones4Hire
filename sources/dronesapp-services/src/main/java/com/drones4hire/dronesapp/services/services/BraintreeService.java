package com.drones4hire.dronesapp.services.services;

import com.braintreegateway.*;
import com.drones4hire.dronesapp.models.db.users.User;
import com.drones4hire.dronesapp.services.exceptions.ServiceException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;

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
		}
		braintreeGateway = new BraintreeGateway(env, merchantId, publicKey, privateKey);
	}

	public Transaction sale(User user, String paymentMethodNonce, BigDecimal amount) throws ServiceException
	{
		createPaymentMethod(user, paymentMethodNonce);
		return sale(amount, paymentMethodNonce);
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
			throw new ServiceException("Can't create payment customer: " + e.getMessage());
		}
		return result.getTarget();
	}

	public Customer updateCustomer(User user, String customerId) throws ServiceException
	{
		Result<Customer> result = null;
		try
		{
			if (!StringUtils.isEmpty(customerId))
			{
				CustomerRequest request = new CustomerRequest()
						.firstName(user.getFirstName())
						.lastName(user.getLastName())
						.email(user.getEmail())
						.company(merchantName);
				result = braintreeGateway.customer().update(customerId, request);
			}
			if (!result.isSuccess())
			{
				throw new Exception(result.getMessage());
			}
		} catch (Exception e)
		{
			throw new ServiceException("Can't update payment customer: " + e.getMessage());
		}
		return result.getTarget();
	}

	public Customer getCustomer(String customerId) throws ServiceException
	{
		Customer customer = null;
		try
		{
			if (!StringUtils.isEmpty(customerId))
			{
				customer = braintreeGateway.customer().find(customerId);
				if (customer == null)
				{
					throw new Exception("Customer with id '" + customerId + "' is not found");
				}
			}
		} catch (Exception e)
		{
			throw new ServiceException("Can't find customer by id: " + e.getMessage());
		}
		return customer;
	}

	public Customer deleteCustomer(String customerId) throws ServiceException
	{
		Result<Customer> result = null;
		try
		{
			if (!StringUtils.isEmpty(customerId))
			{
				result = braintreeGateway.customer().delete(customerId);
				if (!result.isSuccess())
				{
					throw new Exception(result.getMessage());
				}
			}
		} catch (Exception e)
		{
			throw new ServiceException("Can't delete customer: " + e.getMessage());
		}
		return result.getTarget();
	}

	public PaymentMethod createPaymentMethod(String customerId, String paymentMethodNonce) throws ServiceException
	{
		Result<? extends PaymentMethod> result = null;
		try
		{
			if (!StringUtils.isEmpty(customerId))
			{
				PaymentMethodRequest request = new PaymentMethodRequest()
						.customerId(customerId)
						.paymentMethodNonce(paymentMethodNonce);
				result = braintreeGateway.paymentMethod().create(request);
				if (!result.isSuccess())
				{
					throw new Exception(result.getMessage());
				}
			}
		} catch (Exception e)
		{
			throw new ServiceException("Can't create payment method: " + e.getMessage());
		}
		return result.getTarget();
	}

	public PaymentMethod createPaymentMethod(User user, String paymentMethodNonce) throws ServiceException
	{
		return createPaymentMethod(createCustomer(user).getId(), paymentMethodNonce);
	}

	public Transaction sale(BigDecimal amount, String paymentMethodNonce) throws ServiceException
	{
		Result<Transaction> result = null;
		try
		{
			TransactionRequest request = new TransactionRequest()
					.amount(amount)
					.paymentMethodNonce(paymentMethodNonce)
					.options()
					.submitForSettlement(true)
					.done();
			result = braintreeGateway.transaction().sale(request);
			if (!result.isSuccess())
			{
				throw new Exception(result.getMessage());
			}
		} catch (Exception e)
		{
			throw new ServiceException("Can't create payment transaction: " + e.getMessage());
		}
		return result.getTarget();
	}

	public Transaction settleByTransactionId(String transactionId, BigDecimal amount) throws ServiceException
	{
		Result<Transaction> result = null;
		try
		{
			if (!StringUtils.isEmpty(transactionId))
			{
				result = braintreeGateway.transaction().submitForSettlement(transactionId, amount);
				if (!result.isSuccess())
				{
					throw new Exception(result.getMessage());
				}
			}
		} catch (Exception e)
		{
			throw new ServiceException("Can't settlement: " + e.getMessage());
		}
		return result.getTarget();
	}

	public void voidTransaction(String transactionId) throws ServiceException
	{
		try
		{
			if (!StringUtils.isEmpty(transactionId))
			{
				Result<Transaction> result = braintreeGateway.transaction().voidTransaction(transactionId);
				if (!result.isSuccess())
				{
					throw new Exception(result.getMessage());
				}
			}
		} catch (Exception e)
		{
			throw new ServiceException("Can't void transaction: " + e.getMessage());
		}
	}

	public Transaction refundTransaction(String transactionId) throws ServiceException
	{
		Result<Transaction> result = null;
		try
		{
			if (!StringUtils.isEmpty(transactionId))
			{
				result = braintreeGateway.transaction().refund(transactionId);
				if (!result.isSuccess())
				{
					throw new Exception(result.getMessage());
				}
			}
		} catch (Exception e)
		{
			throw new ServiceException("Can't refund transaction: " + e.getMessage());
		}
		return result.getTarget();
	}

	public Transaction getTransaction(String transactionId) throws ServiceException
	{
		Transaction result = null;
		try
		{
			if (!StringUtils.isEmpty(transactionId))
			{
				result = braintreeGateway.transaction().find(transactionId);
				if (result == null)
				{
					throw new Exception("Transaction with id '" + transactionId + "' is not found");
				}
			}
		} catch (Exception e)
		{
			throw new ServiceException("Can't refund transaction: " + e.getMessage());
		}
		return result;
	}
}
