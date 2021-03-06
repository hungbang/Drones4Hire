package com.drones4hire.dronesapp.services.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.drones4hire.dronesapp.dbaccess.dao.mysql.TransactionMapper;
import com.drones4hire.dronesapp.dbaccess.dao.mysql.search.SearchResult;
import com.drones4hire.dronesapp.dbaccess.dao.mysql.search.TransactionSearchCriteria;
import com.drones4hire.dronesapp.models.db.payments.Transaction;
import com.drones4hire.dronesapp.models.db.payments.Wallet;
import com.drones4hire.dronesapp.services.exceptions.ServiceException;

@Service
public class TransactionService
{

	@Autowired
	private TransactionMapper transactionMapper;

	@Autowired
	private WalletService walletService;

	@Transactional(rollbackFor = Exception.class)
	public Transaction createTransaction(Transaction transaction)
	{
		transactionMapper.createTransaction(transaction);
		return transaction;
	}

	@Transactional(readOnly = true)
	public Transaction getTransactionById(long id)
	{
		return transactionMapper.getTransactionById(id);
	}

	@Transactional(readOnly = true)
	public List<Transaction> getTransactionsByWalletId(long principalId) throws ServiceException
	{
		Wallet wallet = walletService.getWalletByUserId(principalId);
		if(wallet == null)
		{
			throw new ServiceException("User's wallet not found.");
		}
		return transactionMapper.getTransactionsByWalletId(wallet.getId());
	}

	@Transactional(readOnly = true)
	public List<Transaction> getAllTransactions()
	{
		return transactionMapper.getAllTransactions();
	}

	@Transactional(readOnly = true)
	public SearchResult<Transaction> searchTransactions(TransactionSearchCriteria sc)
	{
		SearchResult<Transaction> results = new SearchResult<>();
		results.setPage(sc.getPage());
		results.setPageSize(sc.getPageSize());
		results.setSortOrder(sc.getSortOrder());
		sc.setPageSizeFully(sc.getPage(), sc.getPageSize());
		List<Transaction> transactions = transactionMapper.searchTransactions(sc);
		results.setResults(transactions);
		results.setTotalResults(transactionMapper.getTransactionsSearchCount(sc));
		return results;
	}

	@Transactional(rollbackFor = Exception.class)
	public Transaction updateTransaction(Transaction transaction)
	{
		transactionMapper.updateTransaction(transaction);
		return transaction;
	}

	@Transactional(rollbackFor = Exception.class)
	public void deleteTransaction(long id)
	{
		transactionMapper.deleteTransaction(id);
	}
}
