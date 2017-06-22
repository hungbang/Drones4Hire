package com.drones4hire.dronesapp.ws.controllers;

import javax.validation.Valid;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.drones4hire.dronesapp.dbaccess.dao.mysql.search.SearchResult;
import com.drones4hire.dronesapp.dbaccess.dao.mysql.search.TransactionSearchCriteria;
import com.drones4hire.dronesapp.models.db.payments.Transaction;
import com.drones4hire.dronesapp.models.db.payments.Wallet;
import com.drones4hire.dronesapp.models.dto.WalletDTO;
import com.drones4hire.dronesapp.services.exceptions.ForbiddenOperationException;
import com.drones4hire.dronesapp.services.services.TransactionService;
import com.drones4hire.dronesapp.services.services.WalletService;
import com.drones4hire.dronesapp.ws.swagger.annotations.ResponseStatusDetails;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Controller
@Api(value = "Wallet API")
@CrossOrigin
@RequestMapping("api/v1/wallets")
public class WalletController extends AbstractController
{

	@Autowired
	private WalletService walletService;

	@Autowired
	private TransactionService transactionService;

	@Autowired
	private Mapper mapper;

	@ResponseStatusDetails
	@ApiOperation(value = "Get wallet", nickname = "getWallet", code = 200, httpMethod = "GET", response = WalletDTO.class)
	@ApiImplicitParams(
			{ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody WalletDTO getWallet()
	{
		return mapper.map(walletService.getWalletByUserId(getPrincipal().getId()), WalletDTO.class);
	}

	@ResponseStatusDetails
	@ApiOperation(value = "Search wallet transactions", nickname = "searchWalletTransactions", code = 201, httpMethod = "POST", response = SearchResult.class)
	@ApiImplicitParams(
			{ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(value = "transactions/search", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody SearchResult<Transaction> searchWalletTransactions(@Valid @RequestBody TransactionSearchCriteria sc)
			throws ForbiddenOperationException
	{
		Wallet wallet = walletService.getWalletByUserId(getPrincipal().getId());
		sc.setWalletId(wallet.getId());
		return transactionService.searchTransactions(sc);
	}

	@ResponseStatusDetails
	@ApiOperation(value = "Update wallet transactions", nickname = "updateWalletTransactions", code = 200, httpMethod = "PUT", response = Transaction.class)
	@ApiImplicitParams(
			{ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@ResponseStatus(HttpStatus.CREATED)
	@Secured({"ROLE_ADMIN"})
	@RequestMapping(value = "transactions", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Transaction updateWalletTransactions(@RequestBody Transaction transaction)
			throws ForbiddenOperationException
	{
		return transactionService.updateTransaction(transaction);
	}

	@ResponseStatusDetails
	@ApiOperation(value = "Get transaction by id", nickname = "getTransactionById", code = 200, httpMethod = "GET", response = Transaction.class)
	@ApiImplicitParams(
			{ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "transactions/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Transaction getTransactionById(
			@ApiParam(value = "Id of the transaction", required = true) @PathVariable(value = "id") long id)
			throws ForbiddenOperationException
	{
		Transaction transaction = transactionService.getTransactionById(id);
		Wallet wallet = walletService.getWalletById(transaction.getWalletId());
		checkPrincipalPermissions(wallet.getUserId());
		return transaction;
	}
}
