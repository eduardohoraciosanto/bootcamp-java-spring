package com.example.bootcamp.cart;

import java.util.UUID;

public interface CartService {
    // New operation 
    Cart newCart(); 

    // Save operation 
    Cart saveCart(Cart cart); 
  
    // Read operation 
    Cart getCart(UUID id); 
  
    // Delete operation 
    void deleteCart(UUID id); 
}
