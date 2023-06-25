package com.example.demo.exception;

public class OutfitNotFoundException extends RuntimeException {
    public OutfitNotFoundException(String s) {
        super(s);
    }
}
