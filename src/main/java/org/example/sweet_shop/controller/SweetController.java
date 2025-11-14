package org.example.sweet_shop.controller;


import lombok.RequiredArgsConstructor;
import org.example.sweet_shop.entity.Sweet;
import org.example.sweet_shop.service.SweetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/sweets")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")

public class SweetController {
    private final SweetService sweetService;

    @GetMapping
    public ResponseEntity<List<Sweet>> getAllSweets() {
        return ResponseEntity.ok(sweetService.getAllSweets());
    }

    @PostMapping
    public ResponseEntity<Sweet> createSweet(@RequestBody Sweet sweet) {
        return ResponseEntity.ok(sweetService.createSweet(sweet));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sweet> getSweetById(@PathVariable Long id) {
        return ResponseEntity.ok(sweetService.getSweetById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sweet> updateSweet(@PathVariable Long id, @RequestBody Sweet sweet) {
        return ResponseEntity.ok(sweetService.updateSweet(id, sweet));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSweet(@PathVariable Long id) {
        sweetService.deleteSweet(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<Sweet>> searchSweets(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice) {
        return ResponseEntity.ok(sweetService.searchSweets(name, category, minPrice, maxPrice));
    }

    @PostMapping("/{id}/purchase")
    public ResponseEntity<Sweet> purchaseSweet(@PathVariable Long id, @RequestParam Integer quantity) {
        return ResponseEntity.ok(sweetService.purchaseSweet(id, quantity));
    }

    @PostMapping("/{id}/restock")
    public ResponseEntity<Sweet> restockSweet(@PathVariable Long id, @RequestParam Integer quantity) {
        return ResponseEntity.ok(sweetService.restockSweet(id, quantity));
    }

}
