package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.qa.ims.persistence.domain.item;
import com.qa.ims.utils.DBUtils;

public class ItemDaoTest {

	
private final itemDAO DAO = new itemDAO();
@Before
public void setup() {
	DBUtils.connect();
	DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
	
}

@Test
public void testCreate() {
	final item created = new item(2L, "Macbook air", 999);
	assertEquals(created, DAO.create(created));
}

@Test
public void testReadLatest() {
	assertEquals(new item(1L, "Macbook pro", 990), DAO.readLatest());
}

@Test
public void testRead() {
	final long ID = 1L;
	assertEquals(new item(ID, "Macbook pro", 990), DAO.read(ID));
}

@Test
public void testUpdate() {
	final item updated = new item(1L, "Macbook pro", 990);
	assertEquals(updated, DAO.update(updated));

}

@Test
public void testDelete() {
	assertEquals(1, DAO.delete(1));
}
	

	@Test
	public void testReadAll() {
		List<item> expected = new ArrayList<>();
		expected.add(new item(1L, "Macbook pro", 990));
		assertEquals(expected, DAO.readAll());
	}
			
	
}
