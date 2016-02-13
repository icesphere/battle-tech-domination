package org.smartreaction.battletechdomination.model.cards.support.attack;

import org.smartreaction.battletechdomination.model.Player;
import org.smartreaction.battletechdomination.model.cards.Card;
import org.smartreaction.battletechdomination.model.cards.SupportAttack;
import org.smartreaction.battletechdomination.model.cards.actions.DamageUnit;

public class GuerrillaWarfare extends SupportAttack {
    public GuerrillaWarfare() {
        name = "Guerrilla Warfare";
        cardText = "+1 Action. Reveal the top card of your deck. If the Industry cost of the card is equal to or less than the number of units in your opponent's deployment zone, your opponent must damage a unit.";
        industryCost = 3;
    }

    @Override
    public void cardPlayed(Player player) {
        player.addActions(1);
        Card topCard = player.revealTopCardOfDeck();
        if (topCard != null) {
            if (topCard.getIndustryCost() <= player.getOpponent().getNumUnitsInDeploymentZone()) {
                player.addOpponentAction(new DamageUnit());
            }
        }
    }
}
