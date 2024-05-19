package com.example.bootcamp.item;

import java.util.UUID;

public record Item(UUID ID, int quantity, float price) {}
