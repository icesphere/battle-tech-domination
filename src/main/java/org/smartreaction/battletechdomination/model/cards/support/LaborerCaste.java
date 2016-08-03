package org.smartreaction.battletechdomination.model.cards.support;

import org.smartreaction.battletechdomination.model.players.Player;
import org.smartreaction.battletechdomination.model.cards.Support;

public class LaborerCaste extends Support {
    public LaborerCaste() {
        name = "Laborer Caste";
        cardText = "+3 Cards. +1 Action.";
        industryCost = 4;
        losTechCost = 1;
        imageFile = "LaborerCaste.png";
    }

    @Override
    public void cardPlayed(Player player) {
        player.drawCards(3);
        player.addActions(1);
    }
}
