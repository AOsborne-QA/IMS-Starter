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
		
		LOGGER.info("Would you like to add another item to the order?");
		
		LOGGER.info("Please enter yes or no");
		
		String addNewItem = utils.getString();
				
		AddItem(addNewItem, customerId);
		
		return order;
	}
	
	public Orders AddItem(String addNewItem, Long customerId) {
		

		if(addNewItem.toLowerCase().equals("yes")) {
			
			boolean startAddItem = true;
			
			while(startAddItem) {
				LOGGER.info("Please enter an item id");
				Long itemId = utils.getLong();
				LOGGER.info("Please enter a quantity");
				Long itemQuantity = utils.getLong();
				ordersDAO.addItem(new Orders(itemId, customerId, itemQuantity));
				LOGGER.info("Item successfully added to order");
				
				LOGGER.info("Would you like to add another item?");
				LOGGER.info("Please enter yes or no");
				String addExtra = utils.getString();
				
				if(addExtra.toLowerCase().equals("no")) {
					startAddItem = false;
				} 
			}
			return null;
			
		} else {
			LOGGER.info("You are leaving");
			return null;
		}
		
	}

	@Override
	public Orders update() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete() {
		LOGGER.info("Would you like to delete an order or an item from an order?");
		LOGGER.info("Please input order or item");
		
		String deleteInput = utils.getString();
		
		if(deleteInput.toLowerCase().equals("order")) {
			LOGGER.info("Please input the ID of the order to delete");
			Long orderId = utils.getLong();
			
			LOGGER.info("Order Deleted");
			return ordersDAO.delete(orderId);
			
			
		} else if (deleteInput.toLowerCase().equals("item")) {
			LOGGER.info("Please input the ID of the order item to delete");
			Long orderItemId = utils.getLong();
			
			LOGGER.info("Item deleted from order");
			return ordersDAO.deleteItem(orderItemId);
			
		} 
		
		return 0;
	}
	
	
//	public Orders deleteChoice(String deleteInput) {
//		
//		if(deleteInput.toLowerCase().equals("order")) {
//			LOGGER.info("Please input the ID of the order to delete");
//			Long orderId = utils.getLong();
//			
//			ordersDAO.delete(orderId);
//			LOGGER.info("Order Deleted");
//			
//		} else if (deleteInput.toLowerCase().equals("item")) {
//			LOGGER.info("Please input the ID of the order item to delete");
//			Long orderItemId = utils.getLong();
//			
//			ordersDAO.delete(orderItemId);
//			LOGGER.info("Item deleted from order");
//			
//		} 
//		return null;
//		
//	}
//	

}
