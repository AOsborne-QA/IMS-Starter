package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.qa.ims.persistence.domain.Items;
import com.qa.ims.utils.DBUtils;

public class ItemsDAOTest {
	
	private final ItemsDAO DAO = new ItemsDAO();
	
	@BeforeClass
	public static void init() {
		DBUtils.connect("root", "root");
	}
	
	@Before
	public void setup() {
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
	}
	
	
	@Test
	public void testCreate() {
		final Items create = new Items(3L, "Mousdfsdge", 1.99);
		assertEquals(create, DAO.create(create));
	}
	
	
	@Test
	public void testReadAll() {
		List<Items> expected = new ArrayList<>();
		expected.add(new Items(1L, "Mouse", 1.99));
		expected.add(new Items(2L, "Treats", 6.99));
		assertEquals(expected, DAO.readAll());
	}
	
	
	@Test
	public void testUpdate() {
		final Items update = new Items(1L, "Xmas Mouse", 5.99);
		assertEquals(update, DAO.update(update));
	}

	
	@Test
	public void testUpdateFail() {
		final Items update = new Items(1L, "Hi ' ; INSERT INTO NOTHING;", 5.99);
		assertNull(DAO.update(update));
	}


	@Test
	public void testDelete() {
		assertEquals(1, DAO.delete(2));
	}

}
