package com.qa.ims.persistence.domain;

import java.util.Objects;

public class item {

	private long id;
	private String itemName;
	private double price;
	
	public item(long id, String itemName, double price) {
		
		this.id = id;
		this.itemName = itemName;
		this.price = price;
	}
	
	public item(String itemName, double price) {
		this.itemName = itemName;
		this.price = price;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, itemName, price);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		item other = (item) obj;
		return id == other.id && Objects.equals(itemName, other.itemName)
				&& Double.doubleToLongBits(price) == Double.doubleToLongBits(other.price);
	}

	@Override
	public String toString() {
		return "item [id=" + id + ", itemName=" + itemName + ", price=" + price + "]";
	}
	
	
	

	
}
