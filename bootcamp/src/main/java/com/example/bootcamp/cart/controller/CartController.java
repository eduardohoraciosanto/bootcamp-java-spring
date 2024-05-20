package com.example.bootcamp.cart.controller;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.bootcamp.cart.entity.Cart;
import com.example.bootcamp.cart.entity.CartRecord;
import com.example.bootcamp.cart.service.CartService;
import com.example.bootcamp.interceptors.LoggingInterceptor;
import com.example.bootcamp.item.entity.Item;
import com.example.bootcamp.item.service.ItemService;

@RestController
public class CartController {
    private static Logger log = LoggerFactory.getLogger(LoggingInterceptor.class);

    @Autowired
    private CartService cartService;
    
    @Autowired
    private ItemService itemService;

    @PostMapping("/cart")
	public CartRecord newCart() {
        log.info("Creating new Cart");
        Cart c = cartService.newCart();

        CartRecord cr = new CartRecord(c);
        log.info("[CartID]["+cr.ID().toString()+"]");
		return cr;
	}

    @PostMapping(path= "/cart/{cartId}/item", consumes = "application/json")
	public CartRecord addItem(@PathVariable UUID cartId, @RequestBody Item item) {
        log.info("Creating new Item for a Cart");
        Cart c = cartService.getCart(cartId);
        log.info("[CartID]["+c.getID().toString()+"]");

        Item i = itemService.createItem(item.getPrice());
        log.info("New Item created");
        log.info("[ItemID]["+i.getID().toString()+"]");
        
        //Create the record to return based on the updated cart
        CartRecord cr = new CartRecord(c.addItem(i));
        
		return cr;
	}
}
