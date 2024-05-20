package com.example.bootcamp.cart.service;

import java.util.UUID;

import com.example.bootcamp.cart.entity.Cart;
import com.example.bootcamp.item.entity.Item;

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
