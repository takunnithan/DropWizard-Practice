package security;

import com.google.common.base.Optional;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;

/**
 * Created by takunnithan on 20-12-2016.
 */
public class BasicAuthenticator implements Authenticator<BasicCredentials, User> {

    public Optional<User> authenticate(BasicCredentials basicCredentials)
        throws AuthenticationException {
        if (basicCredentials.getUsername().equals("username") &&
            basicCredentials.getPassword().equals("password")) {
            return Optional.of(new User(true, basicCredentials.getUsername()));
        }
        return Optional.absent();
    }
}
