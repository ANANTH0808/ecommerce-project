package com.example.ecommerce.controller;

import com.example.ecommerce.dto.CartDTO;
import com.example.ecommerce.model.Cart;
import com.example.ecommerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @PostMapping("/add")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<CartDTO> addToCart(@RequestBody CartRequest request) {
        Cart cart = cartService.addToCart(request.getUserId(), request.getProductId());
        CartDTO cartDTO = new CartDTO();
        cartDTO.setId(cart.getId());
        cartDTO.setUserId(cart.getUserId());
        cartDTO.setProductIds(cart.getProductIds());
        cartDTO.setTotalAmount(cart.getTotalAmount());
        return ResponseEntity.ok(cartDTO);
    }

    @GetMapping("/{userId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<CartDTO> getCart(@PathVariable Long userId) {
        Cart cart = cartService.findByUserId(userId);
        if (cart == null) {
            return ResponseEntity.notFound().build();
        }
        CartDTO cartDTO = new CartDTO();
        cartDTO.setId(cart.getId());
        cartDTO.setUserId(cart.getUserId());
        cartDTO.setProductIds(cart.getProductIds());
        cartDTO.setTotalAmount(cart.getTotalAmount());
        return ResponseEntity.ok(cartDTO);
    }

    @DeleteMapping("/remove")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<CartDTO> removeFromCart(@RequestBody CartRequest request) {
        Cart cart = cartService.removeFromCart(request.getUserId(), request.getProductId());
        if (cart == null) {
            return ResponseEntity.notFound().build();
        }
        CartDTO cartDTO = new CartDTO();
        cartDTO.setId(cart.getId());
        cartDTO.setUserId(cart.getUserId());
        cartDTO.setProductIds(cart.getProductIds());
        cartDTO.setTotalAmount(cart.getTotalAmount());
        return ResponseEntity.ok(cartDTO);
    }
}

class CartRequest {
    private Long userId;
    private Long productId;

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }
}