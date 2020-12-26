package com.leon.kafka.core;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * ClassName Consumer
 * Description:
 * Create by leon
 * Date 2020/12/26 17:01
 */
@Component
public class Consumer {
    @KafkaListener(topics = {"topic001"})
    public void listen(ConsumerRecord<?, ?> record) {
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        System.out.println("record====>" + record);
        System.out.println("message ===>" + kafkaMessage.orElse(null));
    }
}
