package com.playground.kafka.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;


import java.util.Properties;

public class ProducerFactory {

    public static KafkaProducer<String, String> instance() {
        Properties properties = new Properties();


        /**
         * client.id is a logical identifier for the client and the application it is used in. This can be
         * any string and will be used by the brokers to identify messages sent from the client. It is used
         * in logging and metrics and for quotas
         */
        properties.put(ProducerConfig.CLIENT_ID_CONFIG, "");

        /**
         * List of host:port pairs of brokers that the producer will use to establish initial connection to
         * the Kafka cluster. This list doesn’t need to include all brokers, since the producer will get more
         * information after the initial connection. But it is recommended to include at least two, so in case
         * one broker goes down, the producer will still be able to connect to the cluster.
         */
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "");

        /**
         * Fully qualified name of the serializer class which will be used for serializing record key into
         * byte array.This class must be an implementation of org.apache.kafka.common.serialization.Serializer
         * interface.
         */
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");

        /**
         * Fully qualified name of the serializer class which will be used for serializing record key into
         * byte array.This class must be an implementation of org.apache.kafka.common.serialization.Serializer
         * interface.
         */
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");

        /**
         * Controls the amount of time to wait for additional messages before sending the current batch
         * of records.
         */
        properties.put(ProducerConfig.LINGER_MS_CONFIG, "");

        /**
         * Controls the amount of time spent from the point a record is ready for sending (i.e. The record
         * is placed in a batch) until either the broker responds or the client gives up, including
         * time spent on retries.
         */
        properties.put(ProducerConfig.DELIVERY_TIMEOUT_MS_CONFIG, "");

        /**
         * Controls the amount of time the producer will wait for a reply from the server when sending
         * data. Note that this is the time spent waiting on each producer request before giving up. If
         * the timeout is reached without reply, the producer will either retry sending or complete the
         * callback with a TimeoutException.
         */
        properties.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, "");

        /**
         *
         */
        properties.put(ProducerConfig.ACKS_CONFIG, "");

        /**
         * Control how many times the producer will retry sending the message before giving up and notifying
         * the issue. The retries setting defaults to Integer.MAX_VALUE, and it's recommended to use delivery.timeout.ms
         * to control retry behavior, instead of retries.
         */
        properties.put(ProducerConfig.RETRIES_CONFIG, "");
    }


}
