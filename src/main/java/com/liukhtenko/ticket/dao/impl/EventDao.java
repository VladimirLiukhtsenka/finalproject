package com.liukhtenko.ticket.dao.impl;

import com.liukhtenko.ticket.dao.AbstractDao;
import com.liukhtenko.ticket.entity.Event;
import com.liukhtenko.ticket.exception.DaoException;

import java.util.List;

public class EventDao extends AbstractDao<Long, Event> {
    private static final String SQL_CREATE_EVENT =
            "insert into events (name, address, description, type_event, date) values (?,?,?,?,?);";


    @Override
    public List<Event> findAll() throws DaoException {
        return null;
    }

    @Override
    public Event find(Long aLong) throws DaoException {
        return null;
    }

    @Override
    public boolean delete(Long aLong) throws DaoException {
        return false;
    }

    @Override
    public boolean delete(Event entity) throws DaoException {
        return false;
    }

    @Override
    public boolean create(Event entity) throws DaoException {
        return false;
    }

    @Override
    public boolean update(Event entity) throws DaoException {
        return false;
    }
}
