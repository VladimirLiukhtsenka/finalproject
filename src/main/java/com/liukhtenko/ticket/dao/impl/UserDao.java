package com.liukhtenko.ticket.dao.impl;

        import com.liukhtenko.ticket.dao.AbstractDao;
        import com.liukhtenko.ticket.dao.ColumnName;
        import com.liukhtenko.ticket.entity.User;
        import com.liukhtenko.ticket.exception.DaoException;

        import java.io.InputStream;
        import java.sql.PreparedStatement;
        import java.sql.ResultSet;
        import java.sql.SQLException;
        import java.sql.Statement;
        import java.util.ArrayList;
        import java.util.Base64;
        import java.util.List;

/**
 * This class allows to make requests to the database
 * and change the status or view users
 *
 * @author Vladimir Liukhtenko
 * @version 1.25 02 Feb 2020
 */
public class UserDao extends AbstractDao<Long, User> {
    private static final String SQL_SELECT_ALL_USERS =
            "SELECT id, phone, name, surname, father_name, gender, password, mail, role_id, photo FROM users;";
    private static final String SQL_SELECT_USER_BY_ID =
            "SELECT id, phone, name, surname, father_name, gender, password, mail, role_id, photo FROM users WHERE id=?;";
    private static final String SQL_SELECT_USER_BY_MAIL_AND_Password =
            "SELECT id, phone, name, surname, father_name, gender, password, mail, role_id, photo from users " +
                    "WHERE mail=? and password=?;";
    private static final String SQL_DELETE_USER_BY_ID =
            "DELETE FROM users WHERE id=?;";
    private static final String SQL_CREATE_USER =
            "INSERT INTO users (phone, name, surname, father_name, gender, password, mail, role_id) values (?,?,?,?,?,?,?,?);";
    private static final String SQL_UPDATE_USER =
            "UPDATE users SET phone=?, name=?, surname=?, father_name=?, gender=?, password=?, mail=?," +
                    " role_id=? WHERE id=?";
    private static final String SQL_UPDATE_PHOTO =
            "UPDATE users SET photo=? WHERE id=?";

    /**
     * This method finds all users
     *
     * @return List User
     * @throws DaoException if happen SQLException
     */
    @Override
    public List<User> findAll() throws DaoException {
        List<User> users = new ArrayList<>();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQL_SELECT_ALL_USERS);
            while (resultSet.next()) {
                User user = findUser(resultSet);
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

    /**
     * This method finds user by user id
     *
     * @param id user id
     * @return User
     * @throws DaoException if happen SQLException
     */
    public User find(Long id) throws DaoException {
        User user;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(SQL_SELECT_USER_BY_ID);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = findUser(resultSet);
            } else {
                throw new DaoException("Unable to find user");
            }
        } catch (SQLException e) {
            throw new DaoException("Unable to find user", e);
        } finally {
            close(resultSet);
            close(statement);
        }
        return user;
    }

    /**
     * This method finds user by mail and password
     *
     * @param mail     user mail
     * @param password user password
     * @return User
     * @throws DaoException if happen SQLException
     */
    public User find(String mail, String password) throws DaoException {
        User user;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(SQL_SELECT_USER_BY_MAIL_AND_Password);
            statement.setString(1, mail);
            statement.setString(2, password);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = findUser(resultSet);
            } else {
                throw new DaoException("Unable to find user");
            }
        } catch (SQLException e) {
            throw new DaoException("Unable to find user", e);
        } finally {
            close(resultSet);
            close(statement);
        }
        return user;
    }

    /**
     * This method removes user by id
     *
     * @param id user id
     * @return boolean as a result of the method
     * @throws DaoException if happen SQLException
     */
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

    /**
     * This method creates new user
     *
     * @param user new user
     * @return boolean as a result of the method
     * @throws DaoException if happen SQLException
     */
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

    /**
     * This method updates user photo
     *
     * @param image  uploaded photo
     * @param userId user id
     * @throws DaoException if happen SQLException
     */
    public void updatePhoto(InputStream image, long userId) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_UPDATE_PHOTO);
            statement.setBlob(1, image);
            statement.setLong(2, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Unable to update photo", e);
        } finally {
            close(statement);
        }
    }

    /**
     * This method updates user
     *
     * @param user User
     * @return boolean as a result of the method
     * @throws DaoException if happen SQLException
     */
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
            statement.setLong(8, user.getRoleID());
            statement.setLong(9, user.getId());
            flag = (1 == statement.executeUpdate());
        } catch (SQLException e) {
            throw new DaoException("Unable to update user", e);
        } finally {
            close(statement);
        }
        return flag;
    }

    /**
     * This method finds an user from resultSet
     *
     * @param resultSet from statement
     * @return User
     * @throws SQLException if happen SQLException
     */
    private User findUser(ResultSet resultSet) throws SQLException {
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
        if (resultSet.getBytes(ColumnName.PHOTO) != null) {
            byte[] imgData = resultSet.getBytes(ColumnName.PHOTO);
            String encode = Base64.getEncoder().encodeToString(imgData);
            user.setPhoto(encode);
        }
        return user;
    }

}
