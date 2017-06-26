package com.drones4hire.dronesapp.services.services;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.drones4hire.dronesapp.models.db.payments.Wallet;
import com.drones4hire.dronesapp.models.db.users.Group.Role;
import com.drones4hire.dronesapp.models.db.users.User;
import com.drones4hire.dronesapp.services.exceptions.PayoneerException;
import com.drones4hire.dronesapp.services.exceptions.ServiceException;

@Service
public class PayoneerService
{
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
	private WalletService walletService;
	
	@Autowired
	private UserService userService;
	
	public enum Methods {
		GetToken, PerformPayoutPayment
	}
	
	@Transactional(rollbackFor = Exception.class)
	public String signup(User user) throws PayoneerException {
		String url = null;
		url = buildSignupURL(Methods.GetToken) + walletService.getWalletByUserId(user.getId()).getWithdrawToken();
		return openURL(url);
	}
	
	@Transactional(rollbackFor = Exception.class)
	public String submitPaymentRequest(User user) throws PayoneerException {
		String url = null;
		if(user.getRoles().contains(Role.ROLE_PILOT)) {
			url = buildPaymentURL(Methods.PerformPayoutPayment);
			return openURL(url);
		}
		return url;
	}
	
	@Transactional(rollbackFor = Exception.class)
	public void approvePayoneerAccount(String accountUUID) throws ServiceException {
		Wallet wallet = walletService.getWalletByWithdrawToken(accountUUID);
		User user = userService.getUserById(wallet.getUserId());
		user.setWithdrawEnabled(Boolean.TRUE);
		userService.updateUser(user);
	}
	
	@Transactional(rollbackFor = Exception.class)
	public void acceptPayment(String accountUUID) throws ServiceException {
		Wallet wallet = walletService.getWalletByWithdrawToken(accountUUID);
//		JAXBContext jaxbContext = JAXBContext.newInstance(User.class);
//		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
//		StringReader reader = new StringReader("xml string here");
//		User customer = (User) jaxbUnmarshaller.unmarshal(reader); 
//		reader.close();
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
				"&p3=" + partnerId +	"&p4=";
	}
	
	private String buildPaymentURL(Methods method) {
		return baseUrl + "?mname=" + method + "&p1=" + username + "&p2=" + password + 
				"&p3=" + partnerId +	"&p4=" + programId + "&p5=";
	}
}
