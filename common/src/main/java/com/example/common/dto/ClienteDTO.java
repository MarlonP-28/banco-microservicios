package com.example.common.dto;

    import lombok.Data;

    @Data
    public class ClienteDTO {
        private Long clienteId;
        private String nombre;
        private String genero;
        private int edad;
        private String identificacion;
        private String direccion;
        private String telefono;
        private String contrasena;
        private boolean estado;
    }