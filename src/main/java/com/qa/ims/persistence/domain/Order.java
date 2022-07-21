package com.qa.ims.persistence.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Order {

	private Long id;
	private Long customerId;
	private String dateOrdered;
	private List<item> orderItems = new ArrayList<>();
	
	
	
	
	public Order(Long id, Long customerId, String dateOrdered, List<item> orderItems) {
		super();
		this.id = id;
		this.customerId = customerId;
		this.dateOrdered = dateOrdered;
		this.orderItems = orderItems;
	}
	
	public Order(Long id, Long customerId, String dateOrdered) {
		super();
		this.id = id;
		this.customerId = customerId;
		this.dateOrdered = dateOrdered;
		
	}
	public Order( Long customerId, String dateOrdered) {
		super();
		this.customerId = customerId;
		this.dateOrdered = dateOrdered;
		
	}
			
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getDateOrdered() {
		return dateOrdered;
	}

	public void setDateOrdered(String dateOrdered) {
		this.dateOrdered = dateOrdered;
	}

	public List<item> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<item> orderItems) {
		this.orderItems = orderItems;
	}

	
	@Override
	public int hashCode() {
		return Objects.hash(customerId, dateOrdered, id, orderItems);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		return Objects.equals(customerId, other.customerId) && Objects.equals(dateOrdered, other.dateOrdered)
				&& Objects.equals(id, other.id) && Objects.equals(orderItems, other.orderItems);
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", customerId=" + customerId + ", dateOrdered=" + dateOrdered + ", ordersItems="
				+ orderItems + "]";
	}
	
	
	
	
	
	
	
	
	
	
	
}
