package com.playground.kafka.producer;

import org.apache.kafka.clients.producer.KafkaProducer;

public class Main {
    public static void main(String[] args) {
        KafkaProducer<String, String> producer = ProducerFactory.instance();

        
    }
}
