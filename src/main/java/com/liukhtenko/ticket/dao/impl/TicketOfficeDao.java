package com.liukhtenko.ticket.dao.impl;

import com.liukhtenko.ticket.dao.AbstractDao;
import com.liukhtenko.ticket.dao.ColumnName;
import com.liukhtenko.ticket.entity.TicketOffice;
import com.liukhtenko.ticket.exception.DaoException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * This class allows to make requests to the database
 * and change the status or view ticket offices
 *
 * @author Vladimir Liukhtenko
 * @version 1.25 02 Feb 2020
 */
public class TicketOfficeDao extends AbstractDao<String, TicketOffice> {
    private static final String SQL_SELECT_ALL_TICKET_OFFICE =
            " SELECT address, operating_mode, phone FROM ticket_offices;";
    private static final String SQL_DELETE_TICKET_OFFICE_BY_PHONE =
            "DELETE FROM ticket_offices WHERE phone=?;";
    private static final String SQL_CREATE_TICKET_OFFICE =
            "insert into ticket_offices (address, operating_mode, phone) values (?,?,?);";


    @Override
    public List<TicketOffice> findAll() throws DaoException {
        List<TicketOffice> ticketOffices = new ArrayList<>();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQL_SELECT_ALL_TICKET_OFFICE);
            while (resultSet.next()) {
                TicketOffice ticketOffice = new TicketOffice();
                ticketOffice.setAddress(resultSet.getString(ColumnName.ADDRESS));
                ticketOffice.setOperatingMode(resultSet.getString(ColumnName.OPERATING_MODE));
                ticketOffice.setPhone(resultSet.getString(ColumnName.PHONE));
                ticketOffices.add(ticketOffice);
            }
        } catch (SQLException e) {
            throw new DaoException("Unable to find ticketOffices", e);
        } finally {
            close(resultSet);
            close(statement);
        }
        return ticketOffices;
    }

    @Override
    public boolean delete(String phone) throws DaoException {
        boolean flag;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_DELETE_TICKET_OFFICE_BY_PHONE);
            statement.setString(1, phone);
            flag = (1 == statement.executeUpdate());
        } catch (SQLException e) {
            throw new DaoException("Unable to delete ticketOffice", e);
        } finally {
            close(statement);
        }
        return flag;
    }

    @Override
    public boolean create(TicketOffice ticketOffice) throws DaoException {
        boolean flag;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_CREATE_TICKET_OFFICE);
            statement.setString(1, ticketOffice.getAddress());
            statement.setString(2, ticketOffice.getOperatingMode());
            statement.setString(3, ticketOffice.getPhone());
            flag = (1 == statement.executeUpdate());
        } catch (SQLException e) {
            throw new DaoException("Unable to create ticketOffice", e);
        } finally {
            close(statement);
        }
        return flag;
    }

    @Override
    public boolean update(TicketOffice entity) {
        throw new UnsupportedOperationException("update method not implemented");
    }
}
