package org.smartreaction.battletechdomination.model.cards.support;

import org.smartreaction.battletechdomination.model.Player;
import org.smartreaction.battletechdomination.model.cards.Card;
import org.smartreaction.battletechdomination.model.cards.Support;
import org.smartreaction.battletechdomination.model.cards.Unit;

import java.util.ArrayList;
import java.util.List;

public class Reinforcements extends Support {
    public Reinforcements() {
        name = "Reinforcements";
        cardText = "+1 Action. Reveal the top 4 cards of your deck. Put the revealed Unit cards into your hand. Put the other cards on top of your deck in any order.";
        industryCost = 3;
    }

    @Override
    public void cardPlayed(Player player) {
        player.addActions(1);
        List<Card> cards = player.revealTopCardsOfDeck(4);
        List<Card> nonUnitCards = new ArrayList<>();
        for (Card card : cards) {
            player.getDeck().remove(card);
            if (card instanceof Unit) {
                player.addGameLog("Adding Unit to hand: " + card.getName());
                player.addCardToHand(card);
            } else {
                nonUnitCards.add(card);
            }
        }
        player.putCardsOnTopOfDeckInAnyOrder(nonUnitCards);
    }
}
