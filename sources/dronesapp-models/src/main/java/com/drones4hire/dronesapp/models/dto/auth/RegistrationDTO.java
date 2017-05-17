package com.drones4hire.dronesapp.models.dto.auth;

import java.io.Serializable;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

import com.drones4hire.dronesapp.models.db.users.Group.Role;

public class RegistrationDTO implements Serializable
{
	private static final long serialVersionUID = 1567014101763491651L;
	
	@NotNull(message = "First name shouldn't be null.")
	private String firstName;
	
	@NotNull(message = "Last name shouldn't be null")
	private String lastName;
	
	@NotNull(message = "User name shouldn't be null")
	private String username;
	
	@NotNull(message = "Email address shouldn't be null")
	@Email(message = "Wrong email")
	private String email;
	
	@NotNull(message = "Password shouldn't be null")
	@Size(min = 6, message = "Wrong password size")
	private String password;
	
	@NotNull(message = "Confirm password shouldn't be null")
	@Size(min = 6, message = "Wrong confirm password size")
	private String confirmPassword;
	
	@NotNull(message = "Role shouldn't be null")
	private Role role;

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getConfirmPassword()
	{
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword)
	{
		this.confirmPassword = confirmPassword;
	}

	public Role getRole()
	{
		return role;
	}

	public void setRole(Role role)
	{
		this.role = role;
	}
	
	@AssertTrue(message = "Password confirmation not matching")
	public boolean isConfirmationValid()
	{
		return password != null && password.equals(confirmPassword);
	}
}
