package com.example.accountmovement.service;

    import com.example.accountmovement.model.Cuenta;
    import com.example.accountmovement.model.Movimiento;
    import com.example.accountmovement.repository.CuentaRepository;
    import com.example.accountmovement.repository.MovimientoRepository;
    import com.example.common.exception.SaldoNoDisponibleException;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;
    import org.springframework.transaction.annotation.Transactional;

    import java.time.LocalDateTime;
    import java.util.List;

    @Service
    public class MovimientoService {

        private final MovimientoRepository movimientoRepository;
        private final CuentaRepository cuentaRepository;

        @Autowired
        public MovimientoService(MovimientoRepository movimientoRepository, CuentaRepository cuentaRepository) {
            this.movimientoRepository = movimientoRepository;
            this.cuentaRepository = cuentaRepository;
        }

        public List<Movimiento> getAllMovimientos() {
            return movimientoRepository.findAll();
        }

        public Movimiento getMovimientoById(Long id) {
            return movimientoRepository.findById(id).orElse(null);
        }

        @Transactional  //  Ensure atomicity
        public Movimiento createMovimiento(Movimiento movimiento) {
            Cuenta cuenta = cuentaRepository.findByNumeroCuenta(movimiento.getNumeroCuenta());
            if (cuenta == null) {
                throw new IllegalArgumentException("Cuenta not found: " + movimiento.getNumeroCuenta());
            }

            double nuevoSaldo = cuenta.getSaldoInicial() + movimiento.getValor();
            if (nuevoSaldo < 0) {
                throw new SaldoNoDisponibleException("Saldo no disponible");
            }

            cuenta.setSaldoInicial(nuevoSaldo);
            cuentaRepository.save(cuenta);  //  Update account balance

            movimiento.setSaldo(nuevoSaldo);
            movimiento.setFecha(LocalDateTime.now());
            return movimientoRepository.save(movimiento);
        }

        public Movimiento updateMovimiento(Long id, Movimiento movimiento) {
            Movimiento existingMovimiento = movimientoRepository.findById(id).orElse(null);
            if (existingMovimiento != null) {
                movimiento.setId(existingMovimiento.getId());
                return movimientoRepository.save(movimiento);
            }
            return null;
        }

        public void deleteMovimiento(Long id) {
            movimientoRepository.deleteById(id);
        }

        public List<Movimiento> getMovimientosByCuenta(String numeroCuenta) {
            return movimientoRepository.findByNumeroCuenta(numeroCuenta);
        }

        public List<Movimiento> getMovimientosByCuentaAndDates(List<String> numeroCuentas, LocalDateTime startDate, LocalDateTime endDate) {
            return movimientoRepository.findByNumeroCuentaInAndFechaBetween(numeroCuentas, startDate, endDate);
        }
    }