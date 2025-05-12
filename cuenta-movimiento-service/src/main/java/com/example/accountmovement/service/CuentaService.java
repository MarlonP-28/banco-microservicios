package com.example.accountmovement.service;

    import com.example.accountmovement.model.Cuenta;
    import com.example.accountmovement.repository.CuentaRepository;
    import com.example.common.dto.CuentaDTO;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;

    import java.util.List;
    import java.util.stream.Collectors;

    @Service
    public class CuentaService {

        private final CuentaRepository cuentaRepository;

        @Autowired
        public CuentaService(CuentaRepository cuentaRepository) {
            this.cuentaRepository = cuentaRepository;
        }

        public List<Cuenta> getAllCuentas() {
            return cuentaRepository.findAll();
        }

        public Cuenta getCuentaById(Long id) {
            return cuentaRepository.findById(id).orElse(null);
        }

        public Cuenta createCuenta(Cuenta cuenta) {
            return cuentaRepository.save(cuenta);
        }

        public Cuenta updateCuenta(Long id, Cuenta cuenta) {
            Cuenta existingCuenta = cuentaRepository.findById(id).orElse(null);
            if (existingCuenta != null) {
                cuenta.setId(existingCuenta.getId());
                return cuentaRepository.save(cuenta);
            }
            return null;
        }

        public void deleteCuenta(Long id) {
            cuentaRepository.deleteById(id);
        }

        public Cuenta findByNumeroCuenta(String numeroCuenta) {
            return cuentaRepository.findByNumeroCuenta(numeroCuenta);
        }

        public List<Cuenta> findByClienteId(Long clienteId) {
            return cuentaRepository.findByClienteId(clienteId);
        }

        //  For Kafka - convert Cuenta to CuentaDTO
        public CuentaDTO convertToDTO(Cuenta cuenta) {
            CuentaDTO dto = new CuentaDTO();
            dto.setNumeroCuenta(cuenta.getNumeroCuenta());
            dto.setTipoCuenta(cuenta.getTipoCuenta());
            dto.setSaldoInicial(cuenta.getSaldoInicial());
            dto.setEstado(cuenta.isEstado());
            dto.setClienteId(cuenta.getClienteId());
            return dto;
        }

        public List<CuentaDTO> getAllCuentasDTO() {
            List<Cuenta> cuentas = cuentaRepository.findAll();
            return cuentas.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
        }
    }