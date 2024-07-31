package com.pay.taskconsumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pay.common.ChargingMoneyTask;
import com.pay.common.SubTask;
import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TaskConsumer {

  private final KafkaConsumer<String, String> consumer;
  private final TaskResultProducer taskResultProducer;

  public TaskConsumer(@Value("${kafka.clusters.bootstrapservers}") String bootstrapServers,
      @Value("${task.topic}") String topic, TaskResultProducer taskResultProducer) {
    this.taskResultProducer = taskResultProducer;

    Properties props = new Properties();
    props.put("bootstrap.servers", bootstrapServers);
    props.put("group.id", "my-group");

    props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
    props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

    this.consumer = new KafkaConsumer<>(props);
    consumer.subscribe(Collections.singletonList(topic));

    Thread consumerThread = new Thread(() -> {
      try {
        while (true) {
          ObjectMapper mapper = new ObjectMapper();
          ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(1));

          for (ConsumerRecord<String, String> record : records) {
            System.out.println("Received message: " + record.value());

            try {
              ChargingMoneyTask task = mapper.readValue(record.value(), ChargingMoneyTask.class);
              List<SubTask> subTaskList = task.getSubTaskList();

              // TODO subtask 수행 - validation membership (external prot, adapter 활용)
              // TODO subtask 수행 - validation banking

              for (SubTask subTask : subTaskList) {
                subTask.setStatus("success");
              }

              this.taskResultProducer.sendTaskResult(task.getTaskID(), task);

            } catch (JsonProcessingException e) {
              throw new RuntimeException(e);
            }
          }
        }
      } finally {
        consumer.close();
      }
    });
    consumerThread.start();
  }
}