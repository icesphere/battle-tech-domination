package org.smartreaction.battletechdomination.model.cards.support.attack;

import org.smartreaction.battletechdomination.model.players.Player;
import org.smartreaction.battletechdomination.model.cards.Card;
import org.smartreaction.battletechdomination.model.cards.SupportAttack;

import java.util.List;

public class NightAssault extends SupportAttack {
    public NightAssault() {
        name = "Night Assault";
        cardText = "Your opponent reveals the top 3 cards of his deck. Choose two of them to discard. Put the other card on top of his deck.";
        industryCost = 5;
        imageFile = "NightAssault.png";
    }

    @Override
    public void cardPlayed(Player player) {
        List<Card> cards = player.getOpponent().revealTopCardsOfDeck(3);
        player.handleStrategicBombing(cards);
    }
}
