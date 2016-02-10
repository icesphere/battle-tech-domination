package org.smartreaction.battletechdomination.model.cards.support.attack;

import org.smartreaction.battletechdomination.model.Player;
import org.smartreaction.battletechdomination.model.cards.SupportAttack;

public class GuerrillaWarfare extends SupportAttack {
    public GuerrillaWarfare() {
        name = "Guerrilla Warfare";
        cardText = "+1 Action. Reveal the top card of your deck. If the Industry cost of the card is equal to or less than the number of units in your opponent's deployment zone, your opponent must damage a unit.";
        industryCost = 3;
    }

    @Override
    public void cardPlayed(Player player) {
        player.addActions(1);
        //todo
    }
}
