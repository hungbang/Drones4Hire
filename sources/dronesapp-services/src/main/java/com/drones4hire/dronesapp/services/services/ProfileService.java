package com.drones4hire.dronesapp.services.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.drones4hire.dronesapp.dbaccess.dao.mysql.ProfileMapper;
import com.drones4hire.dronesapp.models.db.users.Profile;
import com.drones4hire.dronesapp.models.db.users.User;

@Service
public class ProfileService
{

	@Autowired
	private ProfileMapper profileMapper;

	@Transactional(rollbackFor = Exception.class)
	public Profile createProfile(Profile profile)
	{
		profileMapper.createProfile(profile);
		return profile;
	}
	
	@Transactional(rollbackFor = Exception.class)
	public Profile createDefaultProfile(User user)
	{
		Profile profile = new Profile(user.getId());
		return createProfile(profile);
	}

	@Transactional(readOnly = true)
	public Profile getProfileById(long id)
	{
		return profileMapper.getProfileById(id);
	}

	@Transactional(readOnly = true)
	public List<Profile> getAllProfiles()
	{
		return profileMapper.getAllProfiles();
	}

	@Transactional(rollbackFor = Exception.class)
	public Profile updateProfile(Profile profile)
	{
		profileMapper.updateProfile(profile);
		return profile;
	}

	@Transactional(rollbackFor = Exception.class)
	public void deleteProfile(long id)
	{
		profileMapper.deleteProfile(id);
	}
}
