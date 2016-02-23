package org.smartreaction.battletechdomination.model.players;

import org.smartreaction.battletechdomination.model.Choice;
import org.smartreaction.battletechdomination.model.User;
import org.smartreaction.battletechdomination.model.cards.Card;
import org.smartreaction.battletechdomination.model.cards.CardType;
import org.smartreaction.battletechdomination.model.cards.Unit;

import java.util.List;

public class HumanPlayer extends Player {
    private User user;

    public HumanPlayer(User user) {
        this.user = user;
        playerName = user.getUsername();
    }

    @Override
    public Card getCardFromHandToDiscardForPlusOneAttack() {
        return null;
    }

    @Override
    public Unit getUnitFromHandToDiscardToDamageOpponentUnit() {
        return null;
    }

    @Override
    public List<Card> getCardsToDiscardFromHand(int cards, boolean optional) {
        return null;
    }

    @Override
    public List<Card> getCardsToDiscardFromHandOrDeploymentZone(int cards, boolean optional) {
        return null;
    }

    @Override
    public List<Card> getCardsFromHandToAddToTopOfDeck(int cards) {
        return null;
    }

    @Override
    public void makeChoice(Card card, Choice... choices) {

    }

    @Override
    public void makeAbilityChoice(Card card, String abilityName, Choice... choices) {

    }

    @Override
    public void makeYesNoAbilityChoice(Card card, String abilityName, String text) {

    }

    @Override
    public List<Card> chooseCardsToDiscardForStrategicBombing(List<Card> cards) {
        return null;
    }

    @Override
    public List<Card> chooseOrderToPutCardsOnTopOfDeck(List<Card> cards) {
        return null;
    }

    @Override
    public Card chooseCardFromDeploymentZoneToAddToHand() {
        return null;
    }

    @Override
    public List<List<Card>> getCardsToOptionallyScrapFromDiscardOrHand(int cards, CardType cardType) {
        return null;
    }

    @Override
    public Card getCardToScrapFromHand(CardType cardType, boolean optional) {
        return null;
    }

    @Override
    public Card chooseFreeCardFromSupplyToPutOnTopOfDeck(Integer maxIndustryCost) {
        return null;
    }

    @Override
    public Card chooseFreeResourceCardToPutIntoHand(int maxIndustryCost) {
        return null;
    }

    @Override
    public void takeTurn() {

    }

    @Override
    public Unit chooseUnitToDamage(CardType type) {
        return null;
    }

    @Override
    public Unit chooseUnitToDamageMaxCost(int maxCost) {
        return null;
    }

    @Override
    public Unit chooseUnitToDamageMinCost(int minCost) {
        return null;
    }

    @Override
    public Unit chooseUnitToScrapMaxCost(int maxCost) {
        return null;
    }

    @Override
    public Unit chooseUnitFromHandToPutOnTopOfDeck() {
        return null;
    }

    @Override
    public Unit chooseOpponentUnitToDamage() {
        return null;
    }

    @Override
    public Card chooseCardToSetAside() {
        return null;
    }

    public User getUser() {
        return user;
    }
}
