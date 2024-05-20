package com.example.bootcamp.cart.controller;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException.NotFound;
import org.springframework.web.server.ResponseStatusException;

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

    @GetMapping("/cart/{cartId}")
	public CartRecord getCart(@PathVariable UUID cartId) {
        log.info("Getting Cart");
        Cart c = cartService.getCart(cartId);

        CartRecord cr = new CartRecord(c);
        log.info("[CartID]["+cr.ID().toString()+"]");
		return cr;
	}

    @PostMapping(path= "/cart/{cartId}/item", consumes = "application/json")
	public CartRecord addItem(@PathVariable UUID cartId, @RequestBody Item item) {
        log.info("Creating new Item for a Cart");
        Cart c = cartService.getCart(cartId);
        
        if (c == null){
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "cart not found"
            );
        }

        log.info("[CartID]["+c.getID().toString()+"]");

        Item i = itemService.createItem(item.getPrice(), item.getName(), item.getQuantity());
        log.info("New Item created");
        log.info("[ItemID]["+i.getID().toString()+"]");
        
        c = cartService.addItem(c.getID(),i);
        //Create the record to return based on the updated cart
        CartRecord cr = new CartRecord(c);
        
		return cr;
	}

    @DeleteMapping(path= "/cart/{cartId}/item/{itemId}")
	public CartRecord addItem(@PathVariable UUID cartId, @PathVariable UUID itemId) {
        Cart c;
        Item i;

        try {
            c = cartService.getCart(cartId);    
        } catch (Exception e) {
            log.error("Unable to get Cart", e);
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "cart not found"
            );
        }
        log.info("[CartID]["+c.getID().toString()+"]");

        try {
            i = itemService.getItem(itemId);    
        } catch (Exception e) {
            log.error("Unable to get Item", e);
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "item not found"
            );
        }
        log.info("Removing Item [ItemID]["+i.getID().toString()+"]");
        c = cartService.removeItem(c.getID(), i);

        log.info("Deleting Item [ItemID]["+i.getID().toString()+"]");
        itemService.deleteItemById(i.getID());
        
        //Create the record to return based on the updated cart
        CartRecord cr = new CartRecord(c);
        
		return cr;
	}
}
