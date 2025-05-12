package com.example.common.dto;

    import lombok.Data;

    @Data
    public class CuentaDTO {
        private String numeroCuenta;
        private String tipoCuenta;
        private double saldoInicial;
        private boolean estado;
        private Long clienteId; //  To link to Cliente
    }