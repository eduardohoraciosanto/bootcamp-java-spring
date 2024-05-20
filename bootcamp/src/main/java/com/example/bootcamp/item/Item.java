package com.example.bootcamp.item;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "items")
public class Item {
    @Id
    private UUID id;

    private int quantity;
    private float price;

    public Item(){
        this.id = UUID.randomUUID();
        this.quantity = 0;
        this.price = 0;
    }

    public Item(float price){
        this.id = UUID.randomUUID();
        this.quantity = 1;
        this.price = price;
    }

    public Item(UUID id, int quantity, float price){
        this.id = id;
        this.quantity = quantity;
        this.price = price;
    }

    public UUID getID(){
        return id;
    }

    public int getQuantity(){
        return quantity;
    }

    public void addQuantity(int quantity){
        this.quantity = this.quantity + quantity;
    }

    public float getPrice(){
        return price;
    }
}
