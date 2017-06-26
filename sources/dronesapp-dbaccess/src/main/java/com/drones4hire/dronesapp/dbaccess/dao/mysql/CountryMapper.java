package com.drones4hire.dronesapp.dbaccess.dao.mysql;

import com.drones4hire.dronesapp.dbaccess.dao.mysql.search.CountrySearchCriteria;
import com.drones4hire.dronesapp.models.db.commons.Country;
import java.util.List;

public interface CountryMapper {

    void createCountry(Country country);

    Country getCountryById(long id);

    List<Country> getCountriesWithLicenseRequired();

    List<Country> searchCountries(CountrySearchCriteria sc);

    Integer getCountriesSearchCount(CountrySearchCriteria sc);

    List<Country> getAllCountries();

    void updateCountry(Country country);

    void deleteCountry(long id);
}
