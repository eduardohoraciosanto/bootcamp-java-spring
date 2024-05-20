package com.example.bootcamp.cart.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bootcamp.cart.entity.Cart;
import com.example.bootcamp.cart.repository.CartRepository;
import com.example.bootcamp.item.entity.Item;

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

    @Override
    public Cart addItem(UUID id, Item item){
        Cart c = this.getCart(id);
        c = c.addItem(item);
        this.saveCart(c);
        return c;
    }

    @Override
    public Cart removeItem(UUID id, Item item){
        Cart c = this.getCart(id);
        c = c.removeItem(item);
        this.saveCart(c);
        return c;
    }
}
