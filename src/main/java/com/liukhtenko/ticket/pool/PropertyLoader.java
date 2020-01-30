package com.liukhtenko.ticket.pool;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyLoader {
    static Logger logger = LogManager.getLogger();
    private Properties properties;
    private static final String PROPERTY_PATH = "res/message.properties";

    public Properties loadProperties() {
        properties = new Properties();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(PROPERTY_PATH)) {
            properties.load(inputStream);
        } catch (IOException e) {
            logger.log(Level.FATAL, "Unable to load properties ", e);
            throw new RuntimeException(e);
        }
        return properties;
    }
}
