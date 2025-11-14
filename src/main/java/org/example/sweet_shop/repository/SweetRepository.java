package org.example.sweet_shop.repository;

import org.example.sweet_shop.entity.Sweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface SweetRepository extends JpaRepository<Sweet, Long> {
    List<Sweet> findByNameContainingIgnoreCase(String name);

    List<Sweet> findByCategoryContainingIgnoreCase(String category);

    @Query("SELECT s FROM Sweet s WHERE s.price BETWEEN :minPrice AND :maxPrice")
    List<Sweet> findByPriceRange(@Param("minPrice") BigDecimal minPrice,
                                 @Param("maxPrice") BigDecimal maxPrice);
}