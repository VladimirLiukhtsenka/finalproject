package com.liukhtenko.ticket.dao.impl;

import com.liukhtenko.ticket.dao.AbstractDao;
import com.liukhtenko.ticket.entity.Ticket;
import com.liukhtenko.ticket.exception.DaoException;

import java.util.List;

public class TicketDao extends AbstractDao <Long, Ticket> {
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
}
