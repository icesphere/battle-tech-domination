package org.smartreaction.battletechdomination.model.cards.resource;

import org.smartreaction.battletechdomination.model.Player;
import org.smartreaction.battletechdomination.model.cards.Resource;

public class BasicFactory extends Resource {
    public BasicFactory() {
        name = "Basic Factory";
        cardText = "+1 Industry";
    }

    @Override
    public void cardPlayed(Player player) {
        player.addIndustry(1);
    }
}
