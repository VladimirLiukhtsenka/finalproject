package com.liukhtenko.ticket.service;

import com.liukhtenko.ticket.entity.User;
import com.liukhtenko.ticket.exception.ServiceException;

import java.util.List;

public interface UserServiceInterface {
    List<User> findAllUsers() throws ServiceException;
    User findUserById(long id) throws ServiceException;
    User findUserByMailAndPassword(String mail, String password) throws ServiceException;
    void deleteUserById(long id) throws ServiceException;
    boolean createUser(User user) throws ServiceException;
    void updateUser(User user) throws ServiceException;

}
