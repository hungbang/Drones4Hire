package com.drones4hire.dronesapp.models.dto;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreditCardDTO extends AbstractDTO
{
	private static final long serialVersionUID = 2524632134286736149L;

	private String token;

	private String number;

	private String last4;

	@NotNull(message = "Card expiration month required")
	@Size(max = 2, min = 1, message = "Wrong expiration month size")
	@Pattern(regexp = "^(1[0-2]|[1-9])$", message = "Invalid month")
	private String expirationMonth;

	@NotNull(message = "Card expiration year required")
	@Size(max = 4, min = 2, message = "Wrong expiration year size")
	private String expirationYear;

	private String cvv;

	private String cardholderName;

	private String imageUrl;

	private boolean isDefault;

	public String getToken()
	{
		return token;
	}

	public void setToken(String token)
	{
		this.token = token;
	}

	public String getNumber()
	{
		return number;
	}

	public void setNumber(String number)
	{
		this.number = number;
	}

	public String getLast4()
	{
		return last4;
	}

	public void setLast4(String last4)
	{
		this.last4 = last4;
	}

	public String getExpirationMonth()
	{
		return expirationMonth;
	}

	public void setExpirationMonth(String expirationMonth)
	{
		this.expirationMonth = expirationMonth;
	}

	public String getExpirationYear()
	{
		return expirationYear;
	}

	public void setExpirationYear(String expirationYear)
	{
		this.expirationYear = expirationYear;
	}

	public String getCvv()
	{
		return cvv;
	}

	public void setCvv(String cvv)
	{
		this.cvv = cvv;
	}

	public String getImageUrl()
	{
		return imageUrl;
	}

	public void setImageUrl(String imageUrl)
	{
		this.imageUrl = imageUrl;
	}

	public String getCardholderName()
	{
		return cardholderName;
	}

	public void setCardholderName(String cardholderName)
	{
		this.cardholderName = cardholderName;
	}

	public boolean isDefault()
	{
		return isDefault;
	}

	public void setDefault(boolean isDefault)
	{
		this.isDefault = isDefault;
	}

	@AssertTrue(message = "Card number required")
	public boolean isCardNumberValid()
	{
		return token != null ? true : number != null;
	}

	@AssertTrue(message = "Card cvv number required")
	public boolean isCvvNumberValid()
	{
		return token != null ? true : cvv != null && (cvv.length() == 3 || cvv.length() == 4);
	}
}
