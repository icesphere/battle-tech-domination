package org.smartreaction.battletechdomination.model.cards.support;

import org.smartreaction.battletechdomination.model.players.Player;
import org.smartreaction.battletechdomination.model.cards.Support;

public class Propaganda extends Support {
    public Propaganda() {
        name = "Propaganda";
        cardText = "+1 Card. +1 Action. Gain an Infantry Platoon and put it in your hand.";
        industryCost = 3;
    }

    @Override
    public void cardPlayed(Player player) {
        player.drawCards(1);
        player.addActions(1);
        player.gainInfantryPlatoonIntoHand();
    }
}
