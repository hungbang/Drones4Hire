package com.drones4hire.dronesapp.dbaccess.dao.mysql;

import java.util.List;

import com.drones4hire.dronesapp.models.db.users.Company;

public interface CompanyMapper
{
	void createCompany(Company company);

	Company getCompanyById(long id);
	
	Company getCompanyByUserId(long userId);

	List<Company> getAllCompanies();

	void updateCompany(Company license);

	void deleteCompany(long id);
}
