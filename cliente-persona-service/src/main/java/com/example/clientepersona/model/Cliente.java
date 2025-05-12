package com.example.clientepersona.model;

    import lombok.Data;
    import lombok.EqualsAndHashCode;

    import jakarta.persistence.Entity;
    import jakarta.persistence.InheritanceType;
    import jakarta.validation.constraints.NotBlank;
    import jakarta.validation.constraints.Size;
    import jakarta.persistence.GenerationType;
    import jakarta.persistence.Column;
    import jakarta.persistence.GeneratedValue;
    import jakarta.persistence.Id;

    @Entity
    @Data
    @EqualsAndHashCode(callSuper = true)
    public class Cliente extends Persona {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long clienteId;

        @NotBlank(message = "Contraseña is required")
        private String contrasena;

        private boolean estado;

    }