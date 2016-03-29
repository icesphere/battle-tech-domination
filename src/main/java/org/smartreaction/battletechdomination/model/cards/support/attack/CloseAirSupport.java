package org.smartreaction.battletechdomination.model.cards.support.attack;

import org.smartreaction.battletechdomination.model.cards.SupportAttack;
import org.smartreaction.battletechdomination.model.cards.actions.CardAction;
import org.smartreaction.battletechdomination.model.players.Player;

public class CloseAirSupport extends SupportAttack {
    public CloseAirSupport() {
        name = "Close Air Support";
        cardText = "Discard a card. If you discarded a... Resource Card, your opponent must damage a unit; Unit Card, your opponent must damage a Mech; Support Card, your opponent must damage a unit and gain a Heavy Casualties card.";
        industryCost = 5;
    }

    @Override
    public void cardPlayed(Player player) {
        player.addAction(new CardAction(this));
    }
}
