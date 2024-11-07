package com.yuva.inventory.dto;

public record StockRequest(
    String productId,
    int quantity
) {
}
