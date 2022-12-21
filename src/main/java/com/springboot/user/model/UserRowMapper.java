package com.springboot.user.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

// Model class to update User after fetching details from database
public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {

        User user = new User();
        user.setEmail(rs.getString("email"));
        user.setLastName(rs.getString("lastName"));
        user.setFirstName(rs.getString("firstName"));
        user.setUserPassword(rs.getString("userPassword"));
        user.setPhoneNumber(rs.getString("phoneNumber"));

        return user;

    }
}
