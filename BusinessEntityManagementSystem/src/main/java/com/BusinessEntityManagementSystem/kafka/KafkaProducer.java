package com.BusinessEntityManagementSystem.kafka;

import com.BusinessEntityManagementSystem.dataTransferObject.BusinessEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

    @Autowired
    private KafkaTemplate<String, BusinessEntity> kafkaTemplate;

    @Value("${statistics.kafka.topic}")
    String kafkaTopic;

    public void send(BusinessEntity payload) {
        Message<BusinessEntity> message = MessageBuilder
                .withPayload(payload)
                .setHeader(KafkaHeaders.TOPIC, kafkaTopic)
                .build();
        kafkaTemplate.send(message);
        System.out.println("Message from BusinessStatisticsUnitFiles in Json: " + payload + " sent to topic: " + kafkaTopic);
        System.out.println("Message BusinessStatisticsUnitFiles in byte: " + message + " sent to topic: " + kafkaTopic);
    }
}
