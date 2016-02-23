package org.smartreaction.battletechdomination.view;

import org.smartreaction.battletechdomination.model.User;
import org.smartreaction.battletechdomination.service.GameService;
import org.smartreaction.battletechdomination.service.LoggedInUsers;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.util.List;

@ManagedBean
@ViewScoped
public class LobbyView {
    @ManagedProperty(value="#{userSession}")
    UserSession userSession;

    @EJB
    GameService gameService;

    @EJB
    LoggedInUsers loggedInUsers;

    public List<User> getUsers() {
        return loggedInUsers.getUsers();
    }

    public void startAutoMatch() {
        gameService.autoMatchUser(userSession.getUser());
    }

    public void setUserSession(UserSession userSession) {
        this.userSession = userSession;
    }
}
