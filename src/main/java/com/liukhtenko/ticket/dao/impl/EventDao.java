package com.liukhtenko.ticket.dao.impl;

import com.liukhtenko.ticket.dao.AbstractDao;
import com.liukhtenko.ticket.dao.ColumnName;
import com.liukhtenko.ticket.entity.Event;
import com.liukhtenko.ticket.entity.TypeEvent;
import com.liukhtenko.ticket.entity.User;
import com.liukhtenko.ticket.exception.DaoException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class EventDao extends AbstractDao<Long, Event> {
    private static final String SQL_CREATE_EVENT =
            "INSERT INTO events (name, address, description, type_event, date) values (?,?,?,?,?);";
    private static final String SQL_FIND_EVENT_BY_TYPE =
            "SELECT id, name, address, description, type_event, date FROM events WHERE type_event = ?;";
    private static final String SQL_FIND_EVENT_ALL =
            "SELECT id, name, address, description, type_event, date FROM events;";
    private static final String SQL_DELETE_EVENT_BY_ID =
            "DELETE FROM events WHERE id=?;";
    private static final String SQL_UPDATE_EVENT =
            "UPDATE events SET name=?, address=?, description=?, type_event=?, date=? WHERE id=?";
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    @Override
    public List<Event> findAll() throws DaoException {
        List<Event> events = new ArrayList<>();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQL_FIND_EVENT_ALL);
            while (resultSet.next()) {
                Event event = new Event();
                event.setId(resultSet.getLong(ColumnName.ID));
                event.setName(resultSet.getString(ColumnName.NAME));
                event.setAddress(resultSet.getString(ColumnName.ADDRESS));
                event.setDescription(resultSet.getString(ColumnName.DESCRIPTION));
                TypeEvent typeEventInsert = TypeEvent.findByType(resultSet.getString(ColumnName.TYPE_EVENT));
                event.setTypeOfEvent(typeEventInsert);
                Date dateInsert = transformDate(resultSet.getString(ColumnName.DATE));
                event.setDate(dateInsert);
                events.add(event);
            }
        } catch (SQLException | ParseException e) {
            throw new DaoException("Unable to find events", e);
        } finally {
            close(resultSet);
            close(statement);
        }
        return events;
    }

    public List<Event> findByType(String typeEvent) throws DaoException {
        List<Event> events = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(SQL_FIND_EVENT_BY_TYPE);
            statement.setString(1, typeEvent);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Event event = new Event();
                event.setId(resultSet.getLong(ColumnName.ID));
                event.setName(resultSet.getString(ColumnName.NAME));
                event.setAddress(resultSet.getString(ColumnName.ADDRESS));
                event.setDescription(resultSet.getString(ColumnName.DESCRIPTION));
                TypeEvent typeEventInsert = TypeEvent.findByType(resultSet.getString(ColumnName.TYPE_EVENT));
                event.setTypeOfEvent(typeEventInsert);
                Date dateInsert = transformDate(resultSet.getString(ColumnName.DATE));
                event.setDate(dateInsert);
                events.add(event);
            }
        } catch (SQLException | ParseException e) {
            throw new DaoException("Unable to find user", e);
        } finally {
            close(resultSet);
            close(statement);
        }
        return events;
    }

    @Override
    public Event find(Long aLong) throws DaoException {
        return null;
    }

    @Override
    public boolean delete(Long id) throws DaoException {
        boolean flag;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_DELETE_EVENT_BY_ID);
            statement.setLong(1, id);
            flag = (1 == statement.executeUpdate());
        } catch (SQLException e) {
            throw new DaoException("Unable to delete event", e);
        } finally {
            close(statement);
        }
        return flag;
    }

    @Override
    public boolean delete(Event entity) throws DaoException {
        return false;
    }

    @Override
    public boolean create(Event event) throws DaoException {
        boolean flag;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_CREATE_EVENT);
            statement.setString(1, event.getName());
            statement.setString(2, event.getAddress());
            statement.setString(3, event.getDescription());
            statement.setString(4, event.getTypeOfEvent().getValue());
            String date = transformDate(event.getDate());
            statement.setString(5, date);
            flag = (1 == statement.executeUpdate());
        } catch (SQLException | ParseException e) {
            throw new DaoException("Unable to create event", e);
        } finally {
            close(statement);
        }
        return flag;
    }

    @Override
    public boolean update(Event event) throws DaoException {
        boolean flag;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_UPDATE_EVENT);
            statement.setString(1, event.getName());
            statement.setString(2, event.getAddress());
            statement.setString(3, event.getDescription());
            statement.setString(4, event.getTypeOfEvent().getValue());
            String date = transformDate(event.getDate());
            statement.setString(5, date);
            statement.setLong(6, event.getId());
            flag = (1 == statement.executeUpdate());
        } catch (SQLException | ParseException e) {
            throw new DaoException("Unable to update user", e);
        } finally {
            close(statement);
        }
        return flag;
    }

    public static Date transformDate(String date) throws ParseException { // FIXME: 02.02.2020 перенести метод
        TimeZone tz = TimeZone.getTimeZone("Europe/Minsk");
        DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        dateFormat.setLenient(false);
        dateFormat.setTimeZone(tz);
        Date moment = dateFormat.parse(date);
        return moment;
    }

    private String transformDate(Date date) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        String time = dateFormat.format(date);
        return time;
    }
}
