package org.example.sweet_shop.service;

import org.example.sweet_shop.entity.Sweet;
import org.example.sweet_shop.repository.SweetRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.ExpectedCount.times;

@ExtendWith(MockitoExtension.class)
public class SweetServiceTest {
    @Mock
    private SweetRepository sweetRepository;

    @InjectMocks
    private SweetService sweetService;

    // TEST 1: Get all sweets
    @Test
    void getAllSweets_ShouldReturnAllSweets() {
        // Arrange (GIVEN)
        Sweet sweet1 = new Sweet("Chocolate Bar", "Chocolate", new BigDecimal("2.50"), 100);
        Sweet sweet2 = new Sweet("Gummy Bears", "Gummies", new BigDecimal("1.50"), 50);
        when(sweetRepository.findAll()).thenReturn(Arrays.asList(sweet1, sweet2));

        // Act (WHEN)
        List<Sweet> result = sweetService.getAllSweets();

        // Assert (THEN)
        assertEquals(2, result.size());
        verify(sweetRepository).findAll();
    }

    // TEST 2: Get sweet by ID - success case
    @Test
    void getSweetById_WhenSweetExists_ShouldReturnSweet() {
        // Arrange
        Long sweetId = 1L;
        Sweet sweet = new Sweet("Chocolate Bar", "Chocolate", new BigDecimal("2.50"), 100);
        sweet.setId(sweetId);
        when(sweetRepository.findById(sweetId)).thenReturn(Optional.of(sweet));

        // Act
        Sweet result = sweetService.getSweetById(sweetId);

        // Assert
        assertNotNull(result);
        assertEquals("Chocolate Bar", result.getName());
        verify(sweetRepository).findById(sweetId);
    }

    // TEST 3: Get sweet by ID - not found case
    @Test
    void getSweetById_WhenSweetNotExists_ShouldThrowException() {
        // Arrange
        Long sweetId = 999L;
        when(sweetRepository.findById(sweetId)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> sweetService.getSweetById(sweetId));

        assertEquals("Sweet not found with id: 999", exception.getMessage());
    }

    // TEST 4: Create sweet - success case
    @Test
    void createSweet_WithValidData_ShouldSaveAndReturnSweet() {
        // Arrange
        Sweet newSweet = new Sweet("New Candy", "Candy", new BigDecimal("1.00"), 10);
        Sweet savedSweet = new Sweet("New Candy", "Candy", new BigDecimal("1.00"), 10);
        savedSweet.setId(1L);

        when(sweetRepository.findByNameContainingIgnoreCase("New Candy")).thenReturn(Arrays.asList());
        when(sweetRepository.save(any(Sweet.class))).thenReturn(savedSweet);

        // Act
        Sweet result = sweetService.createSweet(newSweet);

        // Assert
        assertNotNull(result.getId());
        assertEquals("New Candy", result.getName());
        verify(sweetRepository).save(newSweet);
    }

}
