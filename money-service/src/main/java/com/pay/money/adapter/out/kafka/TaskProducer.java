package com.pay.money.adapter.out.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pay.common.ChargingMoneyTask;
import com.pay.money.application.port.out.SendChargingMoneyTaskPort;
import java.util.Properties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TaskProducer implements SendChargingMoneyTaskPort {

  private final KafkaProducer<String, String> producer;
  private final String topic;

  public TaskProducer(
      @Value("${kafka.clusters.bootstrapservers}") String bootstrapServers,
      @Value("${task.topic}") String topic) {
    // Producer Initialization ';'
    Properties props = new Properties();

    props.put("bootstrap.servers", bootstrapServers);

    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
    props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

    this.producer = new KafkaProducer<>(props);
    this.topic = topic;
  }

  public void sendMessage(String key, ChargingMoneyTask task) {
    // json string 으로 value 전달
    ObjectMapper objectMapper = new ObjectMapper();
    String jsonStringToProduce;
    try {
      jsonStringToProduce = objectMapper.writeValueAsString(task);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
    ProducerRecord<String, String> record = new ProducerRecord<>(topic, key, jsonStringToProduce);
    producer.send(record, (metadata, exception) -> {
      if (exception == null) {
        // System.out.println("Message sent successfully. Offset: " + metadata.offset());
      } else {
        exception.printStackTrace();
        // System.err.println("Failed to send message: " + exception.getMessage());
      }
    });
  }

  @Override
  public void sendChargingMoneyTask(ChargingMoneyTask task) {
    this.sendMessage(task.getTaskID(), task);
  }
}
