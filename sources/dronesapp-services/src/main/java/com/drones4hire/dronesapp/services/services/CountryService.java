package com.drones4hire.dronesapp.services.services;

import com.drones4hire.dronesapp.dbaccess.dao.mysql.CountryMapper;
import com.drones4hire.dronesapp.models.db.commons.Country;
import com.drones4hire.dronesapp.models.db.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CountryService
{

	@Autowired
	private CountryMapper countryMapper;

	@Transactional(rollbackFor = Exception.class)
	public Country createCountry(Country country)
	{
		countryMapper.createCountry(country);
		return country;
	}

	@Transactional(readOnly = true)
	public Country getCountryById(long id)
	{
		return countryMapper.getCountryById(id);
	}

	@Transactional(readOnly = true)
	public List<Country> getAllCountries()
	{
		return countryMapper.getAllCountries();
	}

	@Transactional(rollbackFor = Exception.class)
	public Country updateCountry(Country country)
	{
		countryMapper.updateCountry(country);
		return country;
	}

	@Transactional(rollbackFor = Exception.class)
	public void deleteCountry(long id)
	{
		countryMapper.deleteCountry(id);
	}
}
