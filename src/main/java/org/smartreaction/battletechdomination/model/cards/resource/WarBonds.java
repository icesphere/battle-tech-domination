package org.smartreaction.battletechdomination.model.cards.resource;

import org.smartreaction.battletechdomination.model.cards.BaseSupply;
import org.smartreaction.battletechdomination.model.cards.Resource;
import org.smartreaction.battletechdomination.model.players.Player;

public class WarBonds extends Resource implements BaseSupply {
    public WarBonds() {
        name = "War Bonds";
        cardText = "+2 Industry. Return this card to its Supply pile.";
        industryCost = 2;
    }

    @Override
    public void cardPlayed(Player player) {
        player.addIndustry(2);
        player.getCardsPlayed().remove(this);
    }
}
