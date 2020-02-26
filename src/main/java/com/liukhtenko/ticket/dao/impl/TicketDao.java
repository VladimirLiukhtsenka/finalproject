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
/**
 * This class allows to make requests to the database
 * and change the status  or view tickets
 *
 * @author Vladimir Liukhtenko
 * @version 1.25 02 Feb 2020
 */
public class TicketDao extends AbstractDao<Long, Ticket> {
    private static final String SQL_FIND_TICKETS_BY_EVENT_ID =
            "SELECT id,event_id, type_seat, number_of_tickets, price FROM tickets WHERE event_id = ?;";
    private static final String SQL_FIND_TICKETS_BY_EVENT_ID_AND_TYPE_SEAT =
            "SELECT id,event_id, type_seat, number_of_tickets, price FROM tickets WHERE event_id = ? AND type_seat = ?;";
    private static final String SQL_DELETE_TICKETS_BY_EVENT_ID_AND_TYPE_SEAT =
            "DELETE FROM tickets  WHERE event_id = ? AND type_seat = ?;";
    private static final String SQL_DELETE_TICKETS_BY_ID = "DELETE FROM tickets  WHERE id = ?;";
    private static final String SQL_COUNT_USER_TICKETS_BY_TICKET_ID =
            "SELECT COUNT(ticket_id) AS rowCount FROM user_tickets where ticket_id =?;";
    private static final String SQL_FIND_NUMBER_OF_TICKETS_BY_TICKETS_ID =
            "SELECT number_of_tickets FROM tickets where id =?;";
    private static final String SQL_BUY_TICKETS_BY_TICKETS_ID_AND_USER_ID =
            "INSERT INTO user_tickets (user_id,ticket_id,seat_number) VALUES(?,?,?);";
    private static final String SQL_CREATE_TICKETS =
            "INSERT INTO tickets (event_id,type_seat,number_of_tickets,price) VALUES(?,?,?,?);";
    private static final String SQL_FIND_USER_TICKETS =
            "SELECT events.name, events.address, events.description, events.date,tickets.type_seat, tickets.price, user_tickets.seat_number  FROM tickets JOIN user_tickets ON user_tickets.ticket_id = tickets.id JOIN events ON tickets.event_id = events.id WHERE user_tickets.user_id =?;";

    public int buyTicket(long userId, long ticketId) throws DaoException {
        if (isTicketsAvailable(ticketId)) {
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

    public int numberTicketsRemaining(long ticketsId) throws DaoException {
        return findAllTicketsByTicketsId(ticketsId) - countUserTicketByTicketId(ticketsId);
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
    public List<Ticket> findAll() {
        throw new UnsupportedOperationException("findAll method not implemented");
    }

    @Override
    public boolean delete(Long id) throws DaoException {
        boolean flag;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_DELETE_TICKETS_BY_ID);
            statement.setLong(1, id);
            flag = (1 == statement.executeUpdate());
        } catch (SQLException e) {
            throw new DaoException("Unable to delete tickets", e);
        } finally {
            close(statement);
        }
        return flag;
    }

    public boolean delete(long id, String type) throws DaoException {
        boolean flag;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_DELETE_TICKETS_BY_EVENT_ID_AND_TYPE_SEAT);
            statement.setLong(1, id);
            statement.setString(2, type);
            flag = (1 == statement.executeUpdate());
        } catch (SQLException e) {
            throw new DaoException("Unable to delete tickets", e);
        } finally {
            close(statement);
        }
        return flag;
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
    public boolean update(Ticket entity) {
        throw new UnsupportedOperationException("update method not implemented");
    }

    public List<List<String>> printTickets(long UserId) throws DaoException {
        List<List<String>> printUserTickets = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(SQL_FIND_USER_TICKETS);
            statement.setLong(1, UserId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                List<String> printUserTicket = new ArrayList<>();
                printUserTicket.add(resultSet.getString(ColumnName.NAME));
                printUserTicket.add(resultSet.getString(ColumnName.ADDRESS));
                printUserTicket.add(resultSet.getString(ColumnName.DESCRIPTION));
                printUserTicket.add(resultSet.getString(ColumnName.DATE));
                printUserTicket.add(resultSet.getString(ColumnName.TYPE_SEAT));
                String price = Double.toString(resultSet.getDouble(ColumnName.PRICE));
                printUserTicket.add(price);
                String seatNumber = Integer.toString(resultSet.getInt(ColumnName.SEAT_NUMBER));
                printUserTicket.add(seatNumber);
                printUserTickets.add(printUserTicket);
            }
        } catch (SQLException e) {
            throw new DaoException("Unable to print tickets", e);
        } finally {
            close(resultSet);
            close(statement);
        }
        return printUserTickets;
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
