package com.example.ecommerce.service;

import com.example.ecommerce.model.Cart;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.repository.CartRepository;
import com.example.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductRepository productRepository;

    public Cart addToCart(Long userId, Long productId) {
        Cart cart = cartRepository.findByUserId(userId);
        if (cart == null) {
            cart = new Cart();
            cart.setUserId(userId);
            cart.setTotalAmount(0.0);
        }
        cart.getProductIds().add(productId);
        cart.setTotalAmount(calculateTotal(cart));
        return cartRepository.save(cart);
    }

    public Cart findByUserId(Long userId) {
        return cartRepository.findByUserId(userId);
    }

    public Cart findById(Long id) {
        return cartRepository.findById(id).orElse(null);
    }

    public Cart removeFromCart(Long userId, Long productId) {
        Cart cart = cartRepository.findByUserId(userId);
        if (cart == null) {
            return null;
        }
        cart.getProductIds().remove(productId);
        cart.setTotalAmount(calculateTotal(cart));
        return cartRepository.save(cart);
    }

    private Double calculateTotal(Cart cart) {
        List<Product> products = productRepository.findAllById(cart.getProductIds());
        return products.stream()
                .mapToDouble(Product::getPrice)
                .sum();
    }
}