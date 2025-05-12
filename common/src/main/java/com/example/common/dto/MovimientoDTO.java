package com.example.common.dto;

    import lombok.Data;

    import java.time.LocalDateTime;

    @Data
    public class MovimientoDTO {
        private LocalDateTime fecha;
        private String tipoMovimiento;
        private double valor;
        private double saldo;
        private String numeroCuenta;  //  To link to Cuenta
    }
    