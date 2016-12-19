package security;

import java.security.Principal;

/**
 * Created by takunnithan on 20-12-2016.
 */
public class User implements Principal {
    private String userName;

    private boolean isLoggedIn;

    public String getName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

    public User(boolean isLoggedIn, String userName) {
        this.isLoggedIn = isLoggedIn;
        this.userName = userName;
    }
}
