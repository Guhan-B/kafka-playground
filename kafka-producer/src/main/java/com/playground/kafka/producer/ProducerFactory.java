package com.playground.kafka.producer;

//

import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.Properties;
import java.io.FileInputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.kafka.clients.producer.KafkaProducer;


public class ProducerFactory {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProducerFactory.class);

    public static final String PRODUCER_CONFIGURATION_FILE_SYSTEM_PROPERTIES = "producer.configuration.file";
    public static final String PRODUCER_CONFIGURATION_FILE_ENVIRONMENT_VARIABLE = "PRODUCER_CONFIGURATION_FILE";

    private final KafkaProducer<String, String> instance;


    private ProducerFactory() throws RuntimeException {
        String configurationFilePath = System.getProperty(PRODUCER_CONFIGURATION_FILE_SYSTEM_PROPERTIES);

        if (configurationFilePath == null || configurationFilePath.isBlank()) {
            configurationFilePath = System.getenv(PRODUCER_CONFIGURATION_FILE_ENVIRONMENT_VARIABLE);

            if (configurationFilePath != null && !configurationFilePath.isBlank()) {
                LOGGER.debug("Acquired producer configuration file path from Environment Variable");
            }
        }
        else {
            LOGGER.debug("Acquired producer configuration file path from System Property");
        }

        if (configurationFilePath == null || configurationFilePath.isBlank()) {
            String message = String.format(
                    "Producer configuration file path is not provided. Provide a valid path using System Property '%s' or Environment Variable '%s'",
                    PRODUCER_CONFIGURATION_FILE_SYSTEM_PROPERTIES, PRODUCER_CONFIGURATION_FILE_ENVIRONMENT_VARIABLE
            );

            LOGGER.error(message);
            throw new RuntimeException(message);
        }

        if (!Files.exists(Paths.get(configurationFilePath))) {
            String message = String.format("Producer configuration file path provided '%s' does not exist", configurationFilePath);

            LOGGER.error(message);
            throw new RuntimeException(message);
        }

        try {
            Properties configuration = new Properties();
            configuration.load(new FileInputStream(configurationFilePath));

            this.instance = new KafkaProducer<>(configuration);
        }
        catch (Exception e) {
            String message = "Exception occurred while initializing crawler configuration";

            LOGGER.error(message, e);
            throw new RuntimeException(message, e);
        }
    }

    public static KafkaProducer<String, String> instance()  {
        return Holder.FACTORY.instance;
    }

    private static class Holder {
        private static final ProducerFactory FACTORY = new ProducerFactory();
    }
}
