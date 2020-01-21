package com.liukhtenko.ticket.pool;
//
//import org.apache.logging.log4j.Level;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

public class PropertyLoader {
//    static Logger logger = LogManager.getLogger();
    private Properties properties;
   // private static final String PROPERTY_PATH = "/resource/message.properties";
    private static final String PROPERTY_PATH = "D:/Java/Projects/finalproject/src/main/resource/message.properties";
    public Properties loadProperties() throws FileNotFoundException {
        properties = new Properties();


       // InputStream inputStream = getClass().getClassLoader().getResourceAsStream(PROPERTY_PATH);
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(PROPERTY_PATH);
        InputStream in = new
                FileInputStream("src/main/resource/message.properties");
        //InputStream inputStream = PropertyLoader.class.getResourceAsStream(PROPERTY_PATH);
        try {
            properties.load(in);
            // FIXME: 21.01.2020 why dont work???  properties.load(inputStream);
        } catch (IOException e) {
//            logger.log(Level.ERROR, "Unable to load properties ", e);
            throw new RuntimeException(e);
        }
        return properties;
    }
}
