package com.drones4hire.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.drones4hire.dronesapp.dbaccess.dao.mysql.search.SearchResult;
import com.drones4hire.dronesapp.dbaccess.dao.mysql.search.WithdrawSearchCriteria;
import com.drones4hire.dronesapp.models.db.payments.WithdrawRequest;
import com.drones4hire.dronesapp.models.db.payments.WithdrawRequest.Status;
import com.drones4hire.dronesapp.services.exceptions.ServiceException;
import com.drones4hire.dronesapp.services.services.PayoneerService;
import com.drones4hire.dronesapp.services.services.WithdrawService;

@Controller
@RequestMapping("withdraws")
public class WithdrawsController extends AbstractController
{
	@Autowired
	private WithdrawService withdrawService;
	
	@Autowired
	private PayoneerService payoneerService;
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public ModelAndView openClientsPage() throws ServiceException
	{
		return new ModelAndView("withdraws/index");
	}
	
	@RequestMapping(value = "search", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody SearchResult<WithdrawRequest> search(@RequestBody WithdrawSearchCriteria withdrawSearchCriteria) throws Exception
	{
		return withdrawService.search(withdrawSearchCriteria);
	}
	
	@RequestMapping(value = "accept", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody WithdrawRequest acceptWithdraw(@RequestBody WithdrawRequest request) throws Exception
	{
		return payoneerService.submitPaymentRequest(request.getId());
	}
	
	@RequestMapping(value = "cancel", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody WithdrawRequest cancelWithdraw(@RequestBody WithdrawRequest request) throws Exception
	{
		WithdrawRequest currentRequest = withdrawService.getWithdrawRequestById(request.getId());
		currentRequest.setStatus(Status.CANCELED);
		currentRequest.setAdminComment(request.getAdminComment());
		return withdrawService.updateWithdrawRequest(currentRequest);
	}
}
