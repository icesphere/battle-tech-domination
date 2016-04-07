package org.smartreaction.battletechdomination.view;

import org.smartreaction.battletechdomination.model.User;
import org.smartreaction.battletechdomination.service.LoggedInUsers;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

@ManagedBean
@SessionScoped
public class UserSession implements Serializable {
    @EJB
    LoggedInUsers loggedInUsers;

    private boolean loggedIn;

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean loginAsGuest(String username) {
        if (!loggedInUsers.isUsernameInUse(username)) {
            user = new User();
            user.setUsername(username);
            loggedIn = true;
            loggedInUsers.getUsers().add(user);
            return true;
        }

        return false;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
}
