package com.example.bootcamp.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.example.bootcamp.cart.entity.Cart;
import com.example.bootcamp.item.entity.Item;

public class CartEntityUnitTests {

    @Test
    void newCartWithItems() throws Exception {
        ArrayList<Item> items = new ArrayList<Item>();
        Item newItem = new Item((float) 2.5, "eduItem", 1);

        items.add(newItem);

        Cart c = new Cart(UUID.randomUUID(), items);
        assertNotNull(c.getItems());
        assertEquals(c.getItems().getFirst().getName().toString(), "eduItem");
    }

    @Test
    void newCartWithSetItems() throws Exception {
        ArrayList<Item> items = new ArrayList<Item>();
        Item newItem = new Item((float) 2.5, "eduItem", 1);

        items.add(newItem);

        Cart c = new Cart();
        c.setItems(items);
        assertNotNull(c.getItems());
        assertEquals(c.getItems().getFirst().getName().toString(), "eduItem");
    }

    @Test
    void newCartRemoveItem() throws Exception {
        Item newItem = new Item((float) 2.5, "eduItem", 1);

        Cart c = new Cart();
        c.addItem(newItem);

        assertNotNull(c.getItems());
        assertEquals(c.getItems().getFirst().getName().toString(), "eduItem");

        c.removeItem(newItem);

        assertEquals(c.getItems().size(), 0);
    }
}
