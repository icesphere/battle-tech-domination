package org.smartreaction.battletechdomination.model.cards.resource;

import org.smartreaction.battletechdomination.model.cards.BaseSupply;
import org.smartreaction.battletechdomination.model.players.Player;
import org.smartreaction.battletechdomination.model.cards.Resource;

public class AdvancedFactory extends Resource implements BaseSupply {
    public AdvancedFactory() {
        name = "Advanced Factory";
        cardText = "+3 Industry and +1 Los Tech";
        industryCost = 6;
        imageFile = "advanced_factory.jpg";
    }

    @Override
    public void cardPlayed(Player player) {
        player.addIndustry(3);
        player.addLosTech(1);
    }
}
