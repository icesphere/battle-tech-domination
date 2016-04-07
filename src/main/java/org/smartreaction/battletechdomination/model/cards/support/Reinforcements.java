package org.smartreaction.battletechdomination.model.cards.support;

import org.smartreaction.battletechdomination.model.cards.Card;
import org.smartreaction.battletechdomination.model.cards.Support;
import org.smartreaction.battletechdomination.model.cards.Unit;
import org.smartreaction.battletechdomination.model.players.Player;

import java.util.List;

public class Reinforcements extends Support {
    public Reinforcements() {
        name = "Reinforcements";
        cardText = "+1 Action. Reveal the top 4 cards of your deck. The revealed Unit cards go into your hand. The other cards go back on top of your deck in the same order.";
        industryCost = 3;
    }

    @Override
    public void cardPlayed(Player player) {
        player.addActions(1);
        List<Card> cards = player.revealTopCardsOfDeck(4);
        for (Card card : cards) {
            if (card instanceof Unit) {
                player.removeCardFromDeck(card);
                player.addGameLog("Adding Unit to hand: " + card.getName());
                player.addCardToHand(card);
            }
        }
    }
}
