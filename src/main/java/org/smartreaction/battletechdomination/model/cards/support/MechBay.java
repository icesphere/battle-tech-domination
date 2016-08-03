package org.smartreaction.battletechdomination.model.cards.support;

import org.smartreaction.battletechdomination.model.players.Player;
import org.smartreaction.battletechdomination.model.cards.Support;

public class MechBay extends Support {
    public MechBay() {
        name = "Mech Bay";
        cardText = "+3 Cards";
        industryCost = 3;
        imageFile = "MechBay.png";
    }

    @Override
    public void cardPlayed(Player player) {
        player.drawCards(3);
    }
}
