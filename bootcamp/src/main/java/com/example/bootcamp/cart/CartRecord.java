package com.example.bootcamp.cart;

import java.util.UUID;

import com.example.bootcamp.item.Item;

public record CartRecord(UUID ID, Item []items) {   
    
    public CartRecord(Cart c) {
        this(c.getID(),c.getItems());
    }
}