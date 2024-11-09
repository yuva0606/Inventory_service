package com.yuva.inventory.event;

public record ReduceStockEvent(
    String productId,
    Integer quantity
) {
}
