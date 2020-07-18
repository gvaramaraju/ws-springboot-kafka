package org.thehustletech.kafka.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.thehustletech.kafka.dto.SampleDTO;

@Component
public class DemoListener {

  @Autowired ObjectMapper objectMapper;

  @KafkaListener(topics = "first.topic", groupId = "default")
  public void readFromTopic(@Payload String sampleDTO, @Headers MessageHeaders messageHeaders) {
    System.out.println("Message Headers :: " + messageHeaders);
    try {
      SampleDTO dto = objectMapper.readValue(sampleDTO, SampleDTO.class);

      System.out.println("Received :: " + dto);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
  }
}
