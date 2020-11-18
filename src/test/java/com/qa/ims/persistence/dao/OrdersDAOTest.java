package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.qa.ims.persistence.domain.Orders;
import com.qa.ims.utils.DBUtils;

public class OrdersDAOTest {
	
	private final OrdersDAO DAO = new OrdersDAO();
	
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
		final Orders order = new Orders(1L, 1L, 2L, 1L, 1.99, 10L, 11.99);
		assertEquals(order, DAO.create(order));
	}

	@Test
	public void testReadAll() {
		List<Orders> order = new ArrayList<>();
		order.add(new Orders(1L, 1L, 1L, 1L, 1.99, 10L, 11.99));
		assertEquals(order, DAO.readAll());
	}
	
	@Test
	public void testReadLatest() {
		assertEquals(new Orders(1L, 1L, 1L, 1L, 1.99, 10L, 11.99), DAO.readLatest());
	}
	
	
	@Test
	public void testUpdateCustomer() {
		final Orders order = new Orders(1L, 1L, 1L, 1L, 1.99, 10L, 11.99);
		assertEquals(order, DAO.updateCustomer(order));
	}
	
	
	@Test
	public void testUpdateOrder() {
		final Orders order = new Orders(1L, 1L, 1L, 1L, 1.99, 10L, 11.99);
		assertEquals(order, DAO.update(order));
	}
	
	@Test
	public void testDeleteOrder() {
		assertEquals(1, DAO.delete(1));
	}
	
	@Test
	public void testDeleteItem() {
		assertEquals(1, DAO.deleteItem(1));
	}

}
