package com.example.ecommerce.dto;

import lombok.Data;
import java.util.List;

@Data
public class CartDTO {
    private Long id;
    private Long userId;
    private List<Long> productIds;
    private Double totalAmount;
}