package org.learnjava.service;

import org.learnjava.domain.Inventory;
import org.learnjava.domain.ProductOption;

import java.util.concurrent.CompletableFuture;

import static org.learnjava.util.CommonUtil.delay;

public class InventoryService {
    public Inventory addInventory(ProductOption productOption) {
        delay(500);
        return Inventory.builder()
                .count(2).build();

    }

    public CompletableFuture<Inventory> addInventory_CF(ProductOption productOption) {

        return CompletableFuture.supplyAsync(() -> {
            delay(500);
            return Inventory.builder()
                    .count(2).build();
        });

    }
}
