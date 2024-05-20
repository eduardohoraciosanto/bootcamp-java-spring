package com.example.bootcamp.item;

import java.util.UUID;

public record ItemRecord(UUID id, int quantity, float price) {}
