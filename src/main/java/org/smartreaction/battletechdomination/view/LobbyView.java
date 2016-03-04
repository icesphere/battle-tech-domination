package org.smartreaction.battletechdomination.view;

import org.smartreaction.battletechdomination.model.User;
import org.smartreaction.battletechdomination.service.GameService;
import org.smartreaction.battletechdomination.service.LoggedInUsers;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.List;

@ManagedBean
@ViewScoped
public class LobbyView implements Serializable {
    @ManagedProperty(value="#{userSession}")
    UserSession userSession;

    @EJB
    GameService gameService;

    @EJB
    LoggedInUsers loggedInUsers;

    public List<User> getUsers() {
        return loggedInUsers.getUsers();
    }

    public String startAutoMatch() {
        gameService.autoMatchUser(userSession.getUser());
        if (userSession.getUser().getCurrentGame() != null) {
            return "game.xhtml?faces-redirect=true";
        }
        return null;
    }

    public void setUserSession(UserSession userSession) {
        this.userSession = userSession;
    }
}
