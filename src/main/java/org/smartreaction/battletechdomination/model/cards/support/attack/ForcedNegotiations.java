package org.smartreaction.battletechdomination.model.cards.support.attack;

import org.smartreaction.battletechdomination.model.players.Player;
import org.smartreaction.battletechdomination.model.cards.SupportAttack;
import org.smartreaction.battletechdomination.model.cards.actions.UnitFromHandToTopOfDeck;

public class ForcedNegotiations extends SupportAttack {
    public ForcedNegotiations() {
        name = "Forced Negotiations";
        cardText = "Gain a Munitions Factory. Your opponent must place a Unit card from his hand on top of his draw deck or reveal a hand with no Unit cards.";
        industryCost = 4;
    }

    @Override
    public void cardPlayed(Player player) {
        player.gainMunitionsFactory();
        player.addOpponentAction(new UnitFromHandToTopOfDeck("Put a unit card from your hand on top of your deck"));
    }
}
