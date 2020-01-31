package com.liukhtenko.ticket.dao.impl;

import com.liukhtenko.ticket.dao.AbstractDao;
import com.liukhtenko.ticket.dao.ColumnName;
import com.liukhtenko.ticket.entity.Ticket;
import com.liukhtenko.ticket.entity.TypeSeat;
import com.liukhtenko.ticket.exception.DaoException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TicketDao extends AbstractDao<Long, Ticket> {
    private static final String SQL_FIND_TICKETS_BY_EVENT_ID =
            "SELECT id,event_id, type_seat, number_of_tickets, price FROM tickets WHERE event_id = ?;";
    private static final String SQL_FIND_TICKETS_BY_EVENT_ID_AND_TYPE_SEAT =
            "SELECT id,event_id, type_seat, number_of_tickets, price FROM tickets WHERE event_id = ? AND type_seat = ?;";

    public List<Ticket> findTicketsByEventId(long id) throws DaoException {
        List<Ticket> tickets = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(SQL_FIND_TICKETS_BY_EVENT_ID);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Ticket ticket = findTicket(resultSet);
//                ticket.setId(resultSet.getLong(ColumnName.ID));
//                ticket.setEventId(resultSet.getLong(ColumnName.EVENT_ID));
//                TypeSeat typeSeat = TypeSeat.findByType(resultSet.getString(ColumnName.TYPE_SEAT));
//                ticket.setTypeSeat(typeSeat);
//                ticket.setNumberOfTickets(resultSet.getInt(ColumnName.NUMBER_OF_TICKETS));
//                ticket.setPrice(resultSet.getDouble(ColumnName.PRICE));
                tickets.add(ticket);
            }
        } catch (SQLException e) {
            throw new DaoException("Unable to find tickets", e);
        } finally {
            close(resultSet);
            close(statement);
        }
        return tickets;
    }

    public List<Ticket> findTicketsByEventIdAndTypeSeat(long id, String type) throws DaoException {
        List<Ticket> tickets = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(SQL_FIND_TICKETS_BY_EVENT_ID);
            statement.setLong(1, id);
            statement.setString(1, type);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Ticket ticket = findTicket(resultSet);
//                ticket.setId(resultSet.getLong(ColumnName.ID));
//                ticket.setEventId(resultSet.getLong(ColumnName.EVENT_ID));
//                TypeSeat typeSeat = TypeSeat.findByType(resultSet.getString(ColumnName.TYPE_SEAT));
//                ticket.setTypeSeat(typeSeat);
//                ticket.setNumberOfTickets(resultSet.getInt(ColumnName.NUMBER_OF_TICKETS));
//                ticket.setPrice(resultSet.getDouble(ColumnName.PRICE));
                tickets.add(ticket);
            }
        } catch (SQLException e) {
            throw new DaoException("Unable to find tickets", e);
        } finally {
            close(resultSet);
            close(statement);
        }
        return tickets;
    }

    @Override
    public List<Ticket> findAll() throws DaoException {
        return null;
    }

    @Override
    public Ticket find(Long aLong) throws DaoException {
        return null;
    }

    @Override
    public boolean delete(Long aLong) throws DaoException {
        return false;
    }

    @Override
    public boolean delete(Ticket entity) throws DaoException {
        return false;
    }

    @Override
    public boolean create(Ticket entity) throws DaoException {
        return false;
    }

    @Override
    public boolean update(Ticket entity) throws DaoException {
        return false;
    }

    private Ticket findTicket(ResultSet resultSet) throws SQLException {
        Ticket ticket = new Ticket();
        ticket.setId(resultSet.getLong(ColumnName.ID));
        ticket.setEventId(resultSet.getLong(ColumnName.EVENT_ID));
        TypeSeat typeSeat = TypeSeat.findByType(resultSet.getString(ColumnName.TYPE_SEAT));
        ticket.setTypeSeat(typeSeat);
        ticket.setNumberOfTickets(resultSet.getInt(ColumnName.NUMBER_OF_TICKETS));
        ticket.setPrice(resultSet.getDouble(ColumnName.PRICE));
        return ticket;
    }

}
