package com.drones4hire.dronesapp.dbaccess.dao.mysql;

import java.util.List;

import com.drones4hire.dronesapp.models.db.portfolio.PortfolioItem;
import com.drones4hire.dronesapp.models.db.services.ServiceCategory;

public interface PortfolioItemMapper
{
	void createPortfolioItem(PortfolioItem portfolioItem);
	
	void createPortfolioCategory(PortfolioItem portfolio, ServiceCategory serviceCategory);

	PortfolioItem getPortfolioItemById(long id);
	
	PortfolioItem getPortfolioItemByUserId(long userId);

	List<PortfolioItem> getAllPortfolioItems();

	void updatePortfolioItem(PortfolioItem portfolioItem);

	void deletePortfolioItem(long id);
}
