package org.example.sweet_shop.service;

import lombok.RequiredArgsConstructor;
import org.example.sweet_shop.entity.Sweet;
import org.example.sweet_shop.repository.SweetRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SweetService {
    private final SweetRepository sweetRepository;

    public List<Sweet> getAllSweets() {
        return sweetRepository.findAll();
    }

    public Sweet getSweetById(Long id) {
        return sweetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sweet not found with id: " + id));
    }

    public Sweet createSweet(Sweet sweet) {
        // Check if sweet with same name exists
        if (sweetRepository.findByNameContainingIgnoreCase(sweet.getName()).size() > 0) {
            throw new RuntimeException("Sweet with name '" + sweet.getName() + "' already exists");
        }
        return sweetRepository.save(sweet);
    }

    public Sweet updateSweet(Long id, Sweet sweetDetails) {
        Sweet sweet = getSweetById(id);

        sweet.setName(sweetDetails.getName());
        sweet.setCategory(sweetDetails.getCategory());
        sweet.setPrice(sweetDetails.getPrice());
        sweet.setQuantity(sweetDetails.getQuantity());

        return sweetRepository.save(sweet);
    }

    public void deleteSweet(Long id) {
        Sweet sweet = getSweetById(id);
        sweetRepository.delete(sweet);
    }

    public List<Sweet> searchSweets(String name, String category, BigDecimal minPrice, BigDecimal maxPrice) {
        if (name != null && !name.trim().isEmpty()) {
            return sweetRepository.findByNameContainingIgnoreCase(name);
        } else if (category != null && !category.trim().isEmpty()) {
            return sweetRepository.findByCategoryContainingIgnoreCase(category);
        } else if (minPrice != null && maxPrice != null) {
            return sweetRepository.findByPriceRange(minPrice, maxPrice);
        } else {
            return sweetRepository.findAll();
        }
    }

    public Sweet purchaseSweet(Long id, Integer quantity) {
        Sweet sweet = getSweetById(id);

        if (sweet.getQuantity() < quantity) {
            throw new RuntimeException("Not enough quantity available. Available: " + sweet.getQuantity());
        }

        sweet.setQuantity(sweet.getQuantity() - quantity);
        return sweetRepository.save(sweet);
    }

    public Sweet restockSweet(Long id, Integer quantity) {
        Sweet sweet = getSweetById(id);
        sweet.setQuantity(sweet.getQuantity() + quantity);
        return sweetRepository.save(sweet);
    }

}
