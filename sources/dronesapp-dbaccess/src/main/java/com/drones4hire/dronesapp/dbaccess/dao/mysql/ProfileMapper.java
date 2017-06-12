package com.drones4hire.dronesapp.dbaccess.dao.mysql;

import java.util.List;

import com.drones4hire.dronesapp.models.db.users.Profile;

public interface ProfileMapper
{
	void createProfile(Profile profile);

	Profile getProfileById(long id);
	
	Profile getProfileByUserId(long userId);

	List<Profile> getAllProfiles();

	List<Profile> getAllPublicProfiles();

	void updateProfile(Profile profile);

	void deleteProfile(long id);
}
