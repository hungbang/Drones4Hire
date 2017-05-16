package com.drones4hire.dronesapp.dbaccess.dao.mysql;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.drones4hire.dronesapp.models.db.portfolio.PortfolioItem;
import com.drones4hire.dronesapp.models.db.services.ServiceCategory;

public interface PortfolioItemMapper
{
	void createPortfolioItem(PortfolioItem portfolioItem);
	
	void createPortfolioCategory(@Param("portfolio") PortfolioItem portfolio, @Param("serviceCategory") ServiceCategory serviceCategory);

	PortfolioItem getPortfolioItemById(long id);
	
	PortfolioItem getPortfolioItemByUserId(long userId);

	List<PortfolioItem> getAllPortfolioItems();

	void updatePortfolioItem(PortfolioItem portfolioItem);

	void deletePortfolioCategory(@Param("portfolioId") Long portfolioId, @Param("categoryId") Long serviceCategory);
	
	void deletePortfolioItem(long id);
}
