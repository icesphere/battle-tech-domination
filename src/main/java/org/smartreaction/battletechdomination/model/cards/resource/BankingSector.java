package org.smartreaction.battletechdomination.model.cards.resource;

import org.smartreaction.battletechdomination.model.cards.Resource;
import org.smartreaction.battletechdomination.model.players.Player;

public class BankingSector extends Resource {
    public BankingSector() {
        name = "Banking Sector";
        cardText = "+4 Industry";
        industryCost = 5;
    }

    @Override
    public void cardPlayed(Player player) {
        player.addIndustry(4);
    }
}
