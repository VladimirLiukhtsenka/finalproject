package com.liukhtenko.ticket.dao;


import com.liukhtenko.ticket.pool.CustomConnectionPool;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * This class gives the Dao connection
 * and then returns to the pool.
 *
 * @author Vladimir Liukhtenko
 * @version 1.25 02 Feb 2020
 */
public class EntityTransaction {
    private Connection connection;
    private static Logger logger = LogManager.getLogger();

    /**
     * This method is used at the beginning of work with one Dao
     *
     * @param dao AbstractDao
     */
    public void begin(AbstractDao dao) {
        if (connection == null) {
            connection = CustomConnectionPool.INSTANCE.getConnection();
        }
        dao.setConnection(connection);
        logger.log(Level.DEBUG, dao + " have a connection: " + connection);
    }

    /**
     * This method is used at the beginning of work with several Dao
     *
     * @param daos AbstractDao
     */
    public void begin(AbstractDao... daos) {
        try {
            if (connection == null) {
                connection = CustomConnectionPool.INSTANCE.getConnection();
            }
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            logger.log(Level.WARN, "Impossible to establish AutoCommit to value false", e);
        }
        for (AbstractDao abstractDao : daos) {
            if (abstractDao != null) {
                abstractDao.setConnection(connection);
                logger.log(Level.DEBUG, abstractDao + " have a connection: " + connection);
            }
        }
    }

    /**
     * This method allows you to return the connection to the pool
     */
    public void end() {
        try {
            if (connection != null) {
                connection.setAutoCommit(true);
                connection.close();
                connection = null;
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "impossible to return connection to pool", e);
        }
    }

    /**
     * This method allows to commit changes in database
     */
    public void commit() {
        try {
            connection.commit();
        } catch (SQLException e) {
            logger.log(Level.WARN, "impossible to commit " + connection, e);
        }
    }

    /**
     * This method allows you to roll back changes to the database
     */
    public void rollback() {
        try {
            connection.rollback();
        } catch (SQLException e) {
            logger.log(Level.WARN, "impossible to rollback " + connection, e);
        }
    }
}
