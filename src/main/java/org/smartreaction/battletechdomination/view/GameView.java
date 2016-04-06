package org.smartreaction.battletechdomination.view;

import org.smartreaction.battletechdomination.model.Game;
import org.smartreaction.battletechdomination.model.TurnPhase;
import org.smartreaction.battletechdomination.model.cards.*;
import org.smartreaction.battletechdomination.model.cards.abilities.HeavyFireSupport;
import org.smartreaction.battletechdomination.model.cards.abilities.MobileFireSupport;
import org.smartreaction.battletechdomination.model.cards.abilities.QuadERPPCs;
import org.smartreaction.battletechdomination.model.cards.actions.*;
import org.smartreaction.battletechdomination.model.cards.support.BattlefieldSalvage;
import org.smartreaction.battletechdomination.model.cards.support.HiddenBase;
import org.smartreaction.battletechdomination.model.cards.support.Refinery;
import org.smartreaction.battletechdomination.model.cards.support.attack.CloseAirSupport;
import org.smartreaction.battletechdomination.model.cards.support.attack.LongTomBattery;
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
        if (getPlayer().isYourTurn() && getAction() != null) {
            Action action = getAction();
            if (action instanceof FreeCardFromSupplyToTopOfDeck) {
                return true;
            } else if (action instanceof FreeResourceCardIntoHand && card.isResource()) {
                return true;
            }
        } else {
            if (getPlayer().isCardBuyable(card)) {
                return true;
            }
        }

        return false;
    }

    public String getActionableCardClass(Card card, String source) {
        String cardClass = getCardClass(card);

        if (highlightCard(card, source)) {
            cardClass += " actionableCard";
        }

        return cardClass;
    }

    public boolean highlightCard(Card card, String source) {
        if (getPlayer().isYourTurn() && getAction() != null) {
            Action action = getAction();
            if (action instanceof CardFromHandToTopOfDeck) {
                if (source.equals("hand")) {
                    return true;
                }
            } else if (action instanceof DamageOpponentUnit) {
                if (source.equals("opponentUnits")) {
                    return true;
                }
            } else if (action instanceof DamageOpponentUnitMaxCost) {
                if (source.equals("opponentUnits") && card.getIndustryCost() <= ((DamageOpponentUnitMaxCost) action).getMaxCost()) {
                    return true;
                }
            } else if (action instanceof DamageUnit) {
                if (source.equals("playerUnits")) {
                    DamageUnit damageUnitAction = (DamageUnit) action;
                    if (damageUnitAction.getCardType() == null || damageUnitAction.getCardType() == card.getCardType()) {
                        return true;
                    }
                }
            } else if (action instanceof DamageUnitMaxCost) {
                if (source.equals("playerUnits") && card.getIndustryCost() <= ((DamageUnitMaxCost) action).getMaxCost()) {
                    return true;
                }
            } else if (action instanceof DamageUnitMinCost) {
                if (source.equals("playerUnits") && card.getIndustryCost() >= ((DamageUnitMinCost) action).getMinCost()) {
                    return true;
                }
            } else if (action instanceof DiscardCardsFromHand) {
                if (source.equals("hand")) {
                    if (!((DiscardCardsFromHand) action).getSelectedCards().contains(card)) {
                        return true;
                    }
                }
            } else if (action instanceof ScrapCardFromHandOrDiscard) {
                if (source.equals("hand")) {
                    //todo allow selecting from discard
                    return true;
                }
            } else if (action instanceof ScrapOpponentUnitMaxCost) {
                if (source.equals("opponentUnits") && card.getIndustryCost() <= ((ScrapOpponentUnitMaxCost) action).getMaxCost()) {
                    return true;
                }
            } else if (action instanceof UnitFromDeploymentZoneToHand) {
                if (source.equals("playerUnits")) {
                    return true;
                }
            } else if (action instanceof UnitFromHandToTopOfDeck) {
                if (source.equals("hand")) {
                    return true;
                }
            } else if (action instanceof CardAction) {
                CardAction cardAction = (CardAction) action;
                if (cardAction.getCard() instanceof BattlefieldSalvage) {
                    if (card.isUnit() && (source.equals("hand") || source.equals("playerUnits"))) {
                        return true;
                    }
                } else if (cardAction.getCard() instanceof HiddenBase) {
                    if (source.equals("hand")) {
                        return true;
                    }
                } else if (cardAction.getCard() instanceof Refinery) {
                    if (card.isResource() && source.equals("hand")) {
                        return true;
                    }
                } else if (cardAction.getCard() instanceof CloseAirSupport) {
                    if (source.equals("hand")) {
                        return true;
                    }
                } else if (cardAction.getCard() instanceof LongTomBattery) {
                    if (source.equals("hand")) {
                        return true;
                    }
                } else if (cardAction.getCard() instanceof HeavyFireSupport) {
                    if (card.isUnit() && source.equals("hand")) {
                        return true;
                    }
                } else if (cardAction.getCard() instanceof MobileFireSupport) {
                    if (source.equals("hand")) {
                        return true;
                    }
                } else if (cardAction.getCard() instanceof QuadERPPCs) {
                    if (source.equals("hand")) {
                        if (!((CardAction) action).getSelectedCards().contains(card)) {
                            return true;
                        }
                        return true;
                    }
                }
            }
            //todo CardsOnTopOfDeckInAnyOrder
            //todo DiscardCardsForStrategicBombing
            //todo YesNoAbilityAction
        } else if ((source.equals("playerUnits") || source.equals("hand")) && getPlayer().isCardActionable(card)) {
            return true;
        }

        return false;
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
        return getGame().getCurrentPlayer().getCardsPlayed();
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

        getGame().gameLog("clicked " + cardName + " from " + source);

        if (source.equals("basic_supply") || source.equals("supply_grid")) {
            Card card;
            if (source.equals("basic_supply")) {
                card = gameService.getCardByName(cardName);
            } else {
                String cardId = paramValues.get("cardId");
                card = findCardById(getGame().getSupplyGrid(), cardId);
            }
            if (highlightSupplyCard(card)) {
                if (getAction() != null) {
                    handleCardClickedForAction(card);
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
                        handleCardClickedForAction(card);
                    } else {
                        getPlayer().playCardFromHand(card);
                        refreshGamePageWithCheckForAction();
                    }
                }
            } else if (source.equals("playerUnits")) {
                Unit unit = (Unit) findCardById(getPlayer().getDeploymentZone(), cardId);
                if (highlightCard(unit, source)) {
                    if (getAction() != null) {
                        handleCardClickedForAction(unit);
                    } else {
                        getPlayer().useUnitAbility(unit);
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

    public void handleCardClickedForAction(Card card) {
        Action action = getAction();
        if (action instanceof DiscardCardsFromHand) {
            DiscardCardsFromHand discardCardsFromHand = (DiscardCardsFromHand) action;
            discardCardsFromHand.getSelectedCards().add(card);
            if (discardCardsFromHand.getNumCardsToDiscard() == discardCardsFromHand.getSelectedCards().size()) {
                ActionResult result = new ActionResult();
                result.getSelectedCards().addAll(discardCardsFromHand.getSelectedCards());
                getPlayer().actionResult(action, result);
            }
        } else if (action instanceof CardAction && ((CardAction) action).getCard() instanceof QuadERPPCs) {
            CardAction cardAction = (CardAction) action;
            cardAction.getSelectedCards().add(card);
            if (cardAction.getSelectedCards().size() == 2) {
                ActionResult result = new ActionResult();
                result.getSelectedCards().addAll(cardAction.getSelectedCards());
                getPlayer().actionResult(action, result);
            }
        } else {
            ActionResult result = new ActionResult();
            result.getSelectedCards().add(card);
            getPlayer().actionResult(action, result);
        }

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
            sendGameMessageToPlayer("show_action");
            sendGameMessageToOpponent("refresh_game_page");
        } else {
            sendGameMessageToAll("refresh_game_page");
        }
    }

    public void checkForAction() {
        if (getAction() != null) {
            sendGameMessageToPlayer("show_action");
        }
    }
}
