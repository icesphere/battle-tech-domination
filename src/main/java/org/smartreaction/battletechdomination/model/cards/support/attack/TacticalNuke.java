package org.smartreaction.battletechdomination.model.cards.support.attack;

import org.smartreaction.battletechdomination.model.players.Player;
import org.smartreaction.battletechdomination.model.cards.Card;
import org.smartreaction.battletechdomination.model.cards.SupportAttack;
import org.smartreaction.battletechdomination.model.cards.actions.DamageUnit;

public class TacticalNuke extends SupportAttack {
    public TacticalNuke() {
        name = "Tactical Nuke";
        cardText = "Reveal then discard the top card of your deck. Your opponent must damage X units, where X equals the Industry cost of the card divided by two (rounded up).";
        industryCost = 4;
    }

    @Override
    public void cardPlayed(Player player) {
        Card card = player.revealTopCardOfDeck();
        if (card != null) {
            player.discardTopCardOfDeck();
            int roundedValue = (int) Math.round(card.getIndustryCost() / 2.0);
            player.addGameLog(player.getOpponent().getPlayerName() + " must damage " + roundedValue + " units");
            for (int i = 0; i < roundedValue; i++) {
                 player.addOpponentAction(new DamageUnit());
            }
        }
    }
}
