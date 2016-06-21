package org.smartreaction.battletechdomination.model.cards.resource;

import org.smartreaction.battletechdomination.model.cards.BaseSupply;
import org.smartreaction.battletechdomination.model.cards.Resource;
import org.smartreaction.battletechdomination.model.players.Player;

public class SupplyDrop extends Resource implements BaseSupply {
    public SupplyDrop() {
        name = "Supply Drop";
        cardText = "+2 Industry. Return this card to its Supply pile.";
        industryCost = 2;
        imageFile = "SupplyDrop.png";
    }

    @Override
    public void cardPlayed(Player player) {
        player.addIndustry(2);
    }
}
