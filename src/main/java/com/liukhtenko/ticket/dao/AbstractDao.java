package com.liukhtenko.ticket.dao;


import com.liukhtenko.ticket.entity.Entity;
import com.liukhtenko.ticket.exception.DaoException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
/**
 * This abstract class which contains common methods for all Dao.
 *
 * @author Vladimir Liukhtenko
 * @version 1.25 02 Feb 2020
 */
public abstract class AbstractDao<K, T extends Entity> {
    protected Connection connection;
    static Logger logger = LogManager.getLogger();

    public abstract List<T> findAll() throws DaoException;

    public abstract boolean delete(K k) throws DaoException;

    public abstract boolean create(T entity) throws DaoException;

    public abstract boolean update(T entity) throws DaoException;

    public void close(Statement st) {
        try {
            if (st != null) {
                st.close();
            }
        } catch (SQLException e) {
            logger.log(Level.WARN, "Impossible to close statement", e);
        }
    }

    public void close(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            logger.log(Level.WARN, "Impossible to close resultSet", e);
        }
    }

    void setConnection(Connection connection) {
        this.connection = connection;
    }
}
