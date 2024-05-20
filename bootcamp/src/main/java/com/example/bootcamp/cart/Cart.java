package com.example.bootcamp.cart;

import java.util.ArrayList;
import java.util.UUID;

import com.example.bootcamp.item.Item;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "carts")
public class Cart {
    // UUID ID, Item []items
    @Id
    private UUID id;

    private ArrayList<Item> items;

    public Cart() {
        this.id = UUID.randomUUID();
        this.items = null;
    }

    public Cart(UUID id, ArrayList<Item> items) {
        this.id = id;
        this.items = items;
    }

    public UUID getID(){
        return id;
    }

    public ArrayList<Item> getItems(){
        return items;
    }

    public Cart addItem(Item i){
        if (this.items == null){
            this.items = new ArrayList<Item>();
        }
        
        this.items.add(i);
        return this;
    }

}
