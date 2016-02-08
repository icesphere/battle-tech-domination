package org.smartreaction.battletechdomination.model.cards.support.attack;

import org.smartreaction.battletechdomination.model.Player;
import org.smartreaction.battletechdomination.model.cards.SupportAttack;

public class ArrowIVBattery extends SupportAttack {
    public ArrowIVBattery() {
        name = "Arrow IV Battery";
        cardText = "Discard a card. Your opponent must damage a unit of your choice.";
        industryCost = 6;
    }

    @Override
    public void cardPlayed(Player player) {
        player.discardCards(1, false);
        //todo
    }
}
