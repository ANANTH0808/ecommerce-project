package com.example.ecommerce.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class OrderDTO {
    private Long id;
    private Long userId;
    private Long cartId;
    private String status;
    private LocalDateTime date;
}