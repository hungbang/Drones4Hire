package com.drones4hire.dronesapp.services.services.auth;

import java.util.Calendar;
import java.util.List;

import com.drones4hire.dronesapp.models.db.users.Group;
import com.drones4hire.dronesapp.models.db.users.Group.Role;
import com.drones4hire.dronesapp.models.db.users.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTService
{
	private String secret;
	private Integer authTokenExp;
	private Integer refreshTokenExp;
	private Integer confirmEmailTokenExp;
	private Integer changePassworsTokenExp;
	
	public JWTService(String secret, Integer authTokenExp, Integer refreshTokenExp, Integer confirmEmailTokenExp, Integer changePassworsTokenExp)
	{
		this.secret = secret;
		this.authTokenExp = authTokenExp;
		this.refreshTokenExp = refreshTokenExp;
		this.confirmEmailTokenExp = confirmEmailTokenExp;
		this.changePassworsTokenExp = changePassworsTokenExp;
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
	
	public String generateConfirmEmailToken(User user)
	{
		Claims claims = Jwts.claims().setSubject(user.getId().toString());
		return buildToken(claims, confirmEmailTokenExp);
	}
	
	public User readConfirmEmailToken(String token)
	{
		Claims body = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		User user = new User();
		user.setId(Long.valueOf(body.getSubject()));
		return user;
	}
	
	public String generateChangePasswordToken(User user)
	{
		Claims claims = Jwts.claims().setSubject(user.getId().toString());
		return buildToken(claims, changePassworsTokenExp);
	}
	
	public User readChangePasswordToken(String token)
	{
		Claims body = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		User user = new User();
		user.setId(Long.valueOf(body.getSubject()));
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

	public String generateUserRestoreToken(com.drones4hire.dronesapp.services.services.util.model.restore.User user)
	{
		Claims claims = Jwts.claims().setSubject(user.getId().toString());
		claims.put("firstName", user.getFirstName());
		claims.put("lastName", user.getLastName());
		claims.put("email", user.getEmail());
		claims.put("username", user.getUsername());
		claims.put("role", user.getRole());
		authTokenExp = 604800; // one week
		return buildToken(claims, authTokenExp);
	}

	public com.drones4hire.dronesapp.services.services.util.model.restore.User parseUserRestoreToken(String token)
	{
		Claims body = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();

		com.drones4hire.dronesapp.services.services.util.model.restore.User user = new com.drones4hire.dronesapp.services.services.util.model.restore.User();
		user.setId(Long.valueOf(body.getSubject()));
		user.setFirstName((String)body.get("firstName"));
		user.setLastName((String)body.get("lastName"));
		user.setEmail((String)body.get("email"));
		user.setUsername((String)body.get("username"));
		user.setRole(Role.valueOf((String)body.get("role")));

		return user;
	}
}