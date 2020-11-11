package com.qa.ims.controller;

import java.math.BigDecimal;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.ItemsDAO;
import com.qa.ims.persistence.domain.Items;
import com.qa.ims.utils.Utils;


// Takes in item details to enable CRUD functionality

public class ItemsController implements CrudController<Items> {
	
	public static final Logger LOGGER = LogManager.getLogger();
	
	private ItemsDAO itemsDAO;
	private Utils utils;
	
	public ItemsController(ItemsDAO itemsDAO, Utils utils) {
		super();
		this.itemsDAO = itemsDAO;
		this.utils = utils;
	}
	
	
	// Reads all items to the logger

	@Override
	public List<Items> readAll() {
		List<Items> items = itemsDAO.readAll();
		for (Items item : items) {
			LOGGER.info(item.toString());
		}
		return items;
	}
	
	// Add an order to the database through user input

	@Override
	public Items create() {
		LOGGER.info("Please enter a product name");
		String productName = utils.getString();
		LOGGER.info("Please enter a price");
		Double price = utils.getDouble();
		Items items = itemsDAO.create(new Items(productName, price));
		LOGGER.info("Product created");
		return items;
	}
	
	// Updates an existing item based on user input

	@Override
	public Items update() {
		LOGGER.info("Please enter the id of the item you would like to update");
		Long id = utils.getLong();
		LOGGER.info("Please enter the name of a product");
		String productName = utils.getString();
		LOGGER.info("Please enter the price");
		double price = utils.getDouble();
		Items items = itemsDAO.update(new Items(id, productName, price));
		return items;
	}
	
	// Delete an existing item

	@Override
	public int delete() {
		// TODO Auto-generated method stub
		return 0;
	}

}
