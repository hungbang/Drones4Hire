package com.drones4hire.dronesapp.services.services;

import com.braintreegateway.CreditCard;
import com.drones4hire.dronesapp.models.db.payments.Wallet;
import com.drones4hire.dronesapp.models.db.users.User;
import com.drones4hire.dronesapp.services.exceptions.ServiceException;
import com.drones4hire.dronesapp.services.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CreditCardService
{

	@Autowired
	private UserService userService;

	@Autowired
	private BraintreeService braintreeService;

	@Autowired
	private WalletService walletService;

	@Transactional
	public List<CreditCard> getCreditCardsByUserId(long userId) throws ServiceException
	{
		User user = userService.getUserById(userId);
		if (user == null)
			throw new UserNotFoundException("User not found by ID");

		Wallet wallet = walletService.getWalletByUserId(userId);
		if (wallet.getPaymentToken() == null)
			throw new ServiceException("Payment token is null");

		return braintreeService.getCreditCardsByCustomerId(wallet.getPaymentToken());
	}

	@Transactional
	public CreditCard getDefaultUserCreditCard(long userId) throws ServiceException
	{
		User user = userService.getUserById(userId);
		if (user == null)
			throw new UserNotFoundException("User not found by ID");

		List<CreditCard> creditCards = getCreditCardsByUserId(userId);

		for (CreditCard creditCard : creditCards)
		{
			if (creditCard.isDefault())
			{
				return creditCard;
			}
		}

		throw new ServiceException("Default credit card not found!");
	}

	@Transactional
	public CreditCard getCreditCardByToken(String token) throws ServiceException
	{
		return braintreeService.getCreditCard(token);
	}

	@Transactional
	public CreditCard addCreditCard(long userId, String number, String expirationMonth, String expirationYear,
			String cvv, String cardholderName)
			throws ServiceException
	{
		User user = userService.getUserById(userId);
		if (user == null)
			throw new UserNotFoundException("User not found by ID");

		Wallet wallet = walletService.getWalletByUserId(userId);
		if (wallet.getPaymentToken() == null)
			throw new ServiceException("Payment token is null");

		return braintreeService.addCreditCard(wallet.getPaymentToken(), number, expirationMonth, expirationYear, cvv, cardholderName);
	}

	@Transactional
	public void deleteCreditCard(long userId, String token)
			throws ServiceException
	{
		List<CreditCard> creditCards = getCreditCardsByUserId(userId);

		for (CreditCard creditCard : creditCards)
		{
			if (creditCard.getToken().equals(token))
			{
				braintreeService.deleteCreditCard(token);
				return;
			}
		}
		throw new ServiceException("Credit card not found by token: " + token);
	}

	@Transactional
	public CreditCard updateCreditCard(long userId, String token, String expirationMonth, String expirationYear, String cardholderName)
			throws ServiceException
	{
		List<CreditCard> creditCards = getCreditCardsByUserId(userId);

		for (CreditCard creditCard : creditCards)
		{
			if (creditCard.getToken().equals(token))
			{
				return braintreeService.updateCreditCard(token, expirationMonth, expirationYear, cardholderName);
			}
		}
		throw new ServiceException("Credit card not found by token: " + token);
	}

	@Transactional
	public CreditCard makeCreditCardDefault(String token)
			throws ServiceException
	{
		return braintreeService.makeCreditCardDefault(token);
	}
}
