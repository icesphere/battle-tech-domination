package org.smartreaction.battletechdomination.model.cards.resource;

import org.smartreaction.battletechdomination.model.cards.BaseSupply;
import org.smartreaction.battletechdomination.model.players.Player;
import org.smartreaction.battletechdomination.model.cards.Resource;

public class BasicFactory extends Resource implements BaseSupply {
    public BasicFactory() {
        name = "Factory";
        cardText = "+1 Industry";
        imageFile = "basic_factory.jpg";
    }

    @Override
    public void cardPlayed(Player player) {
        player.addIndustry(1);
    }
}
