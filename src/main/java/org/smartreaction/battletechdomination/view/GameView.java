package org.smartreaction.battletechdomination.view;

import org.smartreaction.battletechdomination.service.GameService;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;

@ManagedBean
@ViewScoped
public class GameView implements Serializable {
    @ManagedProperty(value="#{userSession}")
    UserSession userSession;

    @EJB
    GameService gameService;

    public void sendGameMessage() {
        gameService.sendGameMessage(userSession.getUser().getUsername(), "*", userSession.getUser().getCurrentGame().getGameId(), "test message");
    }

    public void setUserSession(UserSession userSession) {
        this.userSession = userSession;
    }
}
