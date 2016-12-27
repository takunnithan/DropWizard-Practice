package dao;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;
import security.User;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by takunnithan on 28-12-2016.
 */
public class UserMapper implements ResultSetMapper<User> {

    @Override
    public User map(int i, ResultSet resultSet, StatementContext statementContext) throws SQLException {
        return new User(resultSet.getString("username"),
            resultSet.getString("password"),
            resultSet.getString("role"));
    }
}
