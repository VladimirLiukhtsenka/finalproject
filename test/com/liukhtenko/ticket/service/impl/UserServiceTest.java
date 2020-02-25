package com.liukhtenko.ticket.service.impl;

import com.liukhtenko.ticket.entity.User;
import com.liukhtenko.ticket.exception.ServiceException;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class UserServiceTest {
 private UserService userService;
    @BeforeMethod
    public void setUp() {
        userService = new UserService();
    }

    @AfterMethod
    public void tearDown() {
        userService = null;
    }

    @Test
    public void testFindAllUsers() throws ServiceException {
        List<User> users = userService.findAllUsers();
        Assert.assertTrue(users.size()>0);
    }

    @Test
    public void testFindUserById() throws ServiceException {
        long adminId = 1;
        User user = userService.findUserById(adminId);
        String actualName ="Владимир";
        String expectedName =user.getName();
        Assert.assertEquals(actualName,expectedName);
    }
}