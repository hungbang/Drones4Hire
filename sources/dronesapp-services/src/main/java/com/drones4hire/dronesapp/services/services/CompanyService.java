package com.drones4hire.dronesapp.services.services;

import com.drones4hire.dronesapp.dbaccess.dao.mysql.CompanyMapper;
import com.drones4hire.dronesapp.models.db.users.Company;
import com.drones4hire.dronesapp.models.db.users.PilotLicense;
import com.drones4hire.dronesapp.models.db.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CompanyService
{

	private static final String DEFAULT_COMPANY_NAME = "default";

	@Autowired
	private CompanyMapper companyMapper;

	@Transactional(rollbackFor = Exception.class)
	public Company createCompany(Company company)
	{
		companyMapper.createCompany(company);
		return company;
	}

	@Transactional(rollbackFor = Exception.class)
	public Company createDefaultCompany(User user)
	{
		Company company = new Company(user.getId(), DEFAULT_COMPANY_NAME);
		return createCompany(company);
	}

	@Transactional(readOnly = true)
	public Company getCompanyById(long id)
	{
		return companyMapper.getCompanyById(id);
	}

	@Transactional(readOnly = true)
	public Company getCompanyByUserId(long userId)
	{
		return companyMapper.getCompanyByUserId(userId);
	}

	@Transactional(readOnly = true)
	public List<Company> getAllCompanys()
	{
		return companyMapper.getAllCompanies();
	}

	@Transactional(rollbackFor = Exception.class)
	public Company updateCompany(Company company)
	{
		companyMapper.updateCompany(company);
		return company;
	}

	@Transactional(rollbackFor = Exception.class)
	public void deleteCompany(long id)
	{
		companyMapper.deleteCompany(id);
	}
}
