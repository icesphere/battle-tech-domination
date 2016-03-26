package org.smartreaction.battletechdomination.model.cards.resource;

import org.smartreaction.battletechdomination.model.cards.BaseSupply;
import org.smartreaction.battletechdomination.model.players.Player;
import org.smartreaction.battletechdomination.model.cards.Resource;

public class BasicFactory extends Resource implements BaseSupply {
    public BasicFactory() {
        name = "Basic Factory";
        cardText = "+1 Industry";
    }

    @Override
    public void cardPlayed(Player player) {
        player.addIndustry(1);
    }
}
