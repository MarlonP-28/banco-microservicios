package com.example.clientepersona.service;

    import com.example.clientepersona.model.Cliente;
    import com.example.clientepersona.repository.ClienteRepository;
    import com.example.common.dto.ClienteDTO;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;
    import org.springframework.transaction.annotation.Transactional;

    import java.util.List;
    import java.util.stream.Collectors;

    @Service
    public class ClienteService {

        private final ClienteRepository clienteRepository;

        @Autowired
        public ClienteService(ClienteRepository clienteRepository) {
            this.clienteRepository = clienteRepository;
        }

        public List<Cliente> getAllClientes() {
            return clienteRepository.findAll();
        }

        public Cliente getClienteById(Long id) {
            return clienteRepository.findById(id).orElse(null);
        }

        public Cliente createCliente(Cliente cliente) {
            return clienteRepository.save(cliente);
        }

        public Cliente updateCliente(Long id, Cliente cliente) {
            Cliente existingCliente = clienteRepository.findById(id).orElse(null);
            if (existingCliente != null) {
                cliente.setClienteId(existingCliente.getClienteId()); //  Ensure ID is preserved
                return clienteRepository.save(cliente);
            }
            return null;
        }

        public void deleteCliente(Long id) {
            clienteRepository.deleteById(id);
        }

        //  For Kafka - convert Cliente to ClienteDTO
        public ClienteDTO convertToDTO(Cliente cliente) {
            ClienteDTO dto = new ClienteDTO();
            dto.setClienteId(cliente.getClienteId());
            dto.setNombre(cliente.getNombre());
            dto.setGenero(cliente.getGenero());
            dto.setEdad(cliente.getEdad());
            dto.setIdentificacion(cliente.getIdentificacion());
            dto.setDireccion(cliente.getDireccion());
            dto.setTelefono(cliente.getTelefono());
            dto.setContrasena(cliente.getContrasena());
            dto.setEstado(cliente.isEstado());
            return dto;
        }

        public List<ClienteDTO> getAllClientesDTO() {
            List<Cliente> clientes = clienteRepository.findAll();
            return clientes.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
        }
    }