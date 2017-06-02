package com.drones4hire.admin.services;

import java.util.Locale;

import org.apache.http.auth.InvalidCredentialsException;
import org.jasypt.exceptions.EncryptionOperationNotPossibleException;
import org.jasypt.util.password.PasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.drones4hire.dronesapp.models.db.users.User;
import com.drones4hire.dronesapp.services.exceptions.ServiceException;
import com.drones4hire.dronesapp.services.exceptions.UserNotFoundException;
import com.drones4hire.dronesapp.services.services.UserService;
import com.drones4hire.dronesapp.services.util.Tokenizer;

@Service
public class AuthenticationService
{
//
//	private final String CSV_DOWNLOADING_PASSWORD = "D3t6ww";
//
//	@Autowired
//	private UserService userService;
//
//	@Autowired
//	private SecurityTokenService securityTokenService;
//
//	@Autowired
//	private EconomicalGroupService economicalGroupService;
//	
//	@Autowired
//	private Tokenizer tokenizer;
//
//	@Autowired
//	private PasswordEncryptor passwordEncryptor;
//
//	@Autowired
//	private IAuthCache authCache;
//	
//	@Autowired
//	private ClientVersionSwitch clientVersionSwitch;
//
//	@Autowired
//	private SmsConfirmationService smsConfirmationService;
//	
//	@Transactional
//	public SecurityToken login(String email, String password, UserRole role, double version, Locale locale) throws ServiceException {
//		User user = userService.getUserByEmailAndRole(email, role);
//		try {
//			if(user == null) {
//				throw new AuthenticationException("User with email: " + email + " not found.");
//			}
//			if (passwordEncryptor.checkPassword(password, user.getPassword())) {
//				if(version > 0 && (version < clientVersionSwitch.getMinClientVersion())) {
//					throw new UnsupportedClientVersionException();
//				}
//			} else {
//				throw new InvalidCredentialsException();
//			}
//		} catch(EncryptionOperationNotPossibleException e) {
//			throw new InvalidCredentialsException();
//		}
//		return syncSecurityToken(user, locale);
//	}
//	
//	private SecurityToken syncSecurityToken(User user, Locale locale) throws ServiceException
//	{
//		SecurityToken securityToken = securityTokenService.getSecurityTokenByUserId(user.getId());
//		if (securityToken != null)
//		{
//			String previousToken = securityToken.getToken();
//			securityToken.setToken(tokenizer.randomToken());
//			securityToken.setRole(user.getRole().name());
//			securityToken.setUserId(user.getId());
//			securityToken.setJid(user.getJid());
//			securityToken.setStatus(user.getStatus());
//			securityToken.setLocale(locale);
//			securityToken.setUnit(user.getUnit());
//			securityTokenService.updateSecurityToken(securityToken);
//			authCache.deleteUserByToken(previousToken);
//		}
//		else
//		{
//			securityToken = new SecurityToken();
//			securityToken.setToken(tokenizer.randomToken());
//			securityToken.setRole(user.getRole().name());
//			securityToken.setUserId(user.getId());
//			securityToken.setJid(user.getJid());
//			securityToken.setStatus(user.getStatus());
//			securityToken.setLocale(locale);
//			securityToken.setUnit(user.getUnit());
//			securityTokenService.createSecurityToken(securityToken);
//		}
//		
//		if (!securityToken.getLocale().equals(user.getLocale()))
//		{
//			user.setLocale(locale);
//			userService.updateLocale(user.getId(), securityToken.getLocale());
//		}
//		
//		if(user.getCityId() != 0)
//		{
//			EconomicalGroup eg = economicalGroupService.getEconomicalGroupByCityId(user.getCityId());
//			securityToken.setCardPaymentEnabled(eg.isPaymentCardsEnabled());
//			securityToken.setPaymentProvider(eg.getPaymentProvider());
//		}
//		else
//		{
//			securityToken.setCardPaymentEnabled(false);
//		}
//		
//		if(user.getCompanyId() != null)
//		{
//			securityToken.setCompanyId(user.getCompanyId());
//		}
//		
//		authCache.associateUserWithToken(securityToken.getToken(), user);
//		
//		return securityToken;
//	}
//	
//	@Transactional
//	public void logout(String token) throws ServiceException
//	{
//		securityTokenService.deleteSecurityTokenByToken(token);
//		authCache.deleteUserByToken(token);
//	}
//
//	@Transactional
//	public User getUserByToken(String token) throws ServiceException
//	{
//		User user = null;
//		user = authCache.getUserByToken(token);
//		if (user == null)
//		{
//			SecurityToken securityToken = securityTokenService.getSecurityTokenByToken(token);
//			if (securityToken != null)
//			{
//				user = userService.getUserById(securityToken.getUserId());
//			}
//		}
//		return user;
//	}
//
//	@Transactional
//	public User passengerEnterByPhone(String phone) throws ServiceException {
//		User user = userService.getUserByPhoneAndRole(phone, UserRole.PASSENGER);
//		if (user == null) {
//			throw new UserNotFoundException("Passenger with phone '" + phone + "' not found.");
//		}
//		if(1 == (user.getStatus() & User.CHECK_PHONE)) {
//			throw new AuthenticationException("Not confirmed phone");
//		}
//		SmsConfirmation sms = smsConfirmationService.sendSmsConfirmationCode(user.getId(), user.getPhone());
//		user.setResendSMSToken(sms.getResendToken());
//		return user;
//	}
//
//	public Boolean checkCSVdownload(String encodedPassword) throws Exception {
//		String decodedPassword = BasicAuthParser.decodeBase64String(encodedPassword);
//		if (decodedPassword.equals(CSV_DOWNLOADING_PASSWORD)) {
//			return true;
//		} else {
//			return false;
//		}
//	}
}
