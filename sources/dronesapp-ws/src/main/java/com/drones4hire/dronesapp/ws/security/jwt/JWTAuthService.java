package com.drones4hire.dronesapp.ws.security.jwt;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

//import com.drones4hire.dronesapp.models.db.User;
//import com.drones4hire.dronesapp.services.services.auth.JWTService;
//import com.drones4hire.dronesapp.ws.security.SecuredUser;

@Component
public class JWTAuthService 
//implements UserDetailsService
{
//	@Autowired
	//private JWTService jwtService;
	
	/*@Override
	public UserDetails loadUserByUsername(String token) throws UsernameNotFoundException
	{
		User user = null;
		try
		{
			user = jwtService.parseAuthToken(token);
		} 
		catch (Exception e)
		{
			throw new UsernameNotFoundException("User not found", e);
		}
		return new SecuredUser(user.getId(), 
							   user.getUsername(), StringUtils.EMPTY, 
							   user.getRoles());
							   
	}*/
}