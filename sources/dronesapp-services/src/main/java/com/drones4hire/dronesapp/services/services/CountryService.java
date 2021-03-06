package com.drones4hire.dronesapp.services.services;

import java.util.List;

import com.drones4hire.dronesapp.dbaccess.dao.mysql.search.CountrySearchCriteria;
import com.drones4hire.dronesapp.dbaccess.dao.mysql.search.SearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.drones4hire.dronesapp.dbaccess.dao.mysql.CountryMapper;
import com.drones4hire.dronesapp.models.db.commons.Country;

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
	public SearchResult<Country> search(CountrySearchCriteria sc)
	{
		SearchResult<Country> result = new SearchResult<>();
		result.setPage(sc.getPage());
		result.setPageSize(sc.getPageSize());
		result.setSortOrder(sc.getSortOrder());
		sc.setPageSizeFully(sc.getPage(), sc.getPageSize());
		result.setResults(countryMapper.searchCountries(sc));
		result.setTotalResults(countryMapper.getCountriesSearchCount(sc));
		return result;
	}

	@Transactional(readOnly = true)
	public List<Country> getCountriesWithLicenseRequired()
	{
		return countryMapper.getCountriesWithLicenseRequired();
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
