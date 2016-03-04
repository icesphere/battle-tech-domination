package org.smartreaction.battletechdomination.view;

import org.primefaces.push.EventBus;
import org.primefaces.push.EventBusFactory;
import org.smartreaction.battletechdomination.service.GameService;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;

@ManagedBean
@ViewScoped
public class GameView implements Serializable {
    private final EventBus eventBus = EventBusFactory.getDefault().eventBus();

    private final static String CHANNEL = "/game/1/";

    @ManagedProperty(value="#{userSession}")
    UserSession userSession;

    @EJB
    GameService gameService;

    public void sendGameMessage() {
        eventBus.publish(CHANNEL + "*", userSession.getUser().getUsername() + ": " + "test message");
    }

    public void setUserSession(UserSession userSession) {
        this.userSession = userSession;
    }
}
