package org.thehustletech.kafka.controller;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

  @Autowired KafkaTemplate<Object, Object> kafkaTemplate;

  @PostMapping("/kafka")
  public void sendToKafka() {
    SampleDTO test = new SampleDTO("TEST", UUID.randomUUID().toString());
    kafkaTemplate.send("first.topic", test);
  }
}

class SampleDTO {
  public String name;
  public String type;

  public SampleDTO(String name, String type) {
    this.name = name;
    this.type = type;
  }
}
