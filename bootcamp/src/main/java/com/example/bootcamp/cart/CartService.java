package com.example.bootcamp.cart;

import java.util.UUID;

import com.example.bootcamp.item.Item;

public interface CartService {
    // New operation 
    Cart newCart(); 

    // Save operation 
    Cart saveCart(Cart cart); 
  
    // Read operation 
    Cart getCart(UUID id); 
  
    // Delete operation 
    void deleteCart(UUID id); 

    // Add Item
    Cart addItem(UUID id, Item item);
}
