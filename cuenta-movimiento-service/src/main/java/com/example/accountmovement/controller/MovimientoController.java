package com.example.accountmovement.controller;

    import com.example.accountmovement.model.Movimiento;
    import com.example.accountmovement.service.MovimientoService;
    import com.example.common.exception.SaldoNoDisponibleException;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.format.annotation.DateTimeFormat;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    import jakarta.validation.Valid;
    import java.time.LocalDateTime;
    import java.util.List;

    @RestController
    @RequestMapping("/movimientos")
    public class MovimientoController {

        private final MovimientoService movimientoService;

        @Autowired
        public MovimientoController(MovimientoService movimientoService) {
            this.movimientoService = movimientoService;
        }

        @GetMapping
        public List<Movimiento> getAllMovimientos() {
            return movimientoService.getAllMovimientos();
        }

        @GetMapping("/{id}")
        public ResponseEntity<Movimiento> getMovimientoById(@PathVariable Long id) {
            Movimiento movimiento = movimientoService.getMovimientoById(id);
            if (movimiento != null) {
                return ResponseEntity.ok(movimiento);
            } else {
                return ResponseEntity.notFound().build();
            }
        }

        @PostMapping
        public ResponseEntity<?> createMovimiento(@Valid @RequestBody Movimiento movimiento) {
            try {
                Movimiento createdMovimiento = movimientoService.createMovimiento(movimiento);
                return ResponseEntity.status(HttpStatus.CREATED).body(createdMovimiento);
            } catch (SaldoNoDisponibleException e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            } catch (IllegalArgumentException e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }

        @PutMapping("/{id}")
        public ResponseEntity<Movimiento> updateMovimiento(@PathVariable Long id, @Valid @RequestBody Movimiento movimiento) {
            Movimiento updatedMovimiento = movimientoService.updateMovimiento(id, movimiento);
            if (updatedMovimiento != null) {
                return ResponseEntity.ok(updatedMovimiento);
            } else {
                return ResponseEntity.notFound().build();
            }
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteMovimiento(@PathVariable Long id) {
            movimientoService.deleteMovimiento(id);
            return ResponseEntity.noContent().build();
        }

        @GetMapping("/cuenta/{numeroCuenta}")
        public ResponseEntity<List<Movimiento>> getMovimientosByCuenta(@PathVariable String numeroCuenta) {
            List<Movimiento> movimientos = movimientoService.getMovimientosByCuenta(numeroCuenta);
            return ResponseEntity.ok(movimientos);
        }

        @GetMapping("/reporte")
        public ResponseEntity<List<Movimiento>> getMovimientosByDates(
                @RequestParam List<String> numeroCuentas,
                @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
                @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
            List<Movimiento> movimientos = movimientoService.getMovimientosByCuentaAndDates(numeroCuentas, startDate, endDate);
            return ResponseEntity.ok(movimientos);
        }
    }