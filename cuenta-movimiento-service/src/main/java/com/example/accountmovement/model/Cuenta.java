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
    import jakarta.validation.constraints.PositiveOrZero;

    @Entity
    @Data
    public class Cuenta {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @NotBlank(message = "Numero Cuenta is required")
        @Column(unique = true)
        private String numeroCuenta;

        private String tipoCuenta;

        @PositiveOrZero(message = "Saldo Inicial must be positive or zero")
        private double saldoInicial;

        private boolean estado;

        private Long clienteId; //  From Cliente-Persona-Service
    }