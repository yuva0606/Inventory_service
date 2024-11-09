package com.yuva.inventory.controller;

import com.yuva.inventory.dto.StockRequest;
import com.yuva.inventory.servicee.InventoryService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/{productId}")
    public ResponseEntity<?> getStock(@PathVariable String productId, HttpServletRequest request) {
        return ResponseEntity.ok(inventoryService.getQuantity(productId));
    }

    @PutMapping("/reduceStock")
    public ResponseEntity<?> reduceStock(@RequestBody StockRequest stockRequest) {
        return ResponseEntity.ok(inventoryService.reduceQuantity(stockRequest.productId(), stockRequest.quantity()));
    }

    @PutMapping("/increaseStock")
    public ResponseEntity<?> increaseStock(@RequestBody StockRequest stockRequest, HttpServletRequest request) {
        if (!"ADMIN".equals(request.getHeader("Authenticated-Role"))) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("It seems like you are not authorized to perform this action");
        }
        return ResponseEntity.ok(inventoryService.increaseQuantity(stockRequest.productId(), stockRequest.quantity()));
    }

    @DeleteMapping("/deleteStock/{productId}")
    public ResponseEntity<?> deleteStock(@PathVariable String productId, HttpServletRequest request) {
        if (!"ADMIN".equals(request.getHeader("Authenticated-Role"))) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("It seems like you are not authorized to perform this action");
        }
        inventoryService.deleteByProductId(productId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Stock deleted successfully");
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }
}
