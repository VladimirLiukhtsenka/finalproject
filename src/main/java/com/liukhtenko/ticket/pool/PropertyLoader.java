package com.liukhtenko.ticket.pool;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * The class that loads necessary properties.
 *
 * @author Vladimir Liukhtenko
 * @version 1.25 02 Feb 2020
 */
class PropertyLoader {
    private static Logger logger = LogManager.getLogger();
    private static final String PROPERTY_PATH = "database/message.properties";

    /**
     * This method loads necessary properties
     *
     * @return properties
     */
    Properties loadProperties() {
        Properties properties = new Properties();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(PROPERTY_PATH)) {
            properties.load(inputStream);
        } catch (IOException e) {
            logger.log(Level.FATAL, "Unable to load properties ", e);
            throw new RuntimeException(e);
        }
        return properties;
    }
}
