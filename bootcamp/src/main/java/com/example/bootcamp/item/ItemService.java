package com.example.bootcamp.item;

import java.util.List;
import java.util.UUID;

public interface ItemService {
    // Create operation 
    Item createItem(float price);

    // Save operation 
    Item saveItem(Item item); 
  
    // Read operations 
    List<Item> getItemList(); 
    Item getItem(UUID id);

    // Delete operation 
    void deleteItemById(UUID itemId); 
}
