package com.qa.ims.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import com.qa.ims.persistence.dao.ItemsDAO;
import com.qa.ims.persistence.domain.Items;
import com.qa.ims.utils.Utils;

@RunWith(MockitoJUnitRunner.class)
public class ItemsControllerTest {
	
	@Mock
	private ItemsDAO itemsDAO;
	
	
	@Mock
	private Utils utils;
	
	@InjectMocks
	private ItemsController itemsController;
	
	
	@Test
	public void testCreate() {
		String productName = "Beer";
		Double price = 9.99;
		Items item = new Items(productName, price);
		
		when(utils.getString()).thenReturn(productName);
		when(utils.getDouble()).thenReturn(price);
		
		when(itemsDAO.create(item)).thenReturn(item);
		
		assertEquals(item, itemsController.create());
		
		verify(utils, times(1)).getString();
		verify(utils, times(1)).getDouble();
		verify(itemsDAO, times(1)).create(item);
		
	}
	
	
	@Test
	public void testUpdate() {
		Long id = 1L;
		String productName = "Beer";
		Double price = 9.99;
		Items item = new Items(id, productName, price);
		
		when(utils.getLong()).thenReturn(id);
		when(utils.getString()).thenReturn(productName);
		when(utils.getDouble()).thenReturn(price);
		
		when(itemsDAO.update(item)).thenReturn(item);
		
		assertEquals(item, itemsController.update());
		
		verify(utils, times(1)).getString();
		verify(utils, times(1)).getDouble();
		verify(itemsDAO, times(1)).update(item);
	}
	
	@Test
	public void testReadAll() {
		List<Items> items = new ArrayList<>();
		items.add(new Items(1L, "Pringles", 5.99));
		
		when(itemsDAO.readAll()).thenReturn(items);
		
		assertEquals(items, itemsController.readAll());
		
		verify(itemsDAO, times(1)).readAll();
	}
	
	@Test
	public void testDeleteYes() {
		Long id = 1L;
		String selection = "yes";

		when(utils.getLong()).thenReturn(id);
		when(utils.getString()).thenReturn(selection);
		when(itemsDAO.delete(id)).thenReturn(1);
		
		
		assertEquals(1L, this.itemsController.delete());

		verify(utils, times(1)).getLong();
		verify(utils, times(1)).getString();
		verify(itemsDAO, times(1)).delete(id);
		
	}
	
	@Test
	public void testDeleteNo() {
		Long id = 1L;
		String selection = "no";

		when(utils.getLong()).thenReturn(id);
		when(utils.getString()).thenReturn(selection);

		
		assertEquals(0, this.itemsController.delete());

		verify(utils, times(1)).getLong();
		verify(utils, times(1)).getString();
		
	}
	
	
}
