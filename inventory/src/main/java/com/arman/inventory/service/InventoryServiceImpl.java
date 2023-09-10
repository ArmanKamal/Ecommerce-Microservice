package com.arman.inventory.service;


import com.arman.inventory.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;
    @Override
    public boolean isInStockOrNot(String skuCode) {
       return inventoryRepository.findBySkuCode(skuCode).isPresent();
    }

}
