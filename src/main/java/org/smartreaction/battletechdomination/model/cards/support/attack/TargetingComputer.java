package org.smartreaction.battletechdomination.model.cards.support.attack;

import org.smartreaction.battletechdomination.model.cards.SupportAttack;
import org.smartreaction.battletechdomination.model.cards.actions.DamageOpponentUnit;
import org.smartreaction.battletechdomination.model.players.Player;

public class TargetingComputer extends SupportAttack {
    public TargetingComputer() {
        name = "Targeting Computer";
        cardText = "Discard a card. Your opponent must damage a unit of your choice.";
        industryCost = 6;
        imageFile = "TargetingComputer.png";
    }

    @Override
    public void cardPlayed(Player player) {
        player.discardCardFromHand();
        player.addAction(new DamageOpponentUnit("Choose an opponent unit to damage"));
    }
}
