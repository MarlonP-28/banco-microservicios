package com.example.accountmovement.repository;

    import com.example.accountmovement.model.Cuenta;
    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.data.jpa.repository.Query;
    import org.springframework.data.repository.query.Param;

    import java.util.List;

    public interface CuentaRepository extends JpaRepository<Cuenta, Long> {
        Cuenta findByNumeroCuenta(String numeroCuenta);

        @Query("SELECT c FROM Cuenta c WHERE c.clienteId = :clienteId")
        List<Cuenta> findByClienteId(@Param("clienteId") Long clienteId);
    }