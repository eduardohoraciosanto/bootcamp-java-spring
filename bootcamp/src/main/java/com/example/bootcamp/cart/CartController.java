package com.example.bootcamp.cart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bootcamp.interceptors.LoggingInterceptor;

@RestController
public class CartController {
    private static Logger log = LoggerFactory.getLogger(LoggingInterceptor.class);

    @PostMapping("/cart")
	public Cart greeting() {
        log.info("Creating new Cart");
        Cart c = new Cart();
        log.info("[CartID]["+c.ID().toString()+"]");
		return c;
	}
}
