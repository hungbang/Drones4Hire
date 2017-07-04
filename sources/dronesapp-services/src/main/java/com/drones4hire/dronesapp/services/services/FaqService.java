package com.drones4hire.dronesapp.services.services;

import com.drones4hire.dronesapp.dbaccess.dao.mysql.FaqMapper;
import com.drones4hire.dronesapp.models.db.content.Faq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FaqService
{

	@Autowired
	private FaqMapper faqMapper;

	@Transactional(rollbackFor = Exception.class)
	public Faq createFaq(Faq faq)
	{
		faqMapper.createFaq(faq);
		return faq;
	}

	@Transactional(readOnly = true)
	public Faq getFaqById(long id)
	{
		return faqMapper.getFaqById(id);
	}

	@Transactional(readOnly = true)
	public List<Faq> getAllFaqs()
	{
		return faqMapper.getAllFaqs();
	}

	@Transactional(rollbackFor = Exception.class)
	public Faq updateFaq(Faq faq)
	{
		faqMapper.updateFaq(faq);
		return faq;
	}

	@Transactional(rollbackFor = Exception.class)
	public void deleteFaq(long id)
	{
		faqMapper.deleteFaq(id);
	}
}
