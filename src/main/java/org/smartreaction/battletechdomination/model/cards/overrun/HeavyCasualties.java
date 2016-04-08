package org.smartreaction.battletechdomination.model.cards.overrun;

import org.smartreaction.battletechdomination.model.cards.OverrunSupport;
import org.smartreaction.battletechdomination.model.cards.actions.CardAction;
import org.smartreaction.battletechdomination.model.players.Player;

public class HeavyCasualties extends OverrunSupport {
    public HeavyCasualties() {
        name = "Heavy Casualties";
        cardText = "You may discard an Infantry Platoon from your hand or deployment zone.  If you do, return this card to the Overrun pile.";
        overrunAmount = 1;
    }

    @Override
    public void cardPlayed(Player player) {
        player.addAction(new CardAction(this, "Discard an Infantry Platoon from Hand or Deployment Zone"));
    }
}
