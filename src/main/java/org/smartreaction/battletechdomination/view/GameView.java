package org.smartreaction.battletechdomination.view;

import org.smartreaction.battletechdomination.model.Game;
import org.smartreaction.battletechdomination.model.TurnPhase;
import org.smartreaction.battletechdomination.model.cards.*;
import org.smartreaction.battletechdomination.model.players.Player;
import org.smartreaction.battletechdomination.service.GameService;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.List;

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

    public Game getGame() {
        return userSession.getUser().getCurrentGame();
    }

    public Player getPlayer() {
        return userSession.getUser().getCurrentPlayer();
    }

    public Player getOpponent() {
        return userSession.getUser().getCurrentPlayer().getOpponent();
    }

    public String getCardClass(Card card) {
        if (card instanceof InfantryUnit) {
            return "infantryUnit";
        } else if (card instanceof MechUnit) {
            return "mechUnit";
        } else if (card instanceof VehicleUnit) {
            return "vehicleUnit";
        } else if (card instanceof Resource) {
            return "resource";
        } else if (card instanceof SupportReaction) {
            return "supportReaction";
        } else if (card instanceof Support || card instanceof SupportAttack) {
            return "support";
        } else if (card instanceof Overrun) {
            return "overrun";
        }

        return "";
    }

    public String getTurnPhase() {
        return getTurnPhaseDisplayName(getGame().getCurrentPlayer().getTurnPhase());
    }

    public String getTurnPhaseDisplayName(TurnPhase turnPhase) {
        if (turnPhase == TurnPhase.COMBAT_START || turnPhase == TurnPhase.COMBAT) {
            return "COMBAT PHASE";
        } else if (turnPhase == TurnPhase.ACTION) {
            return "ACTION PHASE";
        } else if (turnPhase == TurnPhase.BUY) {
            return "BUY PHASE";
        } else if (turnPhase == TurnPhase.CLEANUP) {
            return "CLEANUP PHASE";
        }
        return "";
    }

    public List<Card> getCardsForPlayArea() {
        return getGame().getCurrentPlayer().getCardsInPlayArea();
    }
}
