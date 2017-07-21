package com.drones4hire.dronesapp.models.db.content;

import com.drones4hire.dronesapp.models.db.AbstractEntity;

public class Faq extends AbstractEntity
{

	private static final long serialVersionUID = -8493346550599165713L;

	private String question;
	private String answer;
	private Locale locale;
	private Integer order;
	private Integer groupId;

	public String getQuestion()
	{
		return question;
	}

	public void setQuestion(String question)
	{
		this.question = question;
	}

	public String getAnswer()
	{
		return answer;
	}

	public void setAnswer(String answer)
	{
		this.answer = answer;
	}

	public Locale getLocale()
	{
		return locale;
	}

	public void setLocale(Locale locale)
	{
		this.locale = locale;
	}

	public Integer getOrder()
	{
		return order;
	}

	public void setOrder(Integer order)
	{
		this.order = order;
	}

	public Integer getGroupId()
	{
		return groupId;
	}

	public void setGroupId(Integer groupId)
	{
		this.groupId = groupId;
	}
}
