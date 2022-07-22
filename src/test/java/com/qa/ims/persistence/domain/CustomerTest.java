package com.qa.ims.persistence.domain;

import org.junit.Test;

import nl.jqno.equalsverifier.EqualsVerifier;
import static org.junit.Assert.assertEquals;


public class CustomerTest {

	Customer customer;
	Long id = (long) 2;
	String firstName = "Marcus";
	String surname = "Rashford";
	
	
	
	
	
	@Test
	public void testToString() {
		customer = new Customer(id, firstName, surname);
		assertEquals(("id:" + customer.getId() + " first name:" + customer.getFirstName() + " surname:" + customer.getSurname()), customer.toString());
	}
	

	@Test
	public void firstConstructorTest() {
		Customer customer = new Customer("Marcus", "Rashford");
		assertEquals("Marcus", customer.getFirstName());
		assertEquals("Rashford", customer.getSurname());

	}

	@Test
	public void secondConstructorTest() {
		Customer customer = new Customer(1L, "Marcus", "Rashford");
		assertEquals(Long.valueOf("1"), customer.getId());
		assertEquals("Marcus", customer.getFirstName());
		assertEquals("Rashford", customer.getSurname());

	}
}
