package com.example.accountmovement.repository;

    import com.example.accountmovement.model.Movimiento;
    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.data.jpa.repository.Query;
    import org.springframework.data.repository.query.Param;

    import java.time.LocalDateTime;
    import java.util.List;

    public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {
        List<Movimiento> findByNumeroCuenta(String numeroCuenta);

        @Query("SELECT m FROM Movimiento m WHERE m.numeroCuenta IN :numeroCuentas AND m.fecha BETWEEN :startDate AND :endDate")
        List<Movimiento> findByNumeroCuentaInAndFechaBetween(
                @Param("numeroCuentas") List<String> numeroCuentas,
                @Param("startDate") LocalDateTime startDate,
                @Param("endDate") LocalDateTime endDate
        );
    }