package com.drones4hire.dronesapp.services.services;

import com.drones4hire.dronesapp.dbaccess.dao.mysql.PortfolioItemMapper;
import com.drones4hire.dronesapp.dbaccess.dao.mysql.ServiceCategoryMapper;
import com.drones4hire.dronesapp.models.db.portfolio.PortfolioItem;
import com.drones4hire.dronesapp.models.db.services.ServiceCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PortfolioService
{
	@Autowired
	private PortfolioItemMapper portfolioItemMapper;

	@Autowired
	private ServiceCategoryMapper serviceCategoryMapper;

	@Transactional(rollbackFor = Exception.class)
	public PortfolioItem createPortfolioItem(PortfolioItem portfolioItem)
	{
		portfolioItemMapper.createPortfolioItem(portfolioItem);
		return portfolioItem;
	}

	@Transactional(rollbackFor = Exception.class)
	public PortfolioItem createPortfolioCategory(PortfolioItem portfolioItem, ServiceCategory serviceCategory)
	{
		portfolioItemMapper.createPortfolioCategory(portfolioItem, serviceCategory);
		return portfolioItem;
	}

	@Transactional(readOnly = true)
	public PortfolioItem getPortfolioItemById(long id)
	{
		return portfolioItemMapper.getPortfolioItemById(id);
	}

	@Transactional(readOnly = true)
	public List<PortfolioItem> getPortfolioItemsByUserId(long userId)
	{
		return portfolioItemMapper.getPortfolioItemsByUserId(userId);
	}

	@Transactional(readOnly = true)
	public List<PortfolioItem> getAllPortfolioItems()
	{
		return portfolioItemMapper.getAllPortfolioItems();
	}

	@Transactional(rollbackFor = Exception.class)
	public PortfolioItem updatePortfolioItem(PortfolioItem portfolioItem)
	{
		portfolioItemMapper.deletePortfolioCategoriesByPortfolioId(portfolioItem.getId());
		portfolioItemMapper.createPortfolioCategories(portfolioItem, portfolioItem.getServiceCategories());
		portfolioItemMapper.updatePortfolioItem(portfolioItem);
		return portfolioItem;
	}

	@Transactional(rollbackFor = Exception.class)
	public void deletePortfolioItem(long id)
	{
		portfolioItemMapper.deletePortfolioItem(id);
	}
}
