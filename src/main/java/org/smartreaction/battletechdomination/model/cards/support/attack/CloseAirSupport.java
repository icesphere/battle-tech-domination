package org.smartreaction.battletechdomination.model.cards.support.attack;

import org.smartreaction.battletechdomination.model.Player;
import org.smartreaction.battletechdomination.model.cards.SupportAttack;

public class CloseAirSupport extends SupportAttack {
    public CloseAirSupport() {
        name = "Close Air Support";
        cardText = "Discard a card. If you discarded a... Resource Card, you opponent must damage a unit; Unit Card, your opponent must damage a Mech; Support Card, your opponent must damage a unit and gain a Heavy Casualties card.";
        industryCost = 5;
    }

    @Override
    public void cardPlayed(Player player) {
        player.discardCards(1, false);
        //todo
    }
}
