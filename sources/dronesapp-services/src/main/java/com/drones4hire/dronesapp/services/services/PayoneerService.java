package com.drones4hire.dronesapp.services.services;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.drones4hire.dronesapp.models.db.payments.Transaction;
import com.drones4hire.dronesapp.models.db.payments.Wallet;
import com.drones4hire.dronesapp.models.db.payments.WithdrawRequest;
import com.drones4hire.dronesapp.models.db.payments.WithdrawRequest.Status;
import com.drones4hire.dronesapp.services.exceptions.InavlidWaultAmountException;
import com.drones4hire.dronesapp.services.exceptions.PayoneerException;
import com.drones4hire.dronesapp.services.exceptions.ServiceException;

@Service
public class PayoneerService
{
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY hh:mm:ss");
	
	@Value("#{environmentProperties['drones4hire.payoneer.url']}")
	private String baseUrl;
	
	@Value("#{environmentProperties['drones4hire.payoneer.username']}")
	private String username;
	
	@Value("#{environmentProperties['drones4hire.payoneer.password']}")
	private String password;
	
	@Value("#{environmentProperties['drones4hire.payoneer.partnerId']}")
	private String partnerId;
	
	@Value("#{environmentProperties['drones4hire.payoneer.programId']}")
	private String programId;
	
	@Autowired
	private TransactionService transactionService;
	
	@Autowired
	private WalletService walletService;
	
	@Autowired 
	private WithdrawService withdrawService;
	
	public enum Methods {
		GetToken, PerformPayoutPayment
	}
	
	@Transactional(rollbackFor = Exception.class)
	public String signup(Wallet wallet) throws PayoneerException {
		String url = null;
		url = buildSignupURL(Methods.GetToken) + wallet.getWithdrawToken();
		return openURL(url);
	}
	
	@Transactional(rollbackFor = Exception.class)
	public void approvePayoneerAccount(String accountUUID) throws ServiceException {
		Wallet wallet = walletService.getWalletByWithdrawToken(accountUUID);
		wallet.setWithdrawEnabled(Boolean.TRUE);
		walletService.updateWallet(wallet);
	}
	
	/*
	 * Response status codes are: 
	 * 000 Processed successfully. 
	 * 002 Payee does not exist. 
	 * 003 Insufficient funds 
	 * 004 Payment ID {{Internal Payment ID}} already exists. 
	 * 011 Funding not enabled. 
	 * 010 Payee is inactive. 
	 * 030 Currency Mismatch 
	 * 001m Minimum/ maximum loading amount / Amount to load is less or equal to zero. 
	 * 002b Internal error. 
	 * 002t Internal error. 
	 * 002em Payee does not exist. 
	 * 006n Internal error.
	 * 007d Internal error. 
	 * 007f Internal error.
	 * 007g Internal error. 
	 * PE1028 Invalid currency
	 * 
	 */
	@Transactional(rollbackFor = Exception.class)
	public WithdrawRequest submitPaymentRequest(Long requestId) throws ServiceException {
		WithdrawRequest request = withdrawService.getWithdrawRequestById(requestId);
		Wallet wallet = walletService.getWalletByUserId(request.getUserId());
		if(wallet.getBalance().compareTo(request.getAmount()) < 0) {
			throw new InavlidWaultAmountException("Not enough funds on balance: " + wallet.getBalance());
		}
		String url = null;
		url = buildPaymentURL(Methods.PerformPayoutPayment) + request.getId() + 
				"&p6=" + wallet.getWithdrawToken() + "&p7=" + request.getAmount() +  
				"&p8" + request.getComment() + "&p9=" + dateFormat.format(new Date()) +
				"&p10=" + request.getCurrency();
		String response = openURL(url);
		String code = StringUtils.substringBetween(response, "<Status>", "</Status>");
		if(code.equals("000")) {
			request.setStatus(Status.PENDING);
			wallet.setBalance(wallet.getBalance().min(request.getAmount()));
			walletService.updateWallet(wallet);
		} else {
			request.setStatus(Status.FAILED);
		}
		withdrawService.updateWithdrawRequest(request);
		return request;
	}
	
	@Transactional(rollbackFor = Exception.class)
	public WithdrawRequest acceptPayment(String accountUUID, Long paymentId) throws ServiceException {
		Wallet wallet = walletService.getWalletByWithdrawToken(accountUUID);
		WithdrawRequest request = withdrawService.getWithdrawRequestById(paymentId);
		
		Transaction transaction = new Transaction();
		transaction.setAmount(request.getAmount());
		transaction.setCurrency(request.getCurrency());
		transaction.setStatus(Transaction.Status.COMPLETED);
		transaction.setType(Transaction.Type.WITHDRAW);
		transaction.setWalletId(wallet.getId());
		transaction.setPurpose(request.getComment());
		transactionService.createTransaction(transaction);
		
		request.setStatus(Status.APPROVED);
		withdrawService.updateWithdrawRequest(request);
		return request;
	}
	
	@Transactional(rollbackFor = Exception.class)
	public WithdrawRequest cancelPayment(String accountUUID, Long paymentId) throws ServiceException {
		WithdrawRequest request = withdrawService.getWithdrawRequestById(paymentId);
		
		Wallet wallet = walletService.getWalletByWithdrawToken(accountUUID);
		wallet.chageBalance(request.getAmount());
		walletService.updateWallet(wallet);
		
		request.setStatus(Status.CANCELLED);
		withdrawService.updateWithdrawRequest(request);
		return request;
	}
	
	private String openURL(String url) throws PayoneerException {
		URLConnection connection = null;
		BufferedReader reader = null;
		String inputLine = null;
		try {
			connection = new URL(url).openConnection(); 
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));  
			inputLine = reader.readLine();
		} catch(Exception e) {
			throw new PayoneerException("Can't get the link from Payoneer!" + e);
		} finally {
			IOUtils.close(connection);
			IOUtils.closeQuietly(reader);
		}
		return inputLine; 
	}
	
	private String buildSignupURL(Methods method) {
		return baseUrl + "?mname=" + method + "&p1=" + username + "&p2=" + password + 
				"&p3=" + partnerId + "&p4=";
	}
	
	private String buildPaymentURL(Methods method) {
		return baseUrl + "?mname=" + method + "&p1=" + username + "&p2=" + password + 
				"&p3=" + partnerId + "&p4=" + programId + "&p5=";
	}
}
