package com.drones4hire.dronesapp.services.services;

import com.drones4hire.dronesapp.dbaccess.dao.mysql.TermMapper;
import com.drones4hire.dronesapp.models.db.content.Term;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TermService
{

	@Autowired
	private TermMapper termMapper;

	@Transactional(rollbackFor = Exception.class)
	public Term createTerm(Term term)
	{
		termMapper.createTerm(term);
		return term;
	}

	@Transactional(readOnly = true)
	public Term getTermById(long id)
	{
		return termMapper.getTermById(id);
	}

	@Transactional(readOnly = true)
	public List<Term> getAllTerms()
	{
		return termMapper.getAllTerms();
	}

	@Transactional(rollbackFor = Exception.class)
	public Term updateTerm(Term term)
	{
		termMapper.updateTerm(term);
		return term;
	}

	@Transactional(rollbackFor = Exception.class)
	public void deleteTerm(long id)
	{
		termMapper.deleteTerm(id);
	}
}
