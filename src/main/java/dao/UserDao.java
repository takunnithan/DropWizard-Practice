package dao;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;
import security.User;

/**
 * Created by takunnithan on 28-12-2016.
 */
public interface UserDao {

    @Mapper(UserMapper.class)
    @SqlQuery("select * from user where username= :username and password= :password")
    User getUser(@Bind("username") String username, @Bind("password") String password);
}
