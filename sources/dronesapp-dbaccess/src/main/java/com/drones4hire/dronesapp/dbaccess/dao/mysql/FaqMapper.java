package com.drones4hire.dronesapp.dbaccess.dao.mysql;

import com.drones4hire.dronesapp.models.db.content.Faq;

import java.util.List;

public interface FaqMapper
{

	void createFaq(Faq Faq);

	Faq getFaqById(long id);

	List<Faq> getAllFaqs();

	void updateFaq(Faq Faq);

	void deleteFaq(long id);
}
