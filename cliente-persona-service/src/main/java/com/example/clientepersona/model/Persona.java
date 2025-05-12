package com.example.clientepersona.model;

    import lombok.Data;

    import jakarta.persistence.Entity;
    import jakarta.persistence.InheritanceType;
    import jakarta.validation.constraints.NotBlank;
    import jakarta.validation.constraints.Size;
    import jakarta.persistence.GenerationType;
    import jakarta.persistence.Column;
    import jakarta.persistence.GeneratedValue;
    import jakarta.persistence.Id;
    import jakarta.persistence.Inheritance;

    @Entity
    @Data
    @Inheritance(strategy = InheritanceType.JOINED)
    public class Persona {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @NotBlank(message = "Nombre is required")
        private String nombre;

        private String genero;
        private int edad;

        @NotBlank(message = "Identificacion is required")
        @Size(min = 10, max = 13, message = "Identificacion must be between 10 and 13 characters")
        @Column(unique = true)
        private String identificacion;

        private String direccion;
        private String telefono;

    }