package Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;

public class UserRepository {
    private JdbcTemplate jdbc = new JdbcTemplate();
    private SimpleJdbcInsert insertUser;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        insertUser = new SimpleJdbcInsert(dataSource)
                .withTableName("users")
                .usingGeneratedKeyColumns("user_id");

        this.jdbc = new JdbcTemplate(dataSource);
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

    private static final RowMapper<User> userMapper = (rs, rowNum) -> {
        User user = null;
        User.UserBuilder userBuilder = new User.UserBuilder(rs.getString("login"), rs.getString("password_hash"))
                .id(rs.getInt("user_id"))
                .email(rs.getString("email"))
                .name(rs.getString("name"));
        user = user.buildUser();

        return user;
    };
}
