package com.drones4hire.dronesapp.services.services.util.model.restore;

import com.drones4hire.dronesapp.models.db.users.Group;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User
{
	@JsonProperty("user_id")
	private Long id;

	@JsonProperty("fname")
	private String firstName;

	@JsonProperty("lname")
	private String lastName;

	@JsonProperty("email")
	private String email;

	@JsonProperty("user_name")
	private String username;

	@JsonProperty("user_type_id")
	private Group.Role role;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

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

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public Group.Role getRole()
	{
		return role;
	}

	public void setRole(String typeId)
	{
		this.role = getUserRoleByTypeId(typeId);
	}

	private Group.Role getUserRoleByTypeId(String typeId)
	{
		Group.Role role = null;
		switch (typeId)
		{
		case "3":
			role = Group.Role.ROLE_PILOT;
			break;
		case "4":
			role = Group.Role.ROLE_CLIENT;
			break;
		default:
			break;
		}
		return role;
	}
}
