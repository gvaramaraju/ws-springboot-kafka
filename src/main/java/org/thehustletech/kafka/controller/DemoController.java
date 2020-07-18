package org.thehustletech.kafka.controller;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thehustletech.kafka.dto.SampleDTO;

@RestController
public class DemoController {

  @Autowired KafkaTemplate<Object, Object> kafkaTemplate;

  @PostMapping("/kafka")
  public void sendToKafka() {
    SampleDTO test = new SampleDTO("TEST", UUID.randomUUID().toString());
    ListenableFuture<SendResult<Object, Object>> result = kafkaTemplate.send("first.topic", test);
    result.addCallback(
        new ListenableFutureCallback<SendResult<Object, Object>>() {
          @Override
          public void onFailure(Throwable throwable) {
            System.err.println(throwable.getCause());
          }

          @Override
          public void onSuccess(SendResult<Object, Object> objectObjectSendResult) {
            System.out.println(objectObjectSendResult.getProducerRecord().toString());
          }
        });
  }
}
