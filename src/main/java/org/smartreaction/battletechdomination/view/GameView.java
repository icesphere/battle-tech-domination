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

    public void sendGameMessageToAll(String message) {
        sendGameMessage("*", message);
    }

    public void sendGameMessageToPlayer(String message) {
        sendGameMessage(getPlayer().getPlayerName(), message);
    }

    public void sendGameMessageToOpponent(String message) {
        sendGameMessage(getPlayer().getOpponent().getPlayerName(), message);
    }

    public void sendGameMessage(String recipient, String message) {
        gameService.sendGameMessage(getPlayer().getPlayerName(), recipient, getGame().getGameId(), message);
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
        if (getPlayer().isCardBuyable(card)) {
            return "buyableCard";
        }

        return "";
    }

    public String getActionableCardClass(Card card) {
        String cardClass = getCardClass(card);

        if (getPlayer().isCardActionable(card)) {
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

    public void cardClicked() {
        if (!getPlayer().isYourTurn()) {
            return;
        }

        Map<String, String> paramValues = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

        String source = paramValues.get("source");

        String cardName = paramValues.get("cardName");

        if (source.equals("supply")) {
            Card card = gameService.getCardByName(cardName);
            if (getPlayer().isCardBuyable(card)) {
                getPlayer().buyCard(card);
                sendGameMessageToAll("refresh_supply");
                sendGameMessageToAll("refresh_player_card_info");
                sendGameMessageToAll("refresh_game_log");
            }
        } else  {
            String cardId = paramValues.get("cardId");

            if (source.equals("hand")) {
                Card card = findCardById(getPlayer().getHand(), cardId);
                if (getPlayer().isCardActionable(card)) {
                    getPlayer().playCardFromHand(card);
                    sendGameMessageToAll("refresh_middle_section");
                    sendGameMessageToAll("refresh_right_section");
                    if (card instanceof Resource) {
                        sendGameMessageToPlayer("refresh_supply");
                    }
                }
            } else if (source.equals("playerUnits")) {
                Unit unit = (Unit) findCardById(getPlayer().getDeploymentZone(), cardId);
                if (getPlayer().isCardActionable(unit)) {
                    getPlayer().useUnitAbility(unit);
                    sendGameMessageToAll("refresh_middle_section");
                    sendGameMessageToAll("refresh_right_section");
                }
            }
        }
    }

    public Card findCardById(List<? extends Card> cards, String cardId) {
        for (Card card : cards) {
            if (card.getId().equals(cardId)) {
                return card;
            }
        }
        return null;
    }

    public Card getCardToView() {
        return cardToView;
    }

    public void setCardToView(Card cardToView) {
        this.cardToView = cardToView;
    }

    public void nextPhase() {
        getPlayer().nextPhase();
        if (getPlayer().isActionPhase()) {
            sendGameMessageToOpponent("refresh_middle_section");
            sendGameMessageToOpponent("refresh_right_section");
        } else if (getPlayer().isBuyPhase()) {
            sendGameMessageToOpponent("refresh_right_section");
        }
    }

    public void endTurn() {
        getPlayer().nextPhase();
        if (!getGame().isGameOver()) {
            getPlayer().getOpponent().startTurn();
        }
        sendGameMessageToOpponent("refresh_game_page");
    }
}
