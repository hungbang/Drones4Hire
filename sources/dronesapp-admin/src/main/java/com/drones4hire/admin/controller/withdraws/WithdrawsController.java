package com.drones4hire.admin.controller.withdraws;

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

import com.drones4hire.admin.controller.AbstractController;
import com.drones4hire.dronesapp.dbaccess.dao.mysql.search.SearchResult;
import com.drones4hire.dronesapp.dbaccess.dao.mysql.search.WithdrawSearchCriteria;
import com.drones4hire.dronesapp.models.db.payments.WithdrawRequest;
import com.drones4hire.dronesapp.services.exceptions.ServiceException;
import com.drones4hire.dronesapp.services.services.WithdrawService;

@Controller
@RequestMapping("withdraws")
public class WithdrawsController extends AbstractController
{
	@Autowired
	private WithdrawService withdrawService;
	
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
}
