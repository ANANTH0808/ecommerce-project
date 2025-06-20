package com.example.ecommerce.util;

import java.security.SecureRandom;
import java.util.Base64;

public class JwtSecretGenerator {
    public static void main(String[] args) {
        byte[] randomBytes = new byte[32];
        new SecureRandom().nextBytes(randomBytes);
        String secret = Base64.getEncoder().encodeToString(randomBytes);
        System.out.println("Generated JWT Secret: " + secret);
    }
}