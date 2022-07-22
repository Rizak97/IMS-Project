package com.qa.ims.persistence.domain;

import static org.junit.Assert.assertEquals;
import org.junit.Assert;

import org.junit.Test;

public class ItemTest {

	item item;
	Long id = (long) 1;
	String itemName = "MacbookAir";
	Double price = 999.00;
	
	@Test
	public void oneConstructor() {
		item = new item(id, itemName, price);
	}
	
	@Test
	public void twoConstructor() {
		item = new item(itemName, price);
	}

	
	@Test
	public void testGetItemName() {
		item = new item(id, itemName, price);
		assertEquals(itemName, item.getItemName());
	}
	
	
	
	@Test
	public void testSetId() {
		item = new item(id, itemName, price);
		item.setId((long)6);
		assertEquals(6, item.getId(), 6);
	}
	
	@Test
	public void testSetItemName() {
		item = new item(id, itemName, price);
		item.setItemName("Apple Charger");
		assertEquals("Apple Charger", item.getItemName());
		
	}
	
	@Test
	public void testSetPrice() {
		item = new item(id, itemName, price);
		item.setPrice((double) 200);
		assertEquals(200, item.getPrice(), 200);
	}
	
	@Test
	public void testHashCodeAndEquals() {
		item x = new item((long) 9, "Apple TV", 1000.60);
		item y = new item((long) 4, "Apple Imac", 2000.60);
		
		Assert.assertFalse(x.equals(y) && y.equals(x));
		Assert.assertFalse(x.hashCode() == y.hashCode());
	}
	
	
	
}
