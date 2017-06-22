package com.drones4hire.dronesapp.services.services;

import com.braintreegateway.*;
import com.braintreegateway.MerchantAccount;
import com.braintreegateway.test.*;
import com.drones4hire.dronesapp.models.db.payments.Wallet;
import com.drones4hire.dronesapp.models.db.users.User;
import com.drones4hire.dronesapp.services.exceptions.ServiceException;
import com.drones4hire.dronesapp.services.exceptions.UserNotFoundException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.List;

@Service
public class BraintreeService
{

	private static enum EnvironmentType
	{
		SANDBOX, DEVELOPMENT, PRODUCTION
	}

	;

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

	public String generateClientToken(Wallet wallet) throws ServiceException
	{
		String clientToken = null;
		if (wallet.getPaymentToken() != null)
		{
			ClientTokenRequest request = null;
			try
			{
				if(getCustomer(wallet.getPaymentToken()) != null)
				{
					request = new ClientTokenRequest()
							.customerId(wallet.getPaymentToken());
					clientToken = braintreeGateway.clientToken().generate(request);
				} else
				{
					throw new UserNotFoundException("Invalid customer id");
				}
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return clientToken;
	}

	public PaymentMethod getPaymentMethod(User user, String paymentMethodNonce) throws ServiceException
	{
		Customer customer = createCustomer(user);
		return createPaymentMethod(customer.getId(), paymentMethodNonce);
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
			throw new ServiceException("Can't update customer: " + e.getMessage());
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
					throw new UserNotFoundException("Customer with id '" + customerId + "' is not found");
				}
			}
		} catch (Exception e)
		{
			throw new UserNotFoundException("Can't find customer by id: " + e.getMessage());
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
				request.options()
						.makeDefault(true)
						.done();
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

	public PaymentMethod makePaymentMethodDefault(String paymentToken) throws ServiceException
	{
		Result<? extends PaymentMethod> result = null;
		try
		{
			if (!StringUtils.isEmpty(paymentToken))
			{
				PaymentMethodRequest request = new PaymentMethodRequest()
						.token(paymentToken);
				request.options()
						.makeDefault(true)
						.done();
				result = braintreeGateway.paymentMethod().update(paymentToken, request);
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

	public PaymentMethod findPaymentMethodByToken(String paymentMethodToken) throws ServiceException
	{
		PaymentMethod result = null;
		try
		{
			if (!StringUtils.isEmpty(paymentMethodToken))
			{
				result = braintreeGateway.paymentMethod().find(paymentMethodToken);
				if (result == null)
				{
					throw new Exception("Payment method is not found");
				}
			}
		} catch (Exception e)
		{
			throw new ServiceException("Can't find payment method: " + e.getMessage());
		}
		return result;
	}

	public Transaction sale(String customerId, BigDecimal amount, String paymentMethodToken) throws ServiceException
	{
		Result<Transaction> result = null;
		try
		{
			TransactionRequest request = new TransactionRequest()
					.amount(amount)
					.customerId(customerId)
					.paymentMethodToken(paymentMethodToken);
			request.options()
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
					throw new ServiceException("Transaction with id '" + transactionId + "' is not found");
				}
			}
		} catch (Exception e)
		{
			throw new ServiceException("Can't refund transaction: " + e.getMessage());
		}
		return result;
	}

	public CreditCard addCreditCard(String customerId, String number, String expirationMonth, String expirationYear, String cvv, String cardholderName)
			throws ServiceException
	{
		Result<CreditCard> result = null;
		try
		{
			CreditCardRequest request = new CreditCardRequest()
					.customerId(customerId)
					.number(number)
					.expirationMonth(expirationMonth)
					.expirationYear(expirationYear)
					.cardholderName(cardholderName)
					.cvv(cvv);
			request
					.options()
					.verifyCard(true)
					.makeDefault(true);
			result = braintreeGateway.creditCard().create(request);
			if (!result.isSuccess())
			{
				throw new Exception(result.getMessage());
			}
		} catch (Exception e)
		{
			throw new ServiceException("Can't add credit card: " + e.getMessage());
		}
		return result.getTarget();
	}

	public List<CreditCard> getCreditCardsByCustomerId(String customerId)
			throws ServiceException
	{
		List<CreditCard> creditCards = null;
		try
		{
			creditCards = getCustomer(customerId).getCreditCards();
		} catch (Exception e)
		{
			throw new ServiceException("Can't add credit card: " + e.getMessage());
		}
		return creditCards;
	}

	public CreditCard getCreditCard(String token) throws ServiceException
	{
		CreditCard creditCard = null;
		try
		{
			creditCard = braintreeGateway.creditCard().find(token);
			if (creditCard == null)
			{
				throw new Exception("Not found by card token.");
			}
		} catch (Exception e)
		{
			throw new ServiceException("Can't find credit card: " + e.getMessage());
		}
		return creditCard;
	}

	public CreditCard getDefaultCreditCard(String customerId) throws ServiceException
	{
		CreditCard creditCard = null;
		Customer customer = null;
		try
		{
			customer = braintreeGateway.customer().find(customerId);
			for(CreditCard cc : customer.getCreditCards())
			{
				if(cc.isDefault())
				{
					creditCard = cc;
				}
			}
			if (creditCard == null)
			{
				throw new Exception("Not found by card token.");
			}
		} catch (Exception e)
		{
			throw new ServiceException("Can't find credit card: " + e.getMessage());
		}
		return creditCard;
	}

	public CreditCard updateCreditCard(String token, String expirationMonth, String expirationYear, String cardholderName) throws ServiceException
	{
		Result<CreditCard> result = null;
		try
		{
			CreditCardRequest request = new CreditCardRequest()
					.token(token)
					.expirationMonth(expirationMonth)
					.expirationYear(expirationYear)
					.cardholderName(cardholderName);
			request.options()
					.verifyCard(true);
			result = braintreeGateway.creditCard().update(token, request);
			if (!result.isSuccess())
			{
				throw new Exception(result.getMessage());
			}
		} catch (Exception e)
		{
			throw new ServiceException("Can't update credit card: " + e.getMessage());
		}
		return result.getTarget();
	}

	public CreditCard makeCreditCardDefault(String token) throws ServiceException
	{
		Result<CreditCard> result = null;
		try
		{
			CreditCardRequest request = new CreditCardRequest()
					.token(token);
			request.options().makeDefault(true);
			result = braintreeGateway.creditCard().update(token, request);
			if (!result.isSuccess())
			{
				throw new Exception(result.getMessage());
			}
		} catch (Exception e)
		{
			throw new ServiceException("Can't update credit card: " + e.getMessage());
		}
		return result.getTarget();
	}

	public CreditCard deleteCreditCard(String token) throws ServiceException
	{
		Result<CreditCard> result = null;
		try
		{
			result = braintreeGateway.creditCard().delete(token);
			if (!result.isSuccess())
			{
				throw new Exception(result.getMessage());
			}
		} catch (Exception e)
		{
			throw new ServiceException("Can't delete credit card: " + e.getMessage());
		}
		return result.getTarget();
	}
}
