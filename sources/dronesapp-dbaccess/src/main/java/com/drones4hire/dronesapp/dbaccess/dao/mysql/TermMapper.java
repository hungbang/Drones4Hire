package com.drones4hire.dronesapp.dbaccess.dao.mysql;

import com.drones4hire.dronesapp.models.db.content.Term;

import java.util.List;

public interface TermMapper
{

	void createTerm(Term Term);

	Term getTermById(long id);

	List<Term> getAllTerms();

	void updateTerm(Term Term);

	void deleteTerm(long id);
}
