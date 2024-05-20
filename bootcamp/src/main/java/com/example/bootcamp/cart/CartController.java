package com.example.bootcamp.cart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bootcamp.interceptors.LoggingInterceptor;

@RestController
public class CartController {
    private static Logger log = LoggerFactory.getLogger(LoggingInterceptor.class);

    @Autowired
    private CartService cartService;

    @PostMapping("/cart")
	public CartRecord greeting() {
        log.info("Creating new Cart");
        Cart c = cartService.newCart();

        CartRecord cr = new CartRecord(c);
        log.info("[CartID]["+cr.ID().toString()+"]");
		return cr;
	}
}
