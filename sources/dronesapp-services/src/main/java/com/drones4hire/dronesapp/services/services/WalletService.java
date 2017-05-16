package com.drones4hire.dronesapp.services.services;

import com.drones4hire.dronesapp.dbaccess.dao.mysql.WalletMapper;
import com.drones4hire.dronesapp.models.db.payments.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
