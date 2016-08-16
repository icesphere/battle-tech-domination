package org.smartreaction.battletechdomination.model.cards.actions;

import org.smartreaction.battletechdomination.model.cards.Card;
import org.smartreaction.battletechdomination.model.players.Player;

import java.util.ArrayList;
import java.util.List;

public class DiscardHandDownTo extends Action {
    private int cardsToDiscardDownTo;

    private List<Card> selectedCards = new ArrayList<>(2);

    public DiscardHandDownTo(int cardsToDiscardDownTo, String text) {
        this.cardsToDiscardDownTo = cardsToDiscardDownTo;
        this.text = text;
    }

    @Override
    public boolean isCardActionable(Card card, String cardLocation, Player player) {
        return cardLocation.equals(Card.CARD_LOCATION_HAND);
    }

    @Override
    public boolean processAction(Player player) {
        if (player.getHand().size() <= cardsToDiscardDownTo) {
            return false;
        } else {
            if (cardsToDiscardDownTo == player.getHand().size()) {
                player.getHand().forEach(player::addCardToDiscard);
                player.getHand().clear();
                player.addGameLog(player.getPlayerName() + " discarded " + player.getHand().size() + " cards");
                return false;
            } else {
                player.addGameLog(player.getPlayerName() + " is discarding down to " + cardsToDiscardDownTo + " cards");
                return true;
            }
        }
    }

    @Override
    public boolean processActionResult(Player player, ActionResult result) {
        if (result.isDoneWithAction()) {
            selectedCards.forEach(player::discardCardFromHand);
            return true;
        } else {
            Card selectedCard = result.getSelectedCard();
            if (selectedCards.contains(selectedCard)) {
                selectedCards.remove(selectedCard);
            } else {
                selectedCards.add(selectedCard);
            }
        }

        return false;
    }

    @Override
    public boolean isShowDone(Player player) {
        return selectedCards.size() > 0 && (player.getHand().size() - selectedCards.size()) == cardsToDiscardDownTo;
    }

    @Override
    public String getDoneText() {
        if (selectedCards.size() == 1) {
            return "Discard " + selectedCards.get(0).getName();
        } else {
            return "Discard " + selectedCards.size() + " cards";
        }
    }

    @Override
    public boolean isCardSelected(Card card) {
        return selectedCards.contains(card);
    }
}
