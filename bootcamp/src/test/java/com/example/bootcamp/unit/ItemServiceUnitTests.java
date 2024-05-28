package com.example.bootcamp.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.bootcamp.item.entity.Item;
import com.example.bootcamp.item.repository.ItemRepository;
import com.example.bootcamp.item.service.ItemsServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ItemServiceUnitTests {
    @InjectMocks
    private ItemsServiceImpl itemService;

    @Mock
    private ItemRepository itemRepositoryMock;

    @Test
    void createItemWithZeroQuantity() throws Exception {
        Item repositoryItem = new Item();

        when(itemRepositoryMock.save(any(Item.class))).thenReturn(repositoryItem);

        Item createdItem = itemService.createItem((float) 2.5, "someItem", 0);

        assertEquals(1, createdItem.getQuantity());

    }

    @Test
    void getAllItemsFromRepository() throws Exception {
        ArrayList<Item> allItems = new ArrayList<Item>();
        Item repositoryItem = new Item((float)2.5,"eduItem");
        
        allItems.add(repositoryItem);

        when(itemRepositoryMock.findAll()).thenReturn(allItems);

        List<Item> createdItems = itemService.getItemList();

        assertEquals(1, createdItems.size());
        assertEquals("eduItem", createdItems.getFirst().getName());

    }
}
