package com.example.bootcamp.item.entity;

import java.io.Serializable;
import java.util.UUID;

import com.example.bootcamp.cart.entity.Cart;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "items")
public class Item implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    private UUID id;

    private int quantity;
    private float price;

    @ManyToOne
    private Cart cart;
    
    public Item(){
        this.id = UUID.randomUUID();
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
