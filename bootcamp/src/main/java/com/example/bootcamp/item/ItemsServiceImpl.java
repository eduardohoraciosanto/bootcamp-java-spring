package com.example.bootcamp.item;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemsServiceImpl implements ItemService{
    @Autowired
    private ItemRepository itemRepository;
    
    // Save operation 
    @Override
    public Item createItem(float price){
        Item i = new Item(price);
        this.saveItem(i);
        return i; 
    } 
    
    // Save operation 
    @Override
    public Item saveItem(Item item){ 
        return itemRepository.save(item); 
    } 
  
    // Read operations 
    @Override public List<Item> getItemList(){ 
        return itemRepository.findAll(); 
    }

    @Override public Item getItem(UUID id){ 
        return itemRepository.findById(id).get(); 
    }
  
    // Delete operation 
    @Override
    public void deleteItemById(UUID id){ 
        itemRepository.deleteById(id); 
    } 

}
