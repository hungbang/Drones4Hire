package com.drones4hire.dronesapp.services.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.drones4hire.dronesapp.dbaccess.dao.mysql.WithdrawRequestMapper;
import com.drones4hire.dronesapp.dbaccess.dao.mysql.search.SearchResult;
import com.drones4hire.dronesapp.dbaccess.dao.mysql.search.WithdrawSearchCriteria;
import com.drones4hire.dronesapp.models.db.payments.Wallet;
import com.drones4hire.dronesapp.models.db.payments.WithdrawRequest;
import com.drones4hire.dronesapp.models.db.payments.WithdrawRequest.Status;
import com.drones4hire.dronesapp.services.exceptions.InavlidWaultAmountException;
import com.drones4hire.dronesapp.services.exceptions.InvalidCurrenyException;
import com.drones4hire.dronesapp.services.exceptions.ServiceException;

@Service
public class WithdrawService
{
	@Autowired
	private WithdrawRequestMapper withdrawMapper;

	@Autowired
	private WalletService walletService;

	@Transactional(rollbackFor = Exception.class)
	public WithdrawRequest createWithdraw(WithdrawRequest wr) throws ServiceException
	{
		Wallet wallet = walletService.getWalletByUserId(wr.getUserId());
		
		if(wallet == null || wallet.getBalance().compareTo(wr.getAmount()) < 0)
		{
			new InavlidWaultAmountException("Not enough funds on balance: " + wallet.getBalance());
		}
		
		if(!wallet.getCurrency().equals(wr.getCurrency()))
		{
			new InvalidCurrenyException("Required currency: " + wallet.getCurrency());
		}
		
		wr.setStatus(Status.NEW);
		
		withdrawMapper.createWithdrawRequest(wr);
		return wr;
	}

	public SearchResult<WithdrawRequest> search(WithdrawSearchCriteria withdrawSearchCriteria)
	{
		return null;
	}
}
