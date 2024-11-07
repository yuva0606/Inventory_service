package com.yuva.inventory.repository;

import com.yuva.inventory.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    Inventory findByProductId(String productId);

    void deleteByProductId(String productId);
}
