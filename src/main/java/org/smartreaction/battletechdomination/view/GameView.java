package org.smartreaction.battletechdomination.view;

import org.apache.commons.lang3.StringUtils;
import org.smartreaction.battletechdomination.model.ChatMessage;
import org.smartreaction.battletechdomination.model.Game;
import org.smartreaction.battletechdomination.model.TurnPhase;
import org.smartreaction.battletechdomination.model.cards.*;
import org.smartreaction.battletechdomination.model.cards.abilities.QuadERPPCs;
import org.smartreaction.battletechdomination.model.cards.actions.Action;
import org.smartreaction.battletechdomination.model.cards.actions.ActionResult;
import org.smartreaction.battletechdomination.model.cards.actions.CardAction;
import org.smartreaction.battletechdomination.model.cards.actions.DiscardCardsFromHand;
import org.smartreaction.battletechdomination.model.cards.overrun.RaidedSupplies;
import org.smartreaction.battletechdomination.model.cards.support.reaction.ExpertMechTechs;
import org.smartreaction.battletechdomination.model.cards.support.reaction.ForwardBase;
import org.smartreaction.battletechdomination.model.players.Player;
import org.smartreaction.battletechdomination.service.GameService;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;
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

    String chatMessage = "";

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
        return getPlayer().getOpponent();
    }

    public Action getAction() {
        return getPlayer().getCurrentAction();
    }

    public String getSupplyCardClass(Card card) {
        if (highlightSupplyCard(card)) {
            return "buyableCard";
        }

        return "";
    }

    public boolean highlightSupplyCard(Card card) {
        if (!getPlayer().isYourTurn()) {
            return false;
        } else if (getAction() != null) {
            return getAction().isCardActionable(card, Card.CARD_LOCATION_SUPPLY, getPlayer());
        } else {
            return getPlayer().isCardBuyable(card);
        }
    }

    public String getActionableCardClass(Card card, String source) {
        String cardClass = getCardClass(card);

        if (highlightCard(card, source)) {
            cardClass += " actionableCard";
        }

        return cardClass;
    }

    public boolean highlightCard(Card card, String cardLocation) {
        if (!getPlayer().isYourTurn()) {
            return false;
        } else if (getAction() != null) {
            return getAction().isCardActionable(card, cardLocation, getPlayer());
        } else {
            return card.isActionable(getPlayer(), cardLocation);
        }
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
        } else if (card instanceof Support) {
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
        return getGame().getCurrentPlayer().getCardsPlayed();
    }

    public List<Card> getPlayerDeploymentZoneCards() {
        return getDeploymentZoneCards(getPlayer());
    }

    public List<Card> getOpponentDeploymentZoneCards() {
        return getDeploymentZoneCards(getOpponent());
    }

    public List<Card> getDeploymentZoneCards(Player player) {
        List<Card> cards = new ArrayList<>(player.getDeploymentZone());

        if (player.isForwardBaseInDeploymentZone()) {
            cards.add(new ForwardBase());
        }

        if (player.isExpertMechTechsInDeploymentZone()) {
            cards.add(new ExpertMechTechs());
        }

        return cards;
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

        if (source.equals("basicSupply") || source.equals("supplyGrid")) {
            Card card;
            if (source.equals("basicSupply")) {
                card = gameService.getCardByName(cardName);
            } else {
                String cardId = paramValues.get("cardId");
                card = findCardById(getGame().getSupplyGrid(), cardId);
            }
            if (highlightSupplyCard(card)) {
                if (getAction() != null) {
                    handleCardClickedForAction(card, source);
                } else {
                    getPlayer().buyCard(card);
                    sendGameMessageToAll("refresh_supply");
                    sendGameMessageToAll("refresh_right_section");
                }
            }
        } else  {
            String cardId = paramValues.get("cardId");

            if (source.equals("hand")) {
                Card card = findCardById(getPlayer().getHand(), cardId);
                if (highlightCard(card, source)) {
                    if (getAction() != null) {
                        handleCardClickedForAction(card, source);
                    } else {
                        getPlayer().playCardFromHand(card);
                        refreshGamePageWithCheckForAction();
                    }
                }
            } else if (source.equals("playerUnits")) {
                Card card = findCardById(getPlayer().getDeploymentZone(), cardId);
                if (highlightCard(card, source)) {
                    if (getAction() != null) {
                        handleCardClickedForAction(card, source);
                    } else {
                        getPlayer().useUnitAbility((Unit) card);
                        sendGameMessageToAll("refresh_middle_section");
                        sendGameMessageToAll("refresh_right_section");
                    }
                }
            }
        }

        checkForAction();
    }

    public Card findCardById(List<? extends Card> cards, String cardId) {
        for (Card card : cards) {
            if (card.getId().equals(cardId)) {
                return card;
            }
        }
        return null;
    }

    public void handleCardClickedForAction(Card card, String cardLocation) {
        Action action = getAction();
        ActionResult result = new ActionResult();
        result.setCardLocation(cardLocation);

        if (action instanceof DiscardCardsFromHand) {
            DiscardCardsFromHand discardCardsFromHand = (DiscardCardsFromHand) action;
            discardCardsFromHand.getSelectedCards().add(card);
            if (discardCardsFromHand.getNumCardsToDiscard() == discardCardsFromHand.getSelectedCards().size()) {
                result.getSelectedCards().addAll(discardCardsFromHand.getSelectedCards());
            } else {
                return;
            }
        } else if (action instanceof CardAction && (((CardAction) action).getCardActionCard() instanceof QuadERPPCs || ((CardAction) action).getCardActionCard() instanceof RaidedSupplies)) {
            CardAction cardAction = (CardAction) action;
            cardAction.getSelectedCards().add(card);
            if (cardAction.getSelectedCards().size() == 2) {
                result.getSelectedCards().addAll(cardAction.getSelectedCards());
            } else {
                return;
            }
        } else {
            result.getSelectedCards().add(card);
        }

        getPlayer().actionResult(action, result);

        refreshGamePageWithCheckForAction();
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
        checkForAction();
    }

    public void playAll() {
        getPlayer().playAll();
        sendGameMessageToOpponent("refresh_game_page");
        checkForAction();
    }

    public void endTurn() {
        getPlayer().nextPhase();
        if (getAction() != null) {
            sendShowActionToPlayer();
        } else {
            refreshAfterEndTurn();
        }
    }

    public void refreshAfterEndTurn() {
        if (!getGame().isGameOver()) {
            getPlayer().getOpponent().startTurn();
        }
        if (getPlayer().getOpponent().getCurrentAction() != null) {
            sendGameMessageToOpponent("show_action");
        } else {
            sendGameMessageToOpponent("refresh_game_page");
        }
    }

    public void choiceMade(int choiceSelected) {
        ActionResult result = new ActionResult();
        result.setChoiceSelected(choiceSelected);
        getPlayer().actionResult(getAction(), result);
        refreshGamePageWithCheckForAction();
    }

    public void refreshGamePageWithCheckForAction() {
        if (getAction() != null) {
            sendShowActionToPlayer();
            sendGameMessageToOpponent("refresh_game_page");
        } else {
            if (!getPlayer().isYourTurn()) {
                refreshAfterEndTurn();
            } else {
                sendGameMessageToAll("refresh_game_page");
            }
        }
    }

    public void checkForAction() {
        if (getAction() != null) {
            sendShowActionToPlayer();
        }
    }

    public void sendShowActionToPlayer() {
        if (getAction().showActionDialog()) {
            sendGameMessageToPlayer("show_action");
        } else {
            sendGameMessageToPlayer("refresh_game_page");
        }
    }

    public String quitGame() {
        getGame().quitGame(getPlayer());
        sendGameMessageToOpponent("refresh_game_page");
        return exitGame();
    }

    public String exitGame() {
        userSession.getUser().setCurrentGame(null);
        userSession.getUser().setCurrentPlayer(null);

        return "lobby.xhtml?faces-redirect=true";
    }

    public String getChatMessage() {
        return chatMessage;
    }

    public void setChatMessage(String chatMessage) {
        this.chatMessage = chatMessage;
    }

    public void sendChatMessage() {
        if (!StringUtils.isEmpty(chatMessage)) {
            getGame().getChatMessages().add(new ChatMessage(getPlayer().getPlayerName(), chatMessage));
            chatMessage = "";
            sendGameMessageToOpponent("refresh_chat");
        }
    }
}
