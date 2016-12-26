package security;

import io.dropwizard.auth.Authorizer;

/**
 * Created by takunnithan on 20-12-2016.
 */
public class BasicAuthorizer implements Authorizer<User> {
    @Override
    public boolean authorize(User user, String role) {
        return user.getName().equals("username") && role.equals(user.getRole());
    }
}
