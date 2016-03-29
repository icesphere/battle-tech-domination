package org.smartreaction.battletechdomination.model.cards.support.attack;

import org.smartreaction.battletechdomination.model.cards.SupportAttack;
import org.smartreaction.battletechdomination.model.cards.actions.CardAction;
import org.smartreaction.battletechdomination.model.players.Player;

public class LongTomBattery extends SupportAttack {
    public LongTomBattery() {
        name = "Long Tom Battery";
        cardText = "Discard a card. If you discard a... Unit Card, you opponent must gain a Raided Supplies card; Resource or Support Card, your opponent must damage a Unit.";
        industryCost = 5;
    }

    @Override
    public void cardPlayed(Player player) {
        player.addAction(new CardAction(this));
    }
}
