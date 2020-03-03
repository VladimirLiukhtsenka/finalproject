package com.liukhtenko.ticket.service.impl;

import com.liukhtenko.ticket.dao.EntityTransaction;
import com.liukhtenko.ticket.dao.impl.UserDao;
import com.liukhtenko.ticket.entity.User;
import com.liukhtenko.ticket.exception.DaoException;
import com.liukhtenko.ticket.exception.ServiceException;
import com.liukhtenko.ticket.service.UserServiceInterface;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.util.List;

/**
 * Class that management users.
 *
 * @author Vladimir Liukhtenko
 * @version 1.25 02 Feb 2020
 */
public class UserService implements UserServiceInterface {
    private static Logger logger = LogManager.getLogger();

    /**
     * This method finds all users
     *
     * @return List User
     * @throws ServiceException if happen DaoException
     */
    public List<User> findAllUsers() throws ServiceException {
        List<User> users;
        UserDao userDao = new UserDao();
        EntityTransaction transaction = new EntityTransaction();
        try {
            transaction.begin(userDao);
            users = userDao.findAll();
            logger.log(Level.DEBUG, "findAllUsers completed successfully");
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
        return users;
    }

    /**
     * This method finds user by user id
     *
     * @param id user id
     * @return User
     * @throws ServiceException if happen DaoException
     */
    public User findUserById(long id) throws ServiceException {
        User user;
        UserDao userDao = new UserDao();
        EntityTransaction transaction = new EntityTransaction();
        try {
            transaction.begin(userDao);
            user = userDao.find(id);
            logger.log(Level.DEBUG, "findUserById completed successfully");
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
        return user;
    }

    /**
     * This method finds user by mail and password
     *
     * @param mail     user mail
     * @param password user password
     * @return User
     * @throws ServiceException if happen DaoException
     */
    public User findUserByMailAndPassword(String mail, String password) throws ServiceException {
        User user;
        UserDao userDao = new UserDao();
        EntityTransaction transaction = new EntityTransaction();
        try {
            transaction.begin(userDao);
            user = userDao.find(mail, password);
            logger.log(Level.DEBUG, "findUserByMailAndPassword completed successfully");
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
        return user;
    }

    /**
     * This method removes user by id
     *
     * @param id user id
     * @throws ServiceException if happen DaoException
     */
    public void deleteUserById(long id) throws ServiceException {
        UserDao userDao = new UserDao();
        EntityTransaction transaction = new EntityTransaction();
        try {
            transaction.begin(userDao);
            userDao.delete(id);
            logger.log(Level.DEBUG, "deleteUserById completed successfully");
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
    }

    /**
     * This method creates new user
     *
     * @param user new user
     * @return boolean as a result of the method
     * @throws ServiceException if happen DaoException
     */
    public boolean createUser(User user) throws ServiceException {
        UserDao userDao = new UserDao();
        EntityTransaction transaction = new EntityTransaction();
        boolean flag;
        try {
            transaction.begin(userDao);
            flag = userDao.create(user);
            logger.log(Level.DEBUG, "createUser completed successfully");
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
        return flag;
    }

    /**
     * This method updates user
     *
     * @param user User
     * @throws ServiceException if happen DaoException
     */
    public void updateUser(User user) throws ServiceException {
        UserDao userDao = new UserDao();
        EntityTransaction transaction = new EntityTransaction();
        try {
            transaction.begin(userDao);
            userDao.update(user);
            logger.log(Level.DEBUG, "updateUser completed successfully");
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
    }

    /**
     * This method updates user photo
     *
     * @param image  uploaded photo
     * @param userId user id
     * @throws ServiceException if happen SQLException
     */
    public void updatePhoto(InputStream image, long userId) throws ServiceException {
        UserDao userDao = new UserDao();
        EntityTransaction transaction = new EntityTransaction();
        try {
            transaction.begin(userDao);
            userDao.updatePhoto(image, userId);
            logger.log(Level.DEBUG, "updatePhoto completed successfully");
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
    }
}
