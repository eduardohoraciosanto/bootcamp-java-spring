package com.example.bootcamp.cart.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.example.bootcamp.item.entity.Item;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "carts")
public class Cart {
    @Id
    private UUID id;

    @OneToMany
    private List<Item> items;

    public Cart() {
        this.id = UUID.randomUUID();
        this.items = new ArrayList<Item>();
    }

    public Cart(UUID id, List<Item> items) {
        this.id = id;
        this.items = items;
    }

    public UUID getID(){
        return id;
    }

    public List<Item> getItems(){
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Cart addItem(Item i){
        if (this.items == null){
            this.items = new ArrayList<Item>();
        }
        
        this.items.add(i);
        return this;
    }

}
