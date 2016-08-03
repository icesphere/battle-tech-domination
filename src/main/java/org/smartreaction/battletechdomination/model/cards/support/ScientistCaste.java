package org.smartreaction.battletechdomination.model.cards.support;

import org.smartreaction.battletechdomination.model.players.Player;
import org.smartreaction.battletechdomination.model.cards.Support;

public class ScientistCaste extends Support {
    public ScientistCaste() {
        name = "Scientist Caste";
        cardText = "+3 Cards. You may ignore the Los Tech cost when buying cards this turn.";
        industryCost = 5;
        losTechCost = 1;
        imageFile = "ScientistCaste.png";
    }

    @Override
    public void cardPlayed(Player player) {
        player.drawCards(3);
        player.ignoreLosTechCost();
    }
}
