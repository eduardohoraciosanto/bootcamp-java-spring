package com.example.bootcamp.cart;

import java.util.UUID;

import com.example.bootcamp.item.Item;

public record Cart(UUID ID, Item []items) {   
    
    public Cart() {
        this(UUID.randomUUID(),null);
    }
}
