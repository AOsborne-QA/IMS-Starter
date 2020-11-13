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
	public List<Orders> readAll() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM orders JOIN orders_items "
						+ "ON orders.order_id = orders_items.fk_order_id");) {
			List<Orders> orders = new ArrayList<>();
			while (resultSet.next()) {
				orders.add(modelFromResultSet(resultSet));
			}
			return orders;
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}

	
	public Orders readLatest() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM orders JOIN orders_items ORDER BY order_id DESC LIMIT 1");) {
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
			statement.executeUpdate("INSERT INTO orders(fk_customer_id) values('" + order.getCustomerId() + "')");

			addItem(order);
			
			return readLatest();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}  
		return null;
		
		
	}
	
	
	public Orders addItem(Orders order) {
		
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();) {
			
			String orderIdQuery = "SELECT order_id FROM orders WHERE fk_customer_id = " + order.getCustomerId();
			ResultSet orderquery = statement.executeQuery(orderIdQuery);
			

			while(orderquery.next()) {
				order.setId(orderquery.getLong("order_id"));
			}
			
			String itemQuery = "SELECT item_price FROM items WHERE item_id = " + order.getItemId();
			ResultSet itemPriceQuery = statement.executeQuery(itemQuery);
			
			while(itemPriceQuery.next()) {
				order.setItemPrice(itemPriceQuery.getDouble("item_price"));
			}
		
			statement.executeUpdate("INSERT INTO orders_items(unit_price, quantity, fk_order_id, fk_item_id) "
					+ "values("+ order.getItemPrice() + ", " + order.getQuantity() + ", " + order.getId() + ", " + order.getItemId() + ")");
			
			
			return readLatest();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}  
		return null;
	}



	@Override
	public Orders update(Orders t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(long id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();) {
			return statement.executeUpdate("delete orders_items, orders "
					+ "from orders_items "
					+ "INNER JOIN orders "
					+ "ON orders.order_id = order_items.fk_order_id "
					+ "WHERE orders_items.fk_order_id = " + id);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
	}

	@Override
	public Orders modelFromResultSet(ResultSet resultSet) throws SQLException {
		Long id = resultSet.getLong("order_id");
		Double itemPrice = resultSet.getDouble("unit_price");
		Long itemId = resultSet.getLong("fk_item_id");
		Long customerId = resultSet.getLong("fk_customer_id");
		Long quantity = resultSet.getLong("quantity");
		return new Orders(id, itemPrice, itemId, customerId, quantity);
	}


	

}
