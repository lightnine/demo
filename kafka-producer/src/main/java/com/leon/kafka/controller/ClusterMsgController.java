package com.leon.kafka.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName ClusterMsgController
 * Description:
 * Create by leon
 * Date 2021/1/1 21:47
 */
@RestController
@Slf4j
public class ClusterMsgController {
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @GetMapping("/send/cluster/{message}")
    public String sendMsg(@PathVariable String message) {
        kafkaTemplate.send("test", message);
        String res = "message:" + message + " send success";
        log.info(res);
        return res;
    }
}
