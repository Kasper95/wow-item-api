package com.kasperskove.wowitemapi.services;

import com.kasperskove.wowitemapi.entities.Item;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item, Integer>{

    Item findByInventoryType (String inventoryType);
}
