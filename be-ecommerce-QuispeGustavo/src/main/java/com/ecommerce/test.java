package com.ecommerce;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class test {

    public static void main(String[] args) {

        BCryptPasswordEncoder encoder =
                new BCryptPasswordEncoder();

        String password = "Gustavo123*";

        String hash = encoder.encode(password);

        System.out.println("HASH GENERADO:");
        System.out.println(hash);
    }
}