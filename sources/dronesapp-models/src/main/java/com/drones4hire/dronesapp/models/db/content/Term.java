package com.drones4hire.dronesapp.models.db.content;

import com.drones4hire.dronesapp.models.db.AbstractEntity;

public class Term extends AbstractEntity
{

	private static final long serialVersionUID = -3558219194040936615L;

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
