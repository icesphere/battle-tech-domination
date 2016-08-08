package org.smartreaction.battletechdomination.model.cards.actions;

import org.smartreaction.battletechdomination.model.cards.Card;
import org.smartreaction.battletechdomination.model.players.Player;

import java.util.ArrayList;
import java.util.List;

public class ScrapCardsFromHandOrDiscardPile extends Action implements SelectFromDiscardAction {
    protected List<Card> selectedCardsFromHand = new ArrayList<>(3);
    protected List<Card> selectedCardsFromDiscard = new ArrayList<>(3);

    protected int numCardsToScrap;

    protected boolean optional;

    public ScrapCardsFromHandOrDiscardPile(int numCardsToScrap) {
        this.numCardsToScrap = numCardsToScrap;
        text = "Scrap " + numCardsToScrap + " card";
        if (numCardsToScrap != 1) {
            text += "s";
        }
    }

    public ScrapCardsFromHandOrDiscardPile(int numCardsToScrap, String text) {
        this.numCardsToScrap = numCardsToScrap;
        this.text = text;
    }

    public ScrapCardsFromHandOrDiscardPile(int numCardsToScrap, String text, boolean optional) {
        this.numCardsToScrap = numCardsToScrap;
        this.text = text;
        this.optional = optional;
    }

    @Override
    public boolean isCardActionable(Card card, String cardLocation, Player player) {
        return cardLocation.equals(Card.CARD_LOCATION_HAND) || cardLocation.equals(Card.CARD_LOCATION_DISCARD);
    }

    @Override
    public boolean processAction(Player player) {
        return !(player.getHand().isEmpty() && player.getDiscard().isEmpty());
    }

    @Override
    public boolean processActionResult(Player player, ActionResult result) {
        if (result.isDoneWithAction()) {
            selectedCardsFromHand.forEach(player::scrapCardFromHand);
            selectedCardsFromDiscard.forEach(player::scrapCardFromDiscard);
            return true;
        } else {
            Card selectedCard = result.getSelectedCard();

            if (result.getCardLocation().equals(Card.CARD_LOCATION_DISCARD)) {
                if (selectedCardsFromDiscard.contains(selectedCard)) {
                    selectedCardsFromDiscard.remove(selectedCard);
                } else {
                    selectedCardsFromDiscard.add(selectedCard);
                }
            } else if (result.getCardLocation().equals(Card.CARD_LOCATION_HAND)) {
                if (selectedCardsFromHand.contains(selectedCard)) {
                    selectedCardsFromHand.remove(selectedCard);
                } else {
                    selectedCardsFromHand.add(selectedCard);
                }
            }
        }

        return false;
    }

    @Override
    public boolean isShowDoNotUse() {
        return optional;
    }

    @Override
    public boolean isShowDone(Player player) {
        int totalSelectedCards = getTotalSelectedCards();
        //todo handle if not optional and total available cards to scrap is less than numCardsToScrap
        return totalSelectedCards > 0 && totalSelectedCards <= numCardsToScrap && (optional || totalSelectedCards == numCardsToScrap);
    }

    protected int getTotalSelectedCards() {
        return selectedCardsFromDiscard.size() + selectedCardsFromHand.size();
    }

    protected List<Card> getAllSelectedCards() {
        List<Card> cards = new ArrayList<>();
        cards.addAll(selectedCardsFromDiscard);
        cards.addAll(selectedCardsFromHand);
        return cards;
    }

    @Override
    public String getDoneText() {
        if (getTotalSelectedCards() == 1) {
            return "Scrap " + getAllSelectedCards().get(0).getName();
        } else {
            return "Scrap " + getTotalSelectedCards() + " cards";
        }
    }

    @Override
    public boolean isCardSelected(Card card) {
        return selectedCardsFromHand.contains(card) || selectedCardsFromDiscard.contains(card);
    }
}
