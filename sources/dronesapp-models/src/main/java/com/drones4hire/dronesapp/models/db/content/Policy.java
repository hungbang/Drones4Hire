package com.drones4hire.dronesapp.models.db.content;

import com.drones4hire.dronesapp.models.db.AbstractEntity;

public class Policy extends AbstractEntity
{

	private static final long serialVersionUID = 5646836804990439813L;

	private String text;
	private Locale locale;

	public String getText()
	{
		return text;
	}

	public void setText(String text)
	{
		this.text = text;
	}

	public Locale getLocale()
	{
		return locale;
	}

	public void setLocale(Locale locale)
	{
		this.locale = locale;
	}
}
