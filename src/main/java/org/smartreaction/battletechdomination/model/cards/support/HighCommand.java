package org.smartreaction.battletechdomination.model.cards.support;

import org.smartreaction.battletechdomination.model.players.Player;
import org.smartreaction.battletechdomination.model.cards.Card;
import org.smartreaction.battletechdomination.model.cards.Support;

import java.util.Collections;
import java.util.List;

public class HighCommand extends Support {
    public HighCommand() {
        name = "High Command";
        cardText = "Reveal the top 5 cards of your deck. Discard highest cost card (if multiple tied for highest cost discard one at random). Place the other cards into your hand.";
        industryCost = 5;
    }

    @Override
    public void cardPlayed(Player player) {
        List<Card> cards = player.revealTopCardsOfDeck(5);
        Collections.shuffle(cards);

        int highestValue = 0;

        Card cardWithHighestValue = null;

        for (Card card : cards) {
            int value = card.getIndustryCost();
            if (card.getLosTechCost() > 0) {
                value++;
            }
            if (value >= highestValue) {
                highestValue = value;
                cardWithHighestValue = card;
            }
        }

        if (cardWithHighestValue != null) {
            player.addGameLog("Discarded " + cardWithHighestValue.getName());
            player.addCardToDiscard(cardWithHighestValue);
            cards.remove(cardWithHighestValue);
        }

        for (Card card : cards) {
            player.addCardToHand(card);
        }
    }
}
