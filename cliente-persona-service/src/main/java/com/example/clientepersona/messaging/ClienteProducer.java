package com.example.clientpersona.messaging;

    import com.example.common.dto.ClienteDTO;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.kafka.core.KafkaTemplate;
    import org.springframework.stereotype.Service;

    @Service
    public class ClienteProducer {

        private final KafkaTemplate<String, ClienteDTO> kafkaTemplate;
        private static final String TOPIC = "new-accounts";

        @Autowired
        public ClienteProducer(KafkaTemplate<String, ClienteDTO> kafkaTemplate) {
            this.kafkaTemplate = kafkaTemplate;
        }

        public void sendNewAccountCliente(ClienteDTO cliente) {
            kafkaTemplate.send(TOPIC, cliente);
        }
    }