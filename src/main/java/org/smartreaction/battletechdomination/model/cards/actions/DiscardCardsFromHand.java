package org.smartreaction.battletechdomination.model.cards.actions;

import org.smartreaction.battletechdomination.model.cards.Card;
import org.smartreaction.battletechdomination.model.players.Player;

import java.util.ArrayList;
import java.util.List;

public class DiscardCardsFromHand extends Action {
    private int numCardsToDiscard;

    private List<Card> selectedCards = new ArrayList<>(3);

    public DiscardCardsFromHand(int numCardsToDiscard, String text) {
        this.numCardsToDiscard = numCardsToDiscard;
        this.text = text;
    }

    public int getNumCardsToDiscard() {
        return numCardsToDiscard;
    }

    public List<Card> getSelectedCards() {
        return selectedCards;
    }

    @Override
    public boolean isCardActionable(Card card, String cardLocation, Player player) {
        return cardLocation.equals(Card.CARD_LOCATION_HAND) && !selectedCards.contains(card);
    }

    @Override
    public boolean processAction(Player player) {
        if (player.getHand().isEmpty()) {
            return false;
        } else {
            if (numCardsToDiscard > player.getHand().size()) {
                player.getHand().stream().forEach(player::addCardToDiscard);
                player.getHand().clear();
                player.addGameLog(player.getPlayerName() + " discarded " + player.getHand().size() + " cards");
                return false;
            } else {
                player.addGameLog(player.getPlayerName() + " is discarding " + numCardsToDiscard + " cards");
                return true;
            }
        }
    }

    @Override
    public void processActionResult(Player player, ActionResult result) {
        selectedCards.stream().forEach(player::discardCardFromHand);
    }

    @Override
    public boolean showActionDialog() {
        return selectedCards.size() == 0;
    }
}
