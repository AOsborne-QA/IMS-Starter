package com.qa.ims.persistence.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.domain.Items;
import com.qa.ims.utils.DBUtils;

public class ItemsDAO implements Dao<Items> {

	public static final Logger LOGGER = LogManager.getLogger();

	@Override
	public Items modelFromResultSet(ResultSet resultSet) throws SQLException {
		Long id = resultSet.getLong("item_id");
		String productName = resultSet.getString("item_name");
		Double price = resultSet.getDouble("item_price");
		return new Items(id, productName, price);
	}

	/**
	 * Reads all items from the database
	 * 
	 * @return A list of items
	 */
	@Override
	public List<Items> readAll() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM items");) {
			List<Items> items = new ArrayList<>();
			while (resultSet.next()) {
				items.add(modelFromResultSet(resultSet));
			}
			return items;
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}

	public Items readLatest() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM items ORDER BY item_id DESC LIMIT 1");) {
			resultSet.next();
			return modelFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Creates a items in the database
	 * 
	 * @param items - takes in a items object. id will be ignored
	 */
	
	@Override
	public Items create(Items item) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("INSERT INTO items(item_name, item_price) VALUES('" + item.getName()
					+ "','" + item.getPrice() + "')");
			return readLatest();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	public Items readItems(Long id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM items WHERE item_id = " + id);) {
			resultSet.next();
			return modelFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Updates an item in the database
	 * 
	 * @param item - takes in an item object, the id field will be used to
	 *                 update that item in the database
	 * @return
	 */
	@Override
	public Items update(Items item) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("UPDATE items SET item_name ='" + item.getName() + "', item_price ='"
					+ item.getPrice() + "' WHERE item_id =" + item.getId());
			return readItems(item.getId());
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Deletes an item in the database
	 * 
	 * @param id - id of the item
	 */
	@Override
	public int delete(long id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();) {
			
			String orderIdQuery = "SELECT order_item_id FROM orders_items WHERE fk_item_id = " + id;
			ResultSet orderquery = statement.executeQuery(orderIdQuery);
			
			List<Long> items = new ArrayList<>();
			while (orderquery.next()) {
				items.add(orderquery.getLong("order_item_id"));
			}
			

			items.forEach((n) -> {
				try {
					statement.executeUpdate("DELETE FROM orders_items WHERE order_item_id = " + n);
				} catch (Exception e) {
					LOGGER.debug(e);
					LOGGER.error(e.getMessage());
				}
					
			});
			
			
			return statement.executeUpdate("DELETE FROM items WHERE item_id = " + id);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
	}

}
