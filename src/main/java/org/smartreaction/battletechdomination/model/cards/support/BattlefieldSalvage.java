package org.smartreaction.battletechdomination.model.cards.support;

import org.smartreaction.battletechdomination.model.cards.Support;
import org.smartreaction.battletechdomination.model.cards.actions.CardAction;
import org.smartreaction.battletechdomination.model.players.Player;

public class BattlefieldSalvage extends Support {
    public BattlefieldSalvage() {
        name = "Battlefield Salvage";
        cardText = "+2 Industry. You may discard a Unit card from your hand or deployment zone. Gain an additional +X Industry, where X is the Industry cost of the card you discarded.";
        industryCost = 3;
    }

    @Override
    public void cardPlayed(Player player) {
        player.addIndustry(2);
        player.addAction(new CardAction(this, "Discard a Unit card from your hand or deployment zone. Gain an additional +X Industry, where X is the Industry cost of the card you discarded."));
    }
}
