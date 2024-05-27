package com.example.bootcamp.item.service;

import java.util.List;
import java.util.UUID;

import com.example.bootcamp.item.entity.Item;

public interface ItemService {
    // Create operation 
    Item createItem(float price, String name, int quantity);

    // Save operation 
    Item saveItem(Item item); 
  
    // Read operations 
    List<Item> getItemList(); 
    Item getItem(UUID id);

    // Delete operation 
    void deleteItemById(UUID itemId); 
}
