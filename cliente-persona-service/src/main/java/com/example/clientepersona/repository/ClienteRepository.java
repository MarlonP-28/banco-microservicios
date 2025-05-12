package com.example.clientepersona.repository;

    import com.example.clientepersona.model.Cliente;
    import org.springframework.data.jpa.repository.JpaRepository;

    public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    }