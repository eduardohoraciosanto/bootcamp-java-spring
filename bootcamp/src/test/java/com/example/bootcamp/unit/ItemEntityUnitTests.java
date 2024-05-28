package com.example.bootcamp.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.example.bootcamp.item.entity.Item;
import com.example.bootcamp.item.entity.ItemRecord;

public class ItemEntityUnitTests {
    @Test
    void newItemWithPriceAndName() throws Exception {
        Item newItem = new Item((float) 2.5, "eduItem");

        assertNotNull(newItem);
        assertEquals("eduItem", newItem.getName().toString());
    }

    @Test
    void newItemWithAllProps() throws Exception {
        Item newItem = new Item(
                UUID.randomUUID(),
                1,
                (float) 2.5,
                "eduItem");

        assertNotNull(newItem);
        assertEquals("eduItem", newItem.getName().toString());
        assertEquals((float) 2.5, newItem.getPrice());
        assertEquals(1, newItem.getQuantity());
    }

    @Test
    void newItemWithAddedQuantity() throws Exception {
        Item newItem = new Item();

        assertNotNull(newItem);

        newItem.addQuantity(2);

        assertEquals(2, newItem.getQuantity());
    }

    @Test
    void newItemRecord() throws Exception {
        ItemRecord newItemRecord = new ItemRecord(
                UUID.randomUUID(),
                1,
                (float) 2.5,
                "someItemRecord");

        assertNotNull(newItemRecord);
        assertNotNull(newItemRecord.id());
        assertEquals((float) 2.5, newItemRecord.price());
        assertEquals(1, newItemRecord.quantity());
        assertEquals("someItemRecord", newItemRecord.name());
    }
}
