package com.example.accountmovement.model;

    import lombok.Data;

    import jakarta.persistence.Entity;
    import jakarta.persistence.InheritanceType;
    import jakarta.validation.constraints.NotBlank;
    import jakarta.validation.constraints.Size;
    import jakarta.persistence.GenerationType;
    import jakarta.persistence.Column;
    import jakarta.persistence.GeneratedValue;
    import jakarta.persistence.Id;
    import java.time.LocalDateTime;

    @Entity
    @Data
    public class Movimiento {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private LocalDateTime fecha;
        private String tipoMovimiento;
        private double valor;
        private double saldo;

        private String numeroCuenta;  //  Foreign Key to Cuenta
    }