package com.example.bootcamp.cart.entity;

import java.util.List;
import java.util.UUID;

import com.example.bootcamp.item.entity.Item;

public record CartRecord(UUID ID, List<Item> items) {   
    
    public CartRecord(Cart c) {
        this(c.getID(),c.getItems());
    }
}
