package com.drones4hire.admin.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.drones4hire.dronesapp.models.db.users.Group.Role;

public class SecuredUser implements UserDetails
{
	private static final long serialVersionUID = 1024356863633107004L;

	private long id;
	private String email;
	private String password;
	private boolean enabled;
	private List<GrantedAuthority> authorities = new ArrayList<>();

	public SecuredUser(String email, List<Role> roles)
	{
		this.email = email;
		for(Role role : roles)
		{
			authorities.add(new SimpleGrantedAuthority(role.name()));
		}
	}
	
	public SecuredUser(long id, String username, String password, List<Role> roles, boolean enabled)
	{
		this(username, roles);
		this.id = id;
		this.password = password;
		this.enabled = enabled;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities()
	{
		return this.authorities;
	}

	public long getId()
	{
		return id;
	}

	@Override
	public String getUsername()
	{
		return email;
	}

	@Override
	public String getPassword()
	{
		return password;
	}

	@Override
	public boolean isAccountNonExpired()
	{
		return true;
	}

	@Override
	public boolean isAccountNonLocked()
	{
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired()
	{
		return true;
	}

	@Override
	public boolean isEnabled()
	{
		return enabled;
	}
}
