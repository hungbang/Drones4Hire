package com.drones4hire.dronesapp.dbaccess.dao.mysql;

import com.drones4hire.dronesapp.models.db.commons.Country;
import java.util.List;

public interface CountryMapper {

    void createCountry(Country country);

    Country getCountryById(long id);

    List<Country> getAllCountries();

    void updateCountry(Country country);

    void deleteCountry(long id);
}
