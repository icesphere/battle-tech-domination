package org.smartreaction.battletechdomination.model.cards.resource;

import org.smartreaction.battletechdomination.model.cards.BaseSupply;
import org.smartreaction.battletechdomination.model.players.Player;
import org.smartreaction.battletechdomination.model.cards.Resource;

public class Factory extends Resource implements BaseSupply {
    public Factory() {
        name = "Factory";
        cardText = "+1 Industry";
        imageFile = "Factory.png";
    }

    @Override
    public void cardPlayed(Player player) {
        player.addIndustry(1);
    }
}
