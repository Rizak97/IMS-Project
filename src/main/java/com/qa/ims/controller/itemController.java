package com.qa.ims.controller;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.itemDAO;
import com.qa.ims.persistence.domain.item;
import com.qa.ims.utils.Utils;

public class itemController implements CrudController<item>{
	
	public static final Logger LOGGER = LogManager.getLogger();

	private itemDAO itemDao;
	private Utils Utils;

	public itemController(itemDAO itemDao, Utils Utils) {
	
		this.itemDao = itemDao;
		this.Utils = Utils;
	}

	@Override
	public List<item> readAll() {
		List<item> items = itemDao.readAll();
		for (item item : items) {
			LOGGER.info(item);
		}
		return items;
	}

	@Override
	public item create() {
		LOGGER.info("Please enter the name of the item");
		String itemName = Utils.getString();
		LOGGER.info("Please enter the price of the item");
		Double price = Utils.getDouble();
		item item = itemDao.create(new item(itemName, price));
		LOGGER.info("Item created");
		return item;
	}

	@Override
	public item update() {
		LOGGER.info("Please enter the id of the item you would like to update");
		Long id = Utils.getLong();
		LOGGER.info("Please enter item's title");
		String itemName = Utils.getString();
		LOGGER.info("Please enter a value");
		Double price = Utils.getDouble();
		item item = itemDao.update(new item(id, itemName, price));
		LOGGER.info("Item updated.");
		return item;
	}

	@Override
	public int delete() {
		LOGGER.info("Please enter id of item you would like to delete");
        Long id = Utils.getLong();
        return itemDao.delete(id);
	}

	
	
	
}
