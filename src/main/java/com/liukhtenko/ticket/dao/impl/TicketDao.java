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
    private static final String SQL_COUNT_USER_TICKETS_BY_TICKET_ID =
            "SELECT COUNT(ticket_id) AS rowCount FROM user_tickets where ticket_id =?;";
    private static final String SQL_FIND_NUMBER_OF_TICKETS_BY_TICKETS_ID =
            "SELECT number_of_tickets FROM tickets where id =?;";
    private static final String SQL_BUY_TICKETS_BY_TICKETS_ID_AND_USER_ID =
            "INSERT INTO user_tickets (user_id,ticket_id,seat_number) VALUES(?,?,?);";
    private static final String SQL_CREATE_TICKETS =
            "INSERT INTO tickets (event_id,type_seat,number_of_tickets,price) VALUES(?,?,?,?);";
    public int buyTicket(long userId, long ticketId) throws DaoException {
        if (isTicketsAvailable(ticketId)) {  // FIXME: 01.02.2020 in service or not
            int seatNumber;
            PreparedStatement statement = null;
            try {
                statement = connection.prepareStatement(SQL_BUY_TICKETS_BY_TICKETS_ID_AND_USER_ID);
                statement.setLong(1, userId);
                statement.setLong(2, ticketId);
                int soldTickets = countUserTicketByTicketId(ticketId);
                seatNumber = defineSeatNumber(soldTickets);
                statement.setInt(3, seatNumber);
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new DaoException("Unable buy ticket", e);
            } finally {
                close(statement);
            }
            return seatNumber;
        } else {
            throw new DaoException("No tickets available");
        }
    }

    private boolean isTicketsAvailable(long ticketsId) throws DaoException {
        return findAllTicketsByTicketsId(ticketsId) > countUserTicketByTicketId(ticketsId);
    }

    private int findAllTicketsByTicketsId(long id) throws DaoException {
        int count = 0;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(SQL_FIND_NUMBER_OF_TICKETS_BY_TICKETS_ID);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                count = resultSet.getInt(ColumnName.NUMBER_OF_TICKETS);
            }
        } catch (SQLException e) {
            throw new DaoException("Unable find all tickets by ticket id", e);
        } finally {
            close(resultSet);
            close(statement);
        }
        return count;
    }

    private int defineSeatNumber(int lastSeatNumber) {
        return ++lastSeatNumber;
    }

    private int countUserTicketByTicketId(long id) throws DaoException {
        int count = 0;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(SQL_COUNT_USER_TICKETS_BY_TICKET_ID);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                count = resultSet.getInt(ColumnName.ROW_COUNT);
            }
        } catch (SQLException e) {
            throw new DaoException("Unable count user ticket by ticket id", e);
        } finally {
            close(resultSet);
            close(statement);
        }
        return count;
    }


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
            statement = connection.prepareStatement(SQL_FIND_TICKETS_BY_EVENT_ID_AND_TYPE_SEAT);
            statement.setLong(1, id);
            statement.setString(2, type);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Ticket ticket = findTicket(resultSet);
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
    public boolean create(Ticket ticket) throws DaoException {
        boolean flag;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_CREATE_TICKETS);
            statement.setLong(1, ticket.getEventId());
            statement.setString(2, ticket.getTypeSeat().getValue());
            statement.setInt(3, ticket.getNumberOfTickets());
            statement.setDouble(4, ticket.getPrice());
            flag = (1 == statement.executeUpdate());
        } catch (SQLException e) {
            throw new DaoException("Unable to create ticket", e);
        } finally {
            close(statement);
        }
        return flag;
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
