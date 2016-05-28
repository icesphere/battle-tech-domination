package org.smartreaction.battletechdomination.model.cards.support;

import org.smartreaction.battletechdomination.model.cards.Support;
import org.smartreaction.battletechdomination.model.players.Player;

public class PrimusOfComstar extends Support {
    public PrimusOfComstar() {
        name = "Primus of Comstar";
        cardText = "+2 Actions. Scrap up to 2 Resource cards from your hand and/or discard pile. Draw a card for each card scrapped in this way.";
        industryCost = 7;
        losTechCost = 1;
    }

    @Override
    public void cardPlayed(Player player) {
        player.addActions(2);
        //todo
    }
}
