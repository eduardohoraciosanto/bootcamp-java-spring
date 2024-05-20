package com.example.bootcamp.cart;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService{
    @Autowired
    private CartRepository cartRepository;

    @Override
    public Cart newCart(){
        Cart c = new Cart();
        this.saveCart(c);
        return c;
    }; 

    // Save operation
    @Override 
    public Cart saveCart(Cart cart){
        return cartRepository.save(cart);
    }; 
  
    // Read operation
    @Override 
    public Cart getCart(UUID id){
        return cartRepository.findById(id).get();
    }; 
  
    // Delete operation 
    @Override
    public void deleteCart(UUID id){
        cartRepository.deleteById(id);
    }; 
}
