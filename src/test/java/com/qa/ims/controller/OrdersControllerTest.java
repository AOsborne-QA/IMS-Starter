package com.qa.ims.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.persistence.dao.OrdersDAO;
import com.qa.ims.persistence.domain.Orders;
import com.qa.ims.utils.Utils;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
public class OrdersControllerTest {
	
	@Mock
	private OrdersDAO ordersDAO;
	
	@Mock
	private Utils utils;
	
	@InjectMocks
	private OrdersController orderController;
	
	@Test
	public void testCreateNo() {
		Long customerId = 1L;
		Long itemId = 1L;
		Long itemQuantity = 10L;
		String addNewItem = "no";
		Orders order = new Orders(customerId, itemId, itemQuantity);
		
		when(utils.getLong()).thenReturn(customerId, itemId, itemQuantity);
		when(utils.getString()).thenReturn(addNewItem);
		when(ordersDAO.create(order)).thenReturn(order);
		
		assertEquals(order, orderController.create());
		
		verify(utils, times(3)).getLong();
		verify(utils, times(1)).getString();
		verify(ordersDAO, times(1)).create(order);
	}
	
	@Test
	public void testCreateYes() {
		Long customerId = 1L;
		Long itemId = 1L;
		Long itemQuantity = 10L;
		String addNewItem = "yes";
		String addExtra= "no";
		Orders order = new Orders(customerId, itemId, itemQuantity);
		
		when(utils.getLong()).thenReturn(customerId, itemId, itemQuantity, itemId, itemQuantity);
		when(utils.getString()).thenReturn(addNewItem, addExtra);
		when(ordersDAO.create(order)).thenReturn(order);
		when(ordersDAO.addItem(order)).thenReturn(order);
		
		assertEquals(order, orderController.create());
		
		verify(utils, times(5)).getLong();
		verify(utils, times(2)).getString();
		verify(ordersDAO, times(1)).create(order);
	}
	
	@Test
	public void testDeleteOrder() {
		String selection = "order";
		Long orderId = 1L;
		
		when(utils.getString()).thenReturn(selection);
		when(utils.getLong()).thenReturn(orderId);
		
		when(ordersDAO.delete(orderId)).thenReturn(1);
		
		assertEquals(1L, this.orderController.delete());
		
		verify(utils, times(1)).getString();
		verify(utils, times(1)).getLong();
		
	}

	@Test
	public void testDeleteItem() {
		String deleteInput = "item";
		Long orderItemId = 1L;
		
		when(utils.getString()).thenReturn(deleteInput);
		when(utils.getLong()).thenReturn(orderItemId);
		
		when(ordersDAO.deleteItem(orderItemId)).thenReturn(1);
		
		assertEquals(1L, this.orderController.delete());
		
		verify(utils, times(1)).getString();
		verify(utils, times(1)).getLong();
		
	}
	
	@Test
	public void readAll() {
		List<Orders> orders = new ArrayList<>();
		orders.add(new Orders(1L, 1L, 1L, 1L, 1.99, 10L, 11.99));
		
		when(ordersDAO.readAll()).thenReturn(orders);
		
		assertEquals(orders, orderController.readAll());
		
		verify(ordersDAO, times(1)).readAll();
	}
	
	@Test
	public void updateOrderCustomer() {
		String selection = "customer";
		Long orderId = 1L;
		Long customerId = 2L;
		Orders order = new Orders(orderId, customerId);
		
		when(utils.getString()).thenReturn("notCustomer");
		when(utils.getLong()).thenReturn(orderId, customerId);
		
		when(ordersDAO.updateCustomer(order)).thenReturn(order);
		
		assertNull(orderController.update());
		assertEquals(order, orderController.updateChoice(selection));
		
		verify(utils, times(1)).getString();
		verify(utils, times(2)).getLong();
		verify(ordersDAO, times(1)).updateCustomer(order);
	}
	
	@Test
	public void updateOrderOrder() {
		String selection = "order";
		Long orderId = 1L;
		Long customerId = 2L;
		Long orderItemId = 2L;
		Long itemId = 2L;
		Long quantity = 2L;
		Orders order = new Orders(orderId, customerId, orderItemId, itemId, quantity); 
		
		when(utils.getString()).thenReturn("notOrder");
		when(utils.getLong()).thenReturn(orderId, customerId, orderItemId, itemId, quantity);
		
		when(ordersDAO.update(order)).thenReturn(order);
		
		assertNull(orderController.update());
		assertEquals(order, orderController.updateChoice(selection));
		
		verify(utils, times(1)).getString();
		verify(utils, times(5)).getLong();
		verify(ordersDAO, times(1)).update(order);
	}
	
}
