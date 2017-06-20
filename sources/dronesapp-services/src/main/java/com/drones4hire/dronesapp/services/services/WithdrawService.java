package com.drones4hire.dronesapp.services.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drones4hire.dronesapp.dbaccess.dao.mysql.WithdrawRequestMapper;
import com.drones4hire.dronesapp.dbaccess.dao.mysql.search.SearchResult;
import com.drones4hire.dronesapp.dbaccess.dao.mysql.search.WithdrawSearchCriteria;
import com.drones4hire.dronesapp.models.db.payments.Transaction;
import com.drones4hire.dronesapp.models.db.payments.Transaction.Type;
import com.drones4hire.dronesapp.models.db.payments.Wallet;
import com.drones4hire.dronesapp.models.db.payments.WithdrawRequest;
import com.drones4hire.dronesapp.models.db.payments.WithdrawRequest.Status;
import com.drones4hire.dronesapp.services.exceptions.ForbiddenOperationException;
import com.drones4hire.dronesapp.services.exceptions.NotEnoughMoneyException;
import com.drones4hire.dronesapp.services.exceptions.ServiceException;

@Service
public class WithdrawService
{
	@Autowired
	private WithdrawRequestMapper withdrawMapper;

	@Autowired
	private TransactionService transactionService;
	
	@Autowired
	private WalletService walletService;
	
	public WithdrawRequest createWithdraw(WithdrawRequest request, Long principalId) throws ServiceException {
		if(principalId != request.getUserId()) 
			throw new ForbiddenOperationException();
		Wallet wallet = walletService.getWalletByUserId(request.getUserId());
		if(wallet.getBalance().doubleValue() < request.getAmount().doubleValue())
			throw new NotEnoughMoneyException();
		request.setStatus(Status.NEW);
		Transaction transaction = new Transaction();
		transaction.setAmount(request.getAmount());
		transaction.setCurrency(request.getCurrency());
		transaction.setType(Type.WITHDRAW);
		transaction.setWalletId(wallet.getId());
		transactionService.createTransaction(transaction);
		request.setTransactionId(transaction.getId());
		withdrawMapper.createWithdrawRequest(request);
		return request;
	}
	
	public SearchResult<WithdrawRequest> search(WithdrawSearchCriteria withdrawSearchCriteria) {
		return null;
	}
}
