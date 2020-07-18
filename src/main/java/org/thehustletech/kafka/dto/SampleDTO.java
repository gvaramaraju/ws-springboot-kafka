package org.thehustletech.kafka.dto;

public class SampleDTO {
  public String name;
  public String type;

  public SampleDTO(String name, String type) {
    this.name = name;
    this.type = type;
  }

  @Override
  public String toString() {
    return "SampleDTO{" +
        "name='" + name + '\'' +
        ", type='" + type + '\'' +
        '}';
  }
}
