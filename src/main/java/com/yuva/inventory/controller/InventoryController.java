package com.yuva.inventory.controller;

import com.yuva.inventory.dto.StockRequest;
import com.yuva.inventory.servicee.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @PostMapping("/addStock")
    public Integer addStock(@RequestBody StockRequest stockRequest) {
        return inventoryService.addQuantity(stockRequest.productId(), stockRequest.quantity());
    }

    @PutMapping("/reduceStock")
    public Integer reduceStock(@RequestBody StockRequest stockRequest) {
        return inventoryService.reduceQuantity(stockRequest.productId(), stockRequest.quantity());
    }

    @PutMapping("/increaseStock")
    public Integer increaseStock(@RequestBody StockRequest stockRequest) {
        return inventoryService.increaseQuantity(stockRequest.productId(), stockRequest.quantity());
    }

    @DeleteMapping("/deleteStock/{productId}")
    public void deleteStock(@PathVariable String productId) {
        inventoryService.deleteByProductId(productId);
    }

    @RequestMapping("/test")
    public String test() {
        return "test";
    }
}
