package com.example.bootcamp.cart.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bootcamp.cart.entity.Cart;
import com.example.bootcamp.cart.entity.CartRecord;

@Repository
public interface CartRepository extends JpaRepository<Cart, UUID>{
    List<CartRecord> findCartByID(UUID id);
}
