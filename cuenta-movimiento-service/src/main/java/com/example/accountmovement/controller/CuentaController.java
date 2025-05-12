package com.example.accountmovement.controller;

    import com.example.accountmovement.model.Cuenta;
    import com.example.accountmovement.service.CuentaService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    import jakarta.validation.Valid;
    import java.util.List;

    @RestController
    @RequestMapping("/cuentas")
    public class CuentaController {

        private final CuentaService cuentaService;

        @Autowired
        public CuentaController(CuentaService cuentaService) {
            this.cuentaService = cuentaService;
        }

        @GetMapping
        public List<Cuenta> getAllCuentas() {
            return cuentaService.getAllCuentas();
        }

        @GetMapping("/{id}")
        public ResponseEntity<Cuenta> getCuentaById(@PathVariable Long id) {
            Cuenta cuenta = cuentaService.getCuentaById(id);
            if (cuenta != null) {
                return ResponseEntity.ok(cuenta);
            } else {
                return ResponseEntity.notFound().build();
            }
        }

        @PostMapping
        public ResponseEntity<Cuenta> createCuenta(@Valid @RequestBody Cuenta cuenta) {
            Cuenta createdCuenta = cuentaService.createCuenta(cuenta);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdCuenta);
        }

        @PutMapping("/{id}")
        public ResponseEntity<Cuenta> updateCuenta(@PathVariable Long id, @Valid @RequestBody Cuenta cuenta) {
            Cuenta updatedCuenta = cuentaService.updateCuenta(id, cuenta);
            if (updatedCuenta != null) {
                return ResponseEntity.ok(updatedCuenta);
            } else {
                return ResponseEntity.notFound().build();
            }
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteCuenta(@PathVariable Long id) {
            cuentaService.deleteCuenta(id);
            return ResponseEntity.noContent().build();
        }

        @GetMapping("/cliente/{clienteId}")
        public ResponseEntity<List<Cuenta>> getCuentasByClienteId(@PathVariable Long clienteId) {
            List<Cuenta> cuentas = cuentaService.findByClienteId(clienteId);
            return ResponseEntity.ok(cuentas);
        }
    }