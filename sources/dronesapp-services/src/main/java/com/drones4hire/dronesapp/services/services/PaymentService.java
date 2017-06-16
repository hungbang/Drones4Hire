package com.drones4hire.dronesapp.services.services;

import com.drones4hire.dronesapp.models.db.commons.Currency;
import com.drones4hire.dronesapp.models.db.payments.Transaction;
import com.drones4hire.dronesapp.models.db.payments.Wallet;
import com.drones4hire.dronesapp.models.db.projects.Bid;
import com.drones4hire.dronesapp.models.db.projects.Project;
import com.drones4hire.dronesapp.services.exceptions.ForbiddenOperationException;
import com.drones4hire.dronesapp.services.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class PaymentService
{

	@Autowired
	private BidService bidService;

	@Autowired
	private ProjectService projectService;

	@Autowired
	private WalletService walletService;

	@Autowired
	private TransactionService transactionService;

	@Autowired
	private FeeService feeService;

	@Transactional(rollbackFor = Exception.class)
	public Transaction releasePayment(Long bidId, Long principalId) throws ServiceException
	{
		Bid bid = bidService.getBidById(bidId);
		if (bid == null)
		{
			throw new ServiceException("Bid with id: " + bidId + " not found.");
		}
		Project project = projectService.getProjectById(bid.getProjectId(), principalId);
		if (!project.getClientId().equals(principalId) || !project.getPilotId().equals(bid.getUser().getId())
				|| !project.getStatus().equals(Project.Status.IN_PROGRESS))
		{
			throw new ForbiddenOperationException();
		}
		Wallet wallet = walletService.getWalletByUserId(bid.getUser().getId());
		BigDecimal feeAmount = feeService.getFeeAmount(bid);
		BigDecimal trAmountWithoutFee = bid.getAmount().subtract(feeAmount);

		Transaction feeTransaction = new Transaction();
		feeTransaction.setWalletId(wallet.getId());
		feeTransaction.setAmount(feeAmount);
		feeTransaction.setType(Transaction.Type.SERVICE_FEE);
		feeTransaction.setProjectId(bid.getProjectId());
		feeTransaction.setStatus(Transaction.Status.COMPLETED);
		feeTransaction.setPurpose("Project payment fee");
		feeTransaction.setCurrency(Currency.USD);
		transactionService.createTransaction(feeTransaction);

		Transaction projectPaymentTransaction = new Transaction();
		projectPaymentTransaction.setWalletId(wallet.getId());
		projectPaymentTransaction.setAmount(trAmountWithoutFee);
		projectPaymentTransaction.setType(Transaction.Type.PAYMENT_RELEASED);
		projectPaymentTransaction.setProjectId(bid.getProjectId());
		projectPaymentTransaction.setStatus(Transaction.Status.COMPLETED);
		projectPaymentTransaction.setPurpose("Project payment");
		projectPaymentTransaction.setCurrency(Currency.USD);
		transactionService.createTransaction(projectPaymentTransaction);
		wallet.setBalance(wallet.getBalance().add(trAmountWithoutFee));
		walletService.updateWallet(wallet);

		project.setStatus(Project.Status.COMPLETED);
		projectService.updateProject(project);
		return projectPaymentTransaction;
	}
}
