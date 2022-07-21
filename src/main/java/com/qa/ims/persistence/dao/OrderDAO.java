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
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.DBUtils;



public class OrderDAO implements Dao <Order>{
	public static final Logger LOGGER = LogManager.getLogger();
	private CustomerDAO CustomerDAO;
	private itemDAO itemDAO;
	
	
	public OrderDAO(CustomerDAO customerDAO, itemDAO itemDAO) {
		super();
		this.CustomerDAO = customerDAO;
		this.itemDAO = itemDAO;
	}
	
	
	
	@Override
	public Order modelFromResultSet(ResultSet resultSet) throws SQLException {
		Long id = resultSet.getLong("id");
		Long customerId = resultSet.getLong("customer_id");
		String dateOrdered = resultSet.getString("date_ordered");
		List<item> orderItems = getOrderItem(id);
		return new Order(id, customerId, dateOrdered, orderItems);
	}
		
	public List<item> getOrderItem(Long orderId) {
		List<item> ListOfItems = new ArrayList<>();
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("SELECT * FROM order_items WHERE Order_id = ?");) {
			statement.setLong(1, orderId);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				ListOfItems.add(itemDAO.read(resultSet.getLong("item_id")));
			}
			return ListOfItems;
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return ListOfItems;
	}
		

	@Override
	public List<Order> readAll() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM orders");) {
			List<Order> Orders = new ArrayList<>();
			while (resultSet.next()) {
				Orders.add(modelFromResultSet(resultSet));
			}
			return Orders;
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}

	
	@Override
	public Order read(Long id) {
		
			try (Connection connection = DBUtils.getInstance().getConnection();
					PreparedStatement statement = connection.prepareStatement("SELECT * FROM orders WHERE id = ?");) {
				statement.setLong(1, id);
				try (ResultSet resultSet = statement.executeQuery();) {
					resultSet.next();
					return modelFromResultSet(resultSet);
				}
			}catch (Exception e) {
				LOGGER.debug(e);
				LOGGER.error(e.getMessage());
		}
		return null;
		
	}
	public Order readLatest() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM orders ORDER BY id DESC LIMIT 1");) {
			resultSet.next();
			return modelFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	
	
	@Override
	public Order create(Order O) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("INSERT INTO orders(customer_id, date_ordered) VALUES (?,? )");) {
			statement.setLong(1, O.getCustomerId());
			statement.setString(2, O.getDateOrdered());
			
			statement.executeUpdate();
			return readLatest();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	
	
	public Order addToOrder(Long orderId, Long itemId) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("INSERT INTO order_items(Order_id, Item_id) VALUES (?, ?)");) {
			statement.setLong(1, orderId);
			statement.setLong(2, itemId);
			statement.executeUpdate();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return read(orderId);
	}
	
	
	public Order deleteFromOrder(Long orderId, Long itemId) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("DELETE FROM order_items WHERE (Order_id = ? AND Item_id = ?)");
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return read(orderId);
	}
	
	
	
	
	
	
	
	@Override
	public int delete(long id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("DELETE FROM orders WHERE id = ?");) {
			statement.setLong(1, id);
			return statement.executeUpdate();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
	
	}



	@Override
	public Order update(Order t) {
		// TODO Auto-generated method stub
		return null;
	}

	

	
	
	
	
	
	
	
	
	
	
	
	
}
