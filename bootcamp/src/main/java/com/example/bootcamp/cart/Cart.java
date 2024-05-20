package com.example.bootcamp.cart;

import java.util.UUID;

import com.example.bootcamp.item.Item;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "carts")
public class Cart {
    // UUID ID, Item []items
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    private Item []items;

    public Cart() {
        this.id = UUID.randomUUID();
        this.items = null;
    }

    public Cart(UUID id, Item []items) {
        this.id = id;
        this.items = items;
    }

    public UUID getID(){
        return id;
    }

    public Item[] getItems(){
        return items;
    }

}
