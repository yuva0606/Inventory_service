package com.yuva.inventory.servicee;

import com.azure.messaging.servicebus.ServiceBusReceivedMessageContext;
import com.yuva.inventory.dto.StockRequest;
import com.yuva.inventory.event.ReduceStockEvent;
import com.yuva.inventory.model.Inventory;
import com.yuva.inventory.repository.InventoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public Integer addQuantity(String productId, Integer quantity) {
        Inventory inventory = Inventory.builder().id(null)
                .productId(productId).quantity(quantity).build();
        Inventory savedInventory = inventoryRepository.save(inventory);
        return savedInventory.getQuantity();
    }

//    @KafkaListener(topics = "reduce-quantity")
//    public void reduceQuantity(ReduceStockEvent reduceStockEvent) {
//        reduceQuantity(reduceStockEvent.productId(), reduceStockEvent.quantity());
//        System.out.println("Reduced quantity: " + reduceStockEvent.quantity() + " for product: " + reduceStockEvent.productId());
//    }

    public void processMessage(ServiceBusReceivedMessageContext receivedMessageContext) {
        StockRequest stockRequest = receivedMessageContext.getMessage().getBody().toObject(StockRequest.class);
        reduceQuantity(stockRequest.productId(), stockRequest.quantity());
        System.out.println("Reduced quantity: " + stockRequest.quantity() + " for product: " + stockRequest.productId());
    }

    public Integer reduceQuantity(String productId, Integer quantity) {
        Inventory inventory = inventoryRepository.findByProductId(productId);
        inventory.setQuantity(inventory.getQuantity() - quantity);
        Inventory savedInventory = inventoryRepository.save(inventory);
        return savedInventory.getQuantity();
    }

    public Integer getQuantity(String productId) {
        Inventory inventory = inventoryRepository.findByProductId(productId);
        return inventory.getQuantity();
    }

    public Integer increaseQuantity(String productId, Integer quantity) {
        Inventory inventory = inventoryRepository.findByProductId(productId);
        inventory.setQuantity(inventory.getQuantity() + quantity);
        Inventory savedInventory = inventoryRepository.save(inventory);
        return savedInventory.getQuantity();
    }

    public void deleteByProductId(String productId) {
        inventoryRepository.deleteByProductId(productId);
    }
}
