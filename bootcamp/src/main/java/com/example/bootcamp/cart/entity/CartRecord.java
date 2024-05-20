package com.example.bootcamp.cart.entity;

import java.util.ArrayList;
import java.util.UUID;

import com.example.bootcamp.item.entity.Item;

public record CartRecord(UUID ID, ArrayList<Item> items) {   
    
    public CartRecord(Cart c) {
        this(c.getID(),c.getItems());
    }
}
