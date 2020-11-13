package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.OrdersDAO;
import com.qa.ims.persistence.domain.Orders;
import com.qa.ims.utils.Utils;

public class OrdersController implements CrudController<Orders> {

	public static final Logger LOGGER = LogManager.getLogger();
	
	private OrdersDAO ordersDAO;
	private Utils utils;
	
	public OrdersController(OrdersDAO ordersDAO, Utils utils) {
		super();
		this.ordersDAO = ordersDAO;
		this.utils = utils;
	}
	
	
	@Override
	public List<Orders> readAll() {
		List<Orders> orders = ordersDAO.readAll();
		for (Orders order : orders) {
			LOGGER.info(order.toString());
		}
		return orders;
	}

	@Override
	public Orders create() {
		LOGGER.info("Please enter a customer id");
		Long customerId = utils.getLong();
		
		LOGGER.info("Order Created");
			
		LOGGER.info("Please enter an item id");
		Long itemId = utils.getLong();
		LOGGER.info("Please enter a quantity");
		Long itemQuantity = utils.getLong();
		
		Orders order = ordersDAO.create(new Orders(itemId, customerId, itemQuantity));
	
		
		LOGGER.info("Item successfully added to order");
		return order;
	}

	@Override
	public Orders update() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete() {
		// TODO Auto-generated method stub
		return 0;
	}

}
