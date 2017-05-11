package com.drones4hire.dronesapp.models.db.commons;

import java.math.BigDecimal;

import com.drones4hire.dronesapp.models.db.AbstractEntity;

public class Duration extends AbstractEntity {
	private static final long serialVersionUID = -8574854731176821945L;

	private String title;
	private BigDecimal min;
	private BigDecimal max;
	private Integer order;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public BigDecimal getMin() {
		return min;
	}

	public void setMin(BigDecimal min) {
		this.min = min;
	}

	public BigDecimal getMax() {
		return max;
	}

	public void setMax(BigDecimal max) {
		this.max = max;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}
}
