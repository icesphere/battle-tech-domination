package org.smartreaction.battletechdomination.model.cards.overrun;

import org.smartreaction.battletechdomination.model.cards.OverrunSupport;
import org.smartreaction.battletechdomination.model.cards.actions.CardAction;
import org.smartreaction.battletechdomination.model.players.Player;

public class CriticalHit extends OverrunSupport {
    public CriticalHit() {
        name = "Critical Hit";
        cardText = "You may scrap a Mech from your hand or deployment zone.  If you do, return this card to the Overrun pile.";
        overrunAmount = 3;
    }

    @Override
    public void cardPlayed(Player player) {
        player.addAction(new CardAction(this, "Scrap a Mech from your hand or deployment zone to return Critical Hit to Overrun pile"));
    }
}
