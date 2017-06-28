package com.drones4hire.dronesapp.services.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.drones4hire.dronesapp.dbaccess.dao.mysql.WalletMapper;
import com.drones4hire.dronesapp.models.db.commons.Currency;
import com.drones4hire.dronesapp.models.db.payments.Wallet;
import com.drones4hire.dronesapp.models.db.users.User;
import com.drones4hire.dronesapp.services.exceptions.PaymentException;

@Service
public class WalletService
{
	@Autowired
	private WalletMapper walletMapper;

	@Transactional(rollbackFor = Exception.class)
	public Wallet createWallet(Wallet wallet)
	{
		walletMapper.createWallet(wallet);
		return wallet;
	}

	@Transactional(rollbackFor = Exception.class)
	public Wallet createDefaultWallet(User user)
	{
		Wallet wallet = new Wallet(user.getId());
		wallet.setBalance(BigDecimal.ZERO);
		wallet.setCurrency(Currency.USD);
		wallet.setWithdrawToken(UUID.randomUUID().toString());
		return createWallet(wallet);
	}

	@Transactional(readOnly = true)
	public Wallet getWalletById(long id)
	{
		return walletMapper.getWalletById(id);
	}

	@Transactional(readOnly = true)
	public Wallet getWalletByUserId(long userId)
	{
		return walletMapper.getWalletByUserId(userId);
	}
	
	@Transactional(readOnly = true)
	public Wallet getNotNullUserWallet(long userId) throws PaymentException
	{
		Wallet wallet = walletMapper.getWalletByUserId(userId);
		if(wallet == null)
		{
			throw new PaymentException("No payment token in wallet found");
		}
		return wallet;
	}
	
	@Transactional(readOnly = true)
	public Wallet getWalletByWithdrawToken(String token)
	{
		return walletMapper.getWalletByWithdrawToken(token);
	}

	@Transactional(readOnly = true)
	public List<Wallet> getAllWallets()
	{
		return walletMapper.getAllWallets();
	}

	@Transactional(rollbackFor = Exception.class)
	public Wallet updateWallet(Wallet wallet)
	{
		walletMapper.updateWallet(wallet);
		return wallet;
	}

	@Transactional(rollbackFor = Exception.class)
	public void deleteWallet(long id)
	{
		walletMapper.deleteWallet(id);
	}
}
