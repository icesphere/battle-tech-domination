package org.smartreaction.battletechdomination.model.cards.support;

import org.smartreaction.battletechdomination.model.players.Player;
import org.smartreaction.battletechdomination.model.cards.Support;

public class CombinedArms extends Support {
    public CombinedArms() {
        name = "Combined Arms";
        cardText = "+1 Card. +2 Actions. Gain an Infantry Platoon and put it in your hand.";
        industryCost = 3;
        imageFile = "CombinedArms.png";
    }

    @Override
    public void cardPlayed(Player player) {
        player.drawCards(1);
        player.addActions(2);
        player.gainInfantryPlatoonIntoHand();
    }
}
