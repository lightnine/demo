package com.leon.kafka.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * ClassName MessageController
 * Description: 接受web请求，发送消息到kafka
 * Create by leon
 * Date 2020/12/26 11:02
 */
@RestController
public class MessageController {
    @Autowired
    private KafkaTemplate kafkaTemplate;

    @GetMapping("/send/{name}/{message}")
    public String send(@PathVariable("name") final String name, @PathVariable("message") final String message) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_DATE_TIME;
        String localDateTime = LocalDateTime.now().format(dateTimeFormatter);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", name);
        jsonObject.put("message", message);
        jsonObject.put("time", localDateTime);
        jsonObject.put("timeLong", System.currentTimeMillis());
        jsonObject.put("bizID", UUID.randomUUID());

        String sendMessage = JSON.toJSONString(jsonObject);
        ListenableFuture future = kafkaTemplate.send("topic001", sendMessage);
        future.addCallback(result -> System.out.println("send message success:" + sendMessage),
                throwable -> System.out.println("send message fail:" + sendMessage));
        return "send message";
    }
}
