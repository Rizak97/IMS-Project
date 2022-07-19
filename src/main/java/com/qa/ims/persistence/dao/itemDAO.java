package com.qa.ims.persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.domain.item;
import com.qa.ims.utils.DBUtils;

public class itemDAO implements Dao<item> {

	public static final Logger LOGGER = LogManager.getLogger();
	
	@Override
	public item modelFromResultSet(ResultSet resultSet) throws SQLException {
		Long id = resultSet.getLong("id");
		String itemName = resultSet.getString("item_name");
		double price = resultSet.getFloat("price");
		return new item(id, itemName, price);
	}
	@Override
	public List<item> readAll() {

		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM items");) {
			List<item> items = new ArrayList<>();
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

	@Override
	public item read(Long id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM items WHERE id = ?");) {
			statement.setLong(1, id);
			ResultSet resultSet = statement.executeQuery();
			resultSet.next();
			return modelFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@Override
	public item create(item item) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("INSERT INTO items(name, price) VALUES (?, ?)");) {
			statement.setString(1, item.getItemName());
			statement.setDouble(2, item.getPrice());
			statement.executeUpdate();
			return readLatest();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@Override
	public item update(item item) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("UPDATE items SET name = ?, price = ? WHERE id = ?");) {
			statement.setString(1, item.getItemName());
			statement.setDouble(2, item.getPrice());
			statement.setLong(3, item.getId());
			statement.executeUpdate();
			return read(item.getId());
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@Override
	public int delete(long id) {
		  try (Connection connection = DBUtils.getInstance().getConnection();
	                Statement statement = connection.createStatement();) {
	            return statement.executeUpdate("delete from items where id = " + id);
	        } catch (Exception e) {
	            LOGGER.debug(e);
	            LOGGER.error(e.getMessage());
	        }
		return 0;
	}
	
	public int removeOrdersItems(item item, long orderID) {
        try (Connection connection = DBUtils.getInstance().getConnection();
        PreparedStatement statement = connection
                .prepareStatement("DELETE FROM orders_items WHERE (fk_orders_id = ? AND fk_items_id = ?)")) {
            statement.setLong(1, orderID);
            statement.setLong(2, item.getId());
            return statement.executeUpdate();
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return 0;
    }

	
	
	public item readLatest() {
        try (Connection connection = DBUtils.getInstance().getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM items ORDER BY id DESC LIMIT 1");) {
            resultSet.next();
            return modelFromResultSet(resultSet);
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return null;
    }

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}	