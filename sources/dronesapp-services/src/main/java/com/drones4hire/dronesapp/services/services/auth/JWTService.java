package com.drones4hire.dronesapp.services.services.auth;

import java.util.Calendar;
import java.util.List;

import com.drones4hire.dronesapp.models.db.users.Group;
import com.drones4hire.dronesapp.models.db.users.User;
import com.drones4hire.dronesapp.models.db.users.Group.Role;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTService
{
	private String secret;
	private Integer authTokenExp;
	private Integer refreshTokenExp;
	
	public JWTService(String secret, Integer authTokenExp, Integer refreshTokenExp)
	{
		this.secret = secret;
		this.authTokenExp = authTokenExp;
		this.refreshTokenExp = refreshTokenExp;
	}
	
	/**
	 * Generates JWT auth token storing id, username, email, roles of the user and specifies expiration date.
	 * @param user that is used for token generation
	 * @return generated JWT token
	 */
	public String generateAuthToken(User user)
	{
		Claims claims = Jwts.claims().setSubject(user.getId().toString());
		claims.put("username", user.getUsername());
		claims.put("email", user.getEmail());
		claims.put("enabled", user.isEnabled());
		claims.put("roles", user.getRoles());
		return buildToken(claims, authTokenExp);
	}

	/**
	 * Parses user details from JWT token.
	 * @param JWT token to parse
	 * @return retrieved user details
	 */
	@SuppressWarnings("unchecked")
	public User parseAuthToken(String token)
	{
		Claims body = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		
		User user = new User();
		user.setId(Long.valueOf(body.getSubject()));
		user.setEmail((String)body.get("email"));
		user.setUsername((String)body.get("username"));
		user.setEnabled((Boolean)body.get("enabled"));
		for(String role : (List<String>)body.get("roles"))
		{
			user.getGroups().add(new Group(Role.valueOf(role)));
		}
		
		return user;
	}
	
	/**
	 * Verifies JWT refresh token.
	 * @param refresh token
	 * @param user to verify
	 * @return verification status
	 */
	public User parseRefreshToken(String token)
	{
		Claims body = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		
		User user = new User();
		user.setId(Long.valueOf(body.getSubject()));
		user.setPassword((String)body.get("password"));
		
		return user;
	}

	/**
	 * Generates JWT refresh token storing id, username, password of the user and specifies expiration date.
	 * @param user that is used for token generation
	 * @return generated JWT token
	 */
	public String generateRefreshToken(User user)
	{
		Claims claims = Jwts.claims().setSubject(user.getId().toString());
		claims.put("password", user.getPassword());
		return buildToken(claims, refreshTokenExp);
	}
	
	public String generateEmailToken(Long userId, String newEmail)
	{
		Claims claims = Jwts.claims().setSubject(userId.toString());
		claims.put("email", newEmail);
		return buildToken(claims, authTokenExp * 2);
	}
	
	public User parseEmailToken(String token)
	{
		Claims body = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		
		User user = new User();
		user.setId(Long.valueOf(body.getSubject()));
		user.setEmail((String)body.get("email"));
		
		return user;
	}
	
	private String buildToken(Claims claims, Integer exp)
	{
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MINUTE, exp);
		return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, secret).setExpiration(c.getTime()).compact();
	}
	
	public Integer getExpiration()
	{
		return authTokenExp;
	}
}