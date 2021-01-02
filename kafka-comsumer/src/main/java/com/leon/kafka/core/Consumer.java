package com.leon.kafka.core;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**
 * ClassName Consumer
 * Description:
 * Create by leon
 * Date 2020/12/26 17:01
 */
@Component
@Slf4j
public class Consumer {
//    @KafkaListener(topics = {"topic001"})
    public void listen(ConsumerRecord<?, ?> record) {
//        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
//        System.out.println("record====>" + record);
//        System.out.println("message ===>" + kafkaMessage.orElse(null));
    }

    @KafkaListener(topics = "test", groupId = "consumer-group1")
    public void listen(List<String> list, Acknowledgment ack) {
        log.info("pool data size:" + list.size());
        List<String> msgList = new ArrayList<>();
        for (String record : list) {
            Optional<?> kafkaMessage = Optional.ofNullable(record);
            kafkaMessage.ifPresent(item -> msgList.add(item.toString()));
        }
        if (msgList.size() > 0) {
            for (String msg : msgList) {
                log.info("consume " + msg);
            }
        }
        ack.acknowledge();
        msgList.clear();
        log.info("consume end");
    }
}
