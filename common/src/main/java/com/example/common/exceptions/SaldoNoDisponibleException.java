package com.example.common.exception;

    public class SaldoNoDisponibleException extends RuntimeException {
        public SaldoNoDisponibleException(String message) {
            super(message);
        }
    }