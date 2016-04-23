package org.smartreaction.battletechdomination.model.cards.resource;

import org.smartreaction.battletechdomination.model.cards.Resource;
import org.smartreaction.battletechdomination.model.players.Player;

public class StripMining extends Resource {
    public StripMining() {
        name = "Strip Mining";
        cardText = "You may scrap a Resource card from your hand.";
        industryCost = 3;
    }

    @Override
    public void cardPlayed(Player player) {
        player.makeYesNoAbilityChoice(this, "StripMining", "Do you want to scrap a Resource card from your hand?");
    }
}
