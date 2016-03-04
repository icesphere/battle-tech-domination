package org.smartreaction.battletechdomination.view;

import org.smartreaction.battletechdomination.service.LoggedInUsers;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;

@ManagedBean
@ViewScoped
public class LoginView implements Serializable {
    @ManagedProperty(value = "#{userSession}")
    UserSession userSession;

    @EJB
    LoggedInUsers loggedInUsers;

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String loginAsGuest() {
        userSession.loginAsGuest(username);

        return "lobby.xhtml?faces-redirect=true";
    }

    public void setUserSession(UserSession userSession) {
        this.userSession = userSession;
    }
}
