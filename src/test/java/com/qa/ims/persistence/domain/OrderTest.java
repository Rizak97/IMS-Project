package com.qa.ims.persistence.domain;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class OrderTest {
	
	Order order;
	item item;
	Long customerId = (long) 1;
	Long id = (long) 2;
	String dateOrdered = "2022/12/25";
	List<item> orderItems = new ArrayList<item>();

	//ISSUE RUNNING TO STRING METH
	@Test
	public void toStringTest() {
		order = new Order(id,customerId, dateOrdered, orderItems);
		assertEquals("Order [id=" + id + ", customerId=" + customerId + ", dateOrdered=" + dateOrdered + ", ordersItems="
				+ orderItems,order.toString());

	}

	@Test
	public void constructorOne() {
		order = new Order(id, customerId, dateOrdered, orderItems);
	}

	@Test
	public void constructorTwo() {
		order = new Order(id, customerId, dateOrdered);
	}
	
	@Test
	public void ContructorThree() {
		order = new Order(customerId, dateOrdered);
	}


	@Test
	public void testGetCustomerId() {
		order = new Order(id, customerId, dateOrdered, orderItems);
		assertEquals(customerId, order.getCustomerId());
	}
	

	@Test
	public void testGetid() {
		order = new Order(id, customerId, dateOrdered, orderItems);
		assertEquals(id, order.getId());
	}
	
	@Test
	public void testGetOrderDate() {
		order = new Order(id, customerId, dateOrdered, orderItems);
		assertEquals(dateOrdered, order.getDateOrdered());
	}

	@Test
	public void testGetOrderItems() {
		order = new Order(id, customerId, dateOrdered, orderItems);
		assertEquals(orderItems, order.getOrderItems());
	}

	@Test
	public void testSetCustomerId() {
		order = new Order(id, customerId, dateOrdered, orderItems);
		order.setCustomerId((long) 12);
		assertEquals((long) 12, order.getCustomerId(), 499);

	}

	@Test
	public void testSetOrderId() {
		order = new Order(id, customerId, dateOrdered, orderItems);
		order.setId((long) 12);
		assertEquals((long) 12, order.getId(), 499);

	}
	
	@Test
	public void testSetOrderDate() {
		order = new Order(id, customerId, dateOrdered, orderItems);
		order.setDateOrdered("2022/07/07");
		assertEquals("2022/07/07", order.getDateOrdered());

	}

	@Test
	public void testSetOrderItems() {
		order = new Order(id, customerId, dateOrdered, orderItems);
		order.setOrderItems(orderItems);
		assertEquals(orderItems, order.getOrderItems());

	}
	
	@Test
	public void testHashCodeAndEquals() {
		Order a = new Order((long) 23, "2022/07/08");
		Order b = new Order((long) 22, "2022/07/05");
		
		Assert.assertFalse(a.equals(b) && b.equals(a));
		Assert.assertFalse(a.hashCode() == b.hashCode());
	}

	
	
	
	
}
