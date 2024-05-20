package com.example.bootcamp.cart;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, UUID>{
    List<CartRecord> findCartByID(UUID id);
}
