package com.Repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.io.Serializable;
import java.util.List;

@Repository
public class UserRepository implements Serializable {

    private JdbcTemplate jdbc;
    private SimpleJdbcInsert insertUser;
    private User user;


    @Autowired
    public void setDataSource(DataSource dataSource) {
        insertUser = new SimpleJdbcInsert(dataSource)
                .withTableName("users")
                .usingGeneratedKeyColumns("user_id");

        this.jdbc = new JdbcTemplate(dataSource);
    }

    public void addUser(User user) {
        jdbc.update("INSERT INTO users(name, login, password_hash, email) VALUES(?, ?, ?, ?)", new Object[]{user.getName(), user.getLogin(), user.getPassword(), user.getEmail()});
    }

    public User getUser(String login) {
        return jdbc.queryForObject("SELECT * From users where login = ?", userMapper, login);
    }

    public void deleteUser(int user_id) {
        jdbc.queryForObject("DELETE FROM users where user_id = ?", userMapper, user_id);
    }

    public User updateUser(String login, String password, String email, String name) {
        return jdbc.queryForObject("UPDATE users" + " SET password_hash = " + password + ", email = " + email + ", name = " + name + "WHERE login = ?", userMapper, login);
    }

   @Override
    public String toString(){
        return "Id = " + user.getId() + " Login = " + user.getLogin() + " Name = " + user.getName() + "  Email = " + user.getEmail();
    }
    private static final RowMapper<User> userMapper = (rs, rowNum) -> {
        User user;
        User.UserBuilder userBuilder = new User.UserBuilder(rs.getString("login"), rs.getString("password_hash"))
                .id(rs.getInt("user_id"))
                .email(rs.getString("email"))
                .name(rs.getString("name"));
        user = userBuilder.build();

        return user;
    };

    public List<User> getUsers() {
        return jdbc.query("select * from users", userMapper);
    }
}
