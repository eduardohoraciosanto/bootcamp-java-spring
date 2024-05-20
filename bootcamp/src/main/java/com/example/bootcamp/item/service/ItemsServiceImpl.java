package com.example.bootcamp.item.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bootcamp.item.entity.Item;
import com.example.bootcamp.item.repository.ItemRepository;

@Service
public class ItemsServiceImpl implements ItemService {
    @Autowired
    private ItemRepository itemRepository;

    // Save operation
    @Override
    public Item createItem(float price, String name, int quantity) {
        if (quantity <= 0) {
            quantity = 1;
        }
        Item i = new Item(price, name, quantity);
        this.saveItem(i);
        return i;
    }

    // Save operation
    @Override
    public Item saveItem(Item item) {
        return itemRepository.save(item);
    }

    // Read operations
    @Override
    public List<Item> getItemList() {
        return itemRepository.findAll();
    }

    @Override
    public Item getItem(UUID id) {
        return itemRepository.findById(id).get();
    }

    // Delete operation
    @Override
    public void deleteItemById(UUID id) {
        itemRepository.deleteById(id);
    }

}
