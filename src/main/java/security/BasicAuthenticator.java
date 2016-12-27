package security;

import com.google.common.base.Optional;
import dao.UserDao;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;

/**
 * Created by takunnithan on 20-12-2016.
 */
public class BasicAuthenticator implements Authenticator<BasicCredentials, User> {

    private UserDao dao;

    public BasicAuthenticator(UserDao dao){
        this.dao = dao;
    }

    @Override
    public Optional<User> authenticate(BasicCredentials credentials) throws AuthenticationException {

        User user = dao.getUser(credentials.getUsername(), credentials.getPassword());
        if (user != null & "Admin".equals(user != null ? user.getRole() : null)) {
            return Optional.of(user);
        }
        return Optional.absent();
    }
    }
