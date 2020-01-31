package com.liukhtenko.ticket.service;

import com.liukhtenko.ticket.dao.EntityTransaction;
import com.liukhtenko.ticket.dao.impl.UserDao;
import com.liukhtenko.ticket.entity.User;
import com.liukhtenko.ticket.exception.DaoException;
import com.liukhtenko.ticket.exception.ServiceException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class UserService {
    static Logger logger = LogManager.getLogger();

    public List<User> findAllUsers() throws ServiceException {
        List<User> users;
        UserDao userDao = new UserDao();
        EntityTransaction transaction = new EntityTransaction();
        try {
            transaction.begin(userDao);
            users = userDao.findAll();
            transaction.commit();
            logger.log(Level.DEBUG, "findAllUsers completed successfully");
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
        return users;
    }

    public User findUserById(long id) throws ServiceException {
        User user;
        UserDao userDao = new UserDao();
        EntityTransaction transaction = new EntityTransaction();
        try {
            transaction.begin(userDao);
            user = userDao.find(id);
            transaction.commit();
            logger.log(Level.DEBUG, "findUserById completed successfully");
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
        return user;
    }

    public User findUserByMailAndPassword(String mail, String password) throws ServiceException {
        User user;
        UserDao userDao = new UserDao();
        EntityTransaction transaction = new EntityTransaction();
        try {
            transaction.begin(userDao);
            user = userDao.find(mail, password);
            transaction.commit();
            logger.log(Level.DEBUG, "findUserByMailAndPassword completed successfully");
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
        return user;
    }

    public void deleteUserById(long id) throws ServiceException {
        UserDao userDao = new UserDao();
        EntityTransaction transaction = new EntityTransaction();
        try {
            transaction.begin(userDao);
            userDao.delete(id);
            transaction.commit();
            logger.log(Level.DEBUG, "deleteUserById completed successfully");
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
    }

    public boolean createUser(User user) throws ServiceException {
        UserDao userDao = new UserDao();
        EntityTransaction transaction = new EntityTransaction();
        boolean flag;
        try {
            transaction.begin(userDao);
            flag = userDao.create(user);
            transaction.commit();
            logger.log(Level.DEBUG, "createUser completed successfully");
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
        return flag;
    }

    public void updateUser(User user) throws ServiceException {
        UserDao userDao = new UserDao();
        EntityTransaction transaction = new EntityTransaction();
        try {
            transaction.begin(userDao);
            userDao.update(user);
            logger.log(Level.DEBUG, "updateUser completed successfully");
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
    }
}
