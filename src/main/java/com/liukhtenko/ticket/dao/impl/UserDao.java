package com.liukhtenko.ticket.dao.impl;

import com.liukhtenko.ticket.dao.AbstractDao;
import com.liukhtenko.ticket.dao.ColumnName;
import com.liukhtenko.ticket.entity.User;
import com.liukhtenko.ticket.exception.DaoException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDao extends AbstractDao<Long, User> {
    private static final String SQL_SELECT_ALL_USERS =
            " SELECT id, phone, name, surname, father_name, gender, password, mail, role_id FROM users;";
    private static final String SQL_SELECT_USER_BY_ID =
            "SELECT id, phone, name, surname, father_name, gender, password, mail, role_id from users WHERE id=?;";
    private static final String SQL_SELECT_USER_BY_MAIL_AND_Password =
            "SELECT id, phone, name, surname, father_name, gender, password, mail, role_id from users " +
                    "WHERE mail=? and password=?;";
    private static final String SQL_DELETE_USER_BY_ID =
            "DELETE from users WHERE id=?;";
    private static final String SQL_CREATE_USER =
            "insert into users (phone, name, surname, father_name, gender, password, mail, role_id) values (?,?,?,?,?,?,?,?);";
    private static final String SQL_UPDATE_USER =
            "UPDATE users SET phone=?, name=?, surname=?, father_name=?, gender=?, password=?, mail=?," +
                    " role_id=? WHERE id=?";

    @Override
    public List<User> findAll() throws DaoException {
        List<User> users = new ArrayList<>();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQL_SELECT_ALL_USERS);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong(ColumnName.ID));
                user.setPhone(resultSet.getString(ColumnName.PHONE));
                user.setName(resultSet.getString(ColumnName.NAME));
                user.setSurName(resultSet.getString(ColumnName.SURNAME));
                user.setFatherName(resultSet.getString(ColumnName.FATHER_NAME));
                user.setGender(resultSet.getByte(ColumnName.GENDER));
                user.setPassword(resultSet.getString(ColumnName.PASSWORD));
                user.setMail(resultSet.getString(ColumnName.MAIL));
                user.setRoleID(resultSet.getLong(ColumnName.ROLE_ID));
                users.add(user);
            }
        } catch (SQLException e) {
            throw new DaoException("Unable to find users", e);
        } finally {
            close(resultSet);
            close(statement);
        }
        return users;
    }

    @Override
    public User find(Long id) throws DaoException {
        User user = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(SQL_SELECT_USER_BY_ID);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getLong(ColumnName.ID));
                user.setPhone(resultSet.getString(ColumnName.PHONE));
                user.setName(resultSet.getString(ColumnName.NAME));
                user.setSurName(resultSet.getString(ColumnName.SURNAME));
                user.setFatherName(resultSet.getString(ColumnName.FATHER_NAME));
                user.setGender(resultSet.getByte(ColumnName.GENDER));
                user.setPassword(resultSet.getString(ColumnName.PASSWORD));
                user.setMail(resultSet.getString(ColumnName.MAIL));
                user.setRoleID(resultSet.getLong(ColumnName.ROLE_ID));
            }
        } catch (SQLException e) {
            throw new DaoException("Unable to find user", e);
        } finally {
            close(resultSet);
            close(statement);
        }
        return user;
    }

    public User find(String mail, String password) throws DaoException {
        User user = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(SQL_SELECT_USER_BY_MAIL_AND_Password);
            statement.setString(1, mail);
            statement.setString(2, password);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getLong(ColumnName.ID));
                user.setPhone(resultSet.getString(ColumnName.PHONE));
                user.setName(resultSet.getString(ColumnName.NAME));
                user.setSurName(resultSet.getString(ColumnName.SURNAME));
                user.setFatherName(resultSet.getString(ColumnName.FATHER_NAME));
                user.setGender(resultSet.getByte(ColumnName.GENDER));
                user.setPassword(resultSet.getString(ColumnName.PASSWORD));
                user.setMail(resultSet.getString(ColumnName.MAIL));
                user.setRoleID(resultSet.getLong(ColumnName.ROLE_ID));
            }
        } catch (SQLException e) {
            throw new DaoException("Unable to find user", e);
        } finally {
            close(resultSet);
            close(statement);
        }
        return user;
    }

    @Override
    public boolean delete(Long id) throws DaoException {
        boolean flag;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_DELETE_USER_BY_ID);
            statement.setLong(1, id);
            flag = (1 == statement.executeUpdate());
        } catch (SQLException e) {
            throw new DaoException("Unable to delete user", e);
        } finally {
            close(statement);
        }
        return flag;
    }


    @Override
    public boolean delete(User entity) throws DaoException {
        return false; // FIXME: 23.01.2020 удалить метод из AbstractDAO
    }

    @Override
    public boolean create(User user) throws DaoException {
        boolean flag;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_CREATE_USER);
            statement.setString(1, user.getPhone());
            statement.setString(2, user.getName());
            statement.setString(3, user.getSurName());
            statement.setString(4, user.getFatherName());
            statement.setInt(5, user.getGender());
            statement.setString(6, user.getPassword());
            statement.setString(7, user.getMail());
            statement.setInt(8, (int) user.getRoleID());
            flag = (1 == statement.executeUpdate());
        } catch (SQLException e) {
            throw new DaoException("Unable to create user", e);
        } finally {
            close(statement);
        }
        return flag;
    }


    @Override
    public boolean update(User user) throws DaoException {
        boolean flag;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_UPDATE_USER);
            statement.setString(1, user.getPhone());
            statement.setString(2, user.getName());
            statement.setString(3, user.getSurName());
            statement.setString(4, user.getFatherName());
            statement.setInt(5, user.getGender());
            statement.setString(6, user.getPassword());
            statement.setString(7, user.getMail());
            statement.setInt(8, (int) user.getRoleID());
            statement.setInt(9, (int) user.getId());
            flag = (1 == statement.executeUpdate());
        } catch (SQLException e) {
            throw new DaoException("Unable to update user", e);
        } finally {
            close(statement);
        }
        return flag;
    }
}
