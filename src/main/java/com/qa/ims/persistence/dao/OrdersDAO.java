package com.qa.ims.persistence.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.domain.Orders;
import com.qa.ims.utils.DBUtils;

public class OrdersDAO implements Dao<Orders> {
	
	public static final Logger LOGGER = LogManager.getLogger();
	
	@Override
	public Orders modelFromResultSet(ResultSet resultSet) throws SQLException {

		Long customerId = resultSet.getLong("fk_customer_id");
		Long orderId = resultSet.getLong("order_id");
		Long orderItemId  = resultSet.getLong("order_item_id");
		Long itemId = resultSet.getLong("fk_item_id");
		Double itemPrice = resultSet.getDouble("unit_price");
		Long quantity = resultSet.getLong("quantity");
		Double orderCost = resultSet.getDouble("order_cost");
		
		return new Orders(customerId, orderId, orderItemId, itemId, itemPrice, quantity, orderCost);
	}

		
	@Override
	public List<Orders> readAll() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT order_id, fk_customer_id, order_item_id, "
						+ "fk_item_id, unit_price, quantity, order_cost FROM orders CROSS JOIN orders_items ON "
						+ "orders.order_id = orders_items.fk_order_id");) {
			List<Orders> order = new ArrayList<>();
			while (resultSet.next()) {
				order.add(modelFromResultSet(resultSet));
			}
			return order;
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}

	
	public Orders readLatest() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT order_id, fk_customer_id, order_item_id, "
						+ "fk_item_id, unit_price, quantity, order_cost FROM orders CROSS JOIN orders_items "
						+ "ON orders.order_id = orders_items.fk_order_id ORDER BY order_id DESC LIMIT 1");) {
			resultSet.next();
			return modelFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	

	@Override
	public Orders create(Orders order) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("INSERT INTO orders(fk_customer_id) VALUES('" + order.getCustomerId() + "')");

			addItem(order);
			
			return readLatest();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}  
		return null;
		
	}
	
	/**
	 * Creates an order item in the orders_items database
	 *  
	 * @param order - takes in an order object.
	 */	
	
	public Orders addItem(Orders order) {
		
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();) {
					
			calculateCost(order);
		
			statement.executeUpdate("INSERT INTO orders_items(fk_order_id, fk_item_id, unit_price, quantity, order_cost) "
					+ "VALUES("+ order.getOrderId() + ", " + order.getItemId() + ", " + order.getItemPrice() + ", " + order.getQuantity() 
					+ ", " + order.getOrderCost() + ")");
			
			return readLatest();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}  
		return null;
	}
	
	
	public Orders readOrders(Long orderId) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT order_id, fk_customer_id, order_item_id, "
						+ "fk_item_id, unit_price, quantity, order_cost FROM orders CROSS JOIN orders_items "
						+ "ON orders.order_id = orders_items.fk_order_id WHERE order_id = " + orderId + 
						" ORDER BY order_id DESC LIMIT 1");) {
			resultSet.next();
			return modelFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	
	/**
	 * Updates an order and order_item in the database
	 * 
	 * @param item - takes in an item object, the id field will be used to
	 *                 update that item in the database
	 * @return
	 */

	@Override
	public Orders update(Orders order) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();) {
			
			updateCustomer(order);
			
			calculateCost(order);
			
			statement.executeUpdate("UPDATE orders_items SET fk_item_id = " + order.getItemId() + ", unit_price = " 
			+ order.getItemPrice() + ", quantity = " + order.getQuantity() + ", order_cost = " + order.getOrderCost() 
			+" WHERE order_item_id = " + order.getOrderItemId());
			
			return readOrders(order.getOrderId());
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	
	
	public Orders updateCustomer(Orders order) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("UPDATE orders SET fk_customer_id = '" + order.getCustomerId() + "' WHERE order_id = "
					+ order.getOrderId());
			
			return readOrders(order.getOrderId());
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	
	
	/**
	 * Deletes an order and order_items from the database
	 * 
	 * @param order - takes in the orderId passed in by user
	 */	

	@Override
	public int delete(long orderId) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();) {
			
			statement.executeUpdate("DELETE FROM orders_items WHERE fk_order_id = " + orderId);


			return statement.executeUpdate("DELETE FROM orders WHERE order_id = " + orderId);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
	}
	
	/**
	 * Deletes an order_item from the database
	 * 
	 * @param order - takes in the orderItemId passed in by user
	 */
	
	public int deleteItem(long orderItemId) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();) {
			
			return statement.executeUpdate("DELETE FROM orders_items WHERE order_item_id = " + orderItemId);
			
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
	}
	
	
	/**
	 * Calculates cost of the order items based on price and quantity
	 * 
	 * @param order - takes in an order object
	 */
	
	public void calculateCost(Orders order) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();) {
			
			String orderIdQuery = "SELECT order_id FROM orders WHERE fk_customer_id = " + order.getCustomerId();
			ResultSet orderquery = statement.executeQuery(orderIdQuery);
			

			while(orderquery.next()) {
				order.setOrderId(orderquery.getLong("order_id"));
			}
			
			String itemQuery = "SELECT item_price FROM items WHERE item_id = " + order.getItemId();
			ResultSet itemPriceQuery = statement.executeQuery(itemQuery);
			
			while(itemPriceQuery.next()) {
				order.setItemPrice(itemPriceQuery.getDouble("item_price"));
			}
			
			double orderCost = order.getItemPrice() * order.getQuantity();
			
			order.setOrderCost(orderCost);
		

		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}  
		
	}



}
