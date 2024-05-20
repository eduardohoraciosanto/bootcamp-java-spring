package com.example.bootcamp.item.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bootcamp.item.entity.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, UUID> {
}

