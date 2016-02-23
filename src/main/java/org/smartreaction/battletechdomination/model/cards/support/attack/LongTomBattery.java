package org.smartreaction.battletechdomination.model.cards.support.attack;

import org.smartreaction.battletechdomination.model.players.Player;
import org.smartreaction.battletechdomination.model.cards.*;
import org.smartreaction.battletechdomination.model.cards.actions.DamageUnit;

import java.util.List;

public class LongTomBattery extends SupportAttack {
    public LongTomBattery() {
        name = "Long Tom Battery";
        cardText = "Discard a card. If you discard a... Unit Card, you opponent must gain a Raided Supplies card; Resource or Support Card, your opponent must damage a Unit.";
        industryCost = 5;
    }

    @Override
    public void cardPlayed(Player player) {
        List<Card> cards = player.discardCardsFromHand(1, false);
        if (!cards.isEmpty()) {
            Card card = cards.get(0);
            if (card instanceof Unit) {
                player.getOpponent().gainRaidedSupplies();
            } else if (card instanceof Resource || card instanceof Support) {
                player.getOpponent().addOpponentAction(new DamageUnit());
            }
        }
    }
}
