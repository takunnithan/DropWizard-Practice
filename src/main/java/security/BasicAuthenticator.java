package security;

import com.google.common.base.Optional;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;

/**
 * Created by takunnithan on 20-12-2016.
 */
public class BasicAuthenticator implements Authenticator<BasicCredentials, User> {

    @Override
    public Optional<User> authenticate(BasicCredentials credentials) throws AuthenticationException {
        if ("password".equals(credentials.getPassword()) & "username".equals(credentials.getUsername())) {
            return Optional.of(new User(credentials.getUsername(), credentials.getPassword(), "Admin"));
        }
        return Optional.absent();
    }
    }
