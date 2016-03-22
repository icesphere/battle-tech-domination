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
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@ManagedBean
@ViewScoped
public class GameView implements Serializable {
    @ManagedProperty(value="#{userSession}")
    UserSession userSession;

    @EJB
    GameService gameService;

    Card cardToView;

    public void sendGameMessage() {
        gameService.sendGameMessage(userSession.getUser().getUsername(), "*", userSession.getUser().getCurrentGame().getGameId(), "test message");
    }

    @SuppressWarnings("UnusedDeclaration")
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

    public String getSupplyCardClass(Card card) {
        String cardClass = getCardClass(card);

        if (getPlayer().isCardBuyable(card)) {
            cardClass += " buyableCard";
        }

        return cardClass;
    }

    public String getActionableCardClass(Card card) {
        String cardClass = getCardClass(card);

        if (card.isActionable()) {
            cardClass += " actionableCard";
        }

        return cardClass;
    }

    public String getCardClass(Card card) {
        String cardClass = "";
        
        if (card instanceof InfantryUnit) {
            cardClass = "infantryUnit";
        } else if (card instanceof MechUnit) {
            cardClass = "mechUnit";
        } else if (card instanceof VehicleUnit) {
            cardClass = "vehicleUnit";
        } else if (card instanceof Resource) {
            cardClass = "resource";
        } else if (card instanceof SupportReaction) {
            cardClass = "supportReaction";
        } else if (card instanceof Support || card instanceof SupportAttack) {
            cardClass = "support";
        } else if (card instanceof Overrun) {
            cardClass = "overrun";
        }

        return cardClass;
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

    public String getCardTypeDisplayName(CardType cardType) {
        switch (cardType) {
            case UNIT_INFANTRY:
                return "Unit - Infantry";
            case UNIT_MECH:
                return "Unit - Mech";
            case UNIT_VEHICLE:
                return "Unit - Vehicle";
            case OVERRUN_SUPPORT:
                return "Overrun - Support";
            case OVERRUN:
                return "Overrun";
            case SUPPORT:
                return "Support";
            case SUPPORT_REACTION:
                return "Support - Reaction";
            case SUPPORT_ATTACK:
                return "Support - Attack";
            case RESOURCE:
                return "Resource";
            default:
                return cardType.toString();
        }
    }

    public List<Card> getCardsForPlayArea() {
        return getGame().getCurrentPlayer().getCardsInPlayArea();
    }

    public void updateCardView() {
        Map<String, String> paramValues = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String cardName = paramValues.get("cardName");
        cardToView = gameService.getCardByName(cardName);
    }

    public Card getCardToView() {
        return cardToView;
    }

    public void setCardToView(Card cardToView) {
        this.cardToView = cardToView;
    }

    public void nextPhase() {
        getPlayer().nextPhase();
    }
}
