package com.liukhtenko.ticket.pool;


import com.liukhtenko.ticket.exception.PoolException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Properties;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * The class that stores and distributes connections.
 *
 * @author Vladimir Liukhtenko
 * @version 1.25 02 Feb 2020
 */
public enum CustomConnectionPool {
    INSTANCE;

    static Logger logger = LogManager.getLogger();
    private BlockingQueue<ProxyConnection> freeConnections;
    private Queue<ProxyConnection> givenAwayConnections;
    private static final String URL = "url";
    private static final String DRIVER_REGISTRATION = "driverRegistration";
    private final static int DEFAULT_POOL_SIZE = 32;

    /**
     * Constructor - creates CustomConnectionPool
     */
    CustomConnectionPool() {
        PropertyLoader propertyLoader = new PropertyLoader();
        Properties property = propertyLoader.loadProperties();
        String url = property.getProperty(URL);
        String regDriver = property.getProperty(DRIVER_REGISTRATION);
        try {
            Class.forName(regDriver);
        } catch (ClassNotFoundException e) {
            throw new PoolException("Unable to register driver", e);
        }
        freeConnections = new LinkedBlockingQueue<>(DEFAULT_POOL_SIZE);
        givenAwayConnections = new ArrayDeque<>();
        for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
            try {
                Connection connection = DriverManager.getConnection(url, property);
                ProxyConnection proxyConnection = new ProxyConnection(connection);
                freeConnections.offer(proxyConnection);
            } catch (SQLException e) {
                throw new PoolException("Unable to get connection", e);
            }
        }
    }

    /**
     * This method allows to take a connection from the pool
     *
     * @return Connection
     */
    public Connection getConnection() {
        ProxyConnection connection = null;
        try {
            //getting connection or waiting
            connection = freeConnections.take();
            //add an connection to the end of the givenAwayConnections
            givenAwayConnections.offer(connection);
        } catch (InterruptedException e) {
            logger.log(Level.WARN, "connection not transferred", e);
            Thread.currentThread().interrupt();
        }
        return connection;
    }

    /**
     * This method returns connections to the pool
     *
     * @param connection Connection that connection to be returned
     * @throws PoolException if connection cannot be returned
     */
    public void releaseConnection(Connection connection) {
        if (connection.getClass() == ProxyConnection.class) {
            //returning an connection from the front of the givenAwayConnections
            givenAwayConnections.remove(connection);
            freeConnections.offer((ProxyConnection) connection);
        } else {
            throw new PoolException("Trying to add alien connection");
        }
    }

    /**
     * This method destroys the pool
     */
    public void destroyPool() {
        for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
            try {
                freeConnections.take().reallyClose();
            } catch (SQLException | InterruptedException e) {
                logger.log(Level.ERROR, "Unable to close connection in " + freeConnections, e);
            }
        }
        deregisterDrivers();
    }

    /**
     * This method shows number of free connections
     *
     * @return number of free connections
     */
    public int getFreeConnectionsSize() {
        return freeConnections.size();
    }

    /**
     * This method shows number of busy connections
     *
     * @return number of busy connections
     */
    public int getGivenAwayConnectionsSize() {
        return givenAwayConnections.size();
    }

    /**
     * This method removes the specified driver from the registered drivers
     * which contains the DriverManager's list.
     */
    private void deregisterDrivers() {
        Collections.list(DriverManager.getDrivers()).forEach(driver -> {
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
                logger.log(Level.ERROR, "Unable to deregister driver", e);
            }
        });
    }
}
