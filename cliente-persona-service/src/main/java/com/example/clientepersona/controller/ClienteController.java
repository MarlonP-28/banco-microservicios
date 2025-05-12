package com.example.clientepersona.controller;

    import com.example.clientepersona.model.Cliente;
    import com.example.clientepersona.service.ClienteService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    import jakarta.validation.Valid;
    import java.util.List;

    @RestController
    @RequestMapping("/clientes")
    public class ClienteController {

        private final ClienteService clienteService;

        @Autowired
        public ClienteController(ClienteService clienteService) {
            this.clienteService = clienteService;
        }

        @GetMapping
        public List<Cliente> getAllClientes() {
            return clienteService.getAllClientes();
        }

        @GetMapping("/{id}")
        public ResponseEntity<Cliente> getClienteById(@PathVariable Long id) {
            Cliente cliente = clienteService.getClienteById(id);
            if (cliente != null) {
                return ResponseEntity.ok(cliente);
            } else {
                return ResponseEntity.notFound().build();
            }
        }

        @PostMapping
        public ResponseEntity<Cliente> createCliente(@Valid @RequestBody Cliente cliente) {
            Cliente createdCliente = clienteService.createCliente(cliente);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdCliente);
        }

        @PutMapping("/{id}")
        public ResponseEntity<Cliente> updateCliente(@PathVariable Long id, @Valid @RequestBody Cliente cliente) {
            Cliente updatedCliente = clienteService.updateCliente(id, cliente);
            if (updatedCliente != null) {
                return ResponseEntity.ok(updatedCliente);
            } else {
                return ResponseEntity.notFound().build();
            }
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
            clienteService.deleteCliente(id);
            return ResponseEntity.noContent().build();
        }
    }