package org.smartreaction.battletechdomination.view;

import org.apache.commons.lang3.StringUtils;
import org.smartreaction.battletechdomination.model.ChatMessage;
import org.smartreaction.battletechdomination.model.Game;
import org.smartreaction.battletechdomination.model.TurnPhase;
import org.smartreaction.battletechdomination.model.cards.*;
import org.smartreaction.battletechdomination.model.cards.actions.Action;
import org.smartreaction.battletechdomination.model.cards.actions.ActionResult;
import org.smartreaction.battletechdomination.model.cards.actions.SelectFromDiscardAction;
import org.smartreaction.battletechdomination.model.cards.support.reaction.Ambush;
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
        
    String chatMessage = "";
    
    boolean showingCards;
    
    String showingCardsTitle;
    
    List<Card> cardsToShow;
    
    String cardsToShowSource;
    
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
        String cardClass = "";
        
        if (highlightSupplyCard(card)) {
            cardClass = "buyableCard";
        }
        
        Action action = getAction();
        if (action != null && action.isCardSelected(card)) {
            cardClass += " selected";
        }
        
        return cardClass;
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
        
        Action action = getAction();
        if (action != null && action.isCardSelected(card)) {
            cardClass += " selected";
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
            return "Combat Phase";
        } else if (turnPhase == TurnPhase.ACTION) {
            return "Action Phase";
        } else if (turnPhase == TurnPhase.BUY) {
            return "Buy Phase";
        } else if (turnPhase == TurnPhase.CLEANUP) {
            return "Cleanup Phase";
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
    
    public List<Card> getBoolDeploymentZoneCards(boolean isOpponent) {
        if(!isOpponent) {
            return getDeploymentZoneCards(getPlayer());
        }
        else {
            return getDeploymentZoneCards(getOpponent());
        }
    }
    
    public String getSourceOfCards(boolean isOpponent) {
        if(!isOpponent) return "playerUnits";
        else return "opponentUnits";
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
        
        if (player.isAmbushInDeploymentZone()) {
            cards.add(new Ambush());
        }
        
        return cards;
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
                    sendGameMessageToAll("refresh_left_section");
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
            } else if (source.equals("playerUnits") || source.equals("opponentUnits")) {
                Player player;
                if (source.equals("opponentUnits")) {
                    player = getOpponent();
                } else {
                    player = getPlayer();
                }
                Card card = findCardById(player.getDeploymentZone(), cardId);
                if (highlightCard(card, source)) {
                    if (getAction() != null) {
                        handleCardClickedForAction(card, source);
                    } else {
                        getPlayer().useUnitAbility((Unit) card);
                        sendGameMessageToAll("refresh_left_section");
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
        
        result.getSelectedCards().add(card);
        
        getPlayer().actionResult(action, result);
        
        refreshGamePageWithCheckForAction();
    }
    
    public void nextPhase() {
        getPlayer().nextPhase();
        sendGameMessageToOpponent("refresh_game_page");
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
                sendGameMessageToOpponent("refresh_game_page");
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
        sendGameMessageToPlayer("refresh_game_page");
    }
    
    public String quitGame() {
        getGame().quitGame(getPlayer());
        sendGameMessageToOpponent("refresh_game_page");
        return exitGame();
    }
    
    public void readyGame() {
        getGame().playerReady(getPlayer());
        sendGameMessageToAll("refresh_overlay");
    }
    
    public String exitGame() {
        userSession.getUser().setCurrentGame(null);
        userSession.getUser().setCurrentPlayer(null);
        
        gameService.refreshLobby(userSession.getUser());
        
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
            sendGameMessageToAll("refresh_chat");
        }
    }
    
    public void showCards(List<Card> cards, String title, String source) {
        if (!cards.isEmpty()) {
            showingCards = true;
            showingCardsTitle = title;
            cardsToShow = cards;
            cardsToShowSource = source;
            
            sendGameMessageToPlayer("refresh_game_page");
        }
    }
    
    public void hideCardsToShow() {
        showingCards = false;
        cardsToShow = null;
    }
    
    public boolean isShowingCards() {
        return showingCards;
    }
    
    public List<Card> getCardsToShow() {
        return cardsToShow;
    }
    
    public String getShowingCardsTitle() {
        return showingCardsTitle;
    }
    
    public String getCardsToShowSource() {
        return cardsToShowSource;
    }
    
    public void doNotUseAction() {
        getPlayer().actionResult(getAction(), ActionResult.doNotUseActionResult());
        sendGameMessageToPlayer("refresh_game_page");
    }
    
    public void doneWithAction() {
        getPlayer().actionResult(getAction(), ActionResult.doneWithActionResult());
        sendGameMessageToAll("refresh_game_page");
    }
    
    public boolean isHighlightDiscardButton() {
        Action action = getAction();
        return action != null && getPlayer().isYourTurn() && !getPlayer().getDiscard().isEmpty()
                && (action instanceof SelectFromDiscardAction);
    }
}
