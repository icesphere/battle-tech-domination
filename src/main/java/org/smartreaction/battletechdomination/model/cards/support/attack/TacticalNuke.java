package org.smartreaction.battletechdomination.model.cards.support.attack;

import org.smartreaction.battletechdomination.model.Player;
import org.smartreaction.battletechdomination.model.cards.SupportAttack;

public class TacticalNuke extends SupportAttack {
    public TacticalNuke() {
        name = "Tactical Nuke";
        cardText = "Reveal then discard the top card of your deck. Your opponent must damage X units, where X equals the Industry cost of the card divided by two (rounded up).";
        industryCost = 4;
    }

    @Override
    public void cardPlayed(Player player) {
        //todo
    }
}
