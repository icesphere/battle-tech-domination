package org.smartreaction.battletechdomination.model.cards.abilities;

import org.smartreaction.battletechdomination.model.cards.Card;
import org.smartreaction.battletechdomination.model.cards.MechUnit;
import org.smartreaction.battletechdomination.model.players.Player;

import java.util.ArrayList;
import java.util.List;

public class LRMFireSupport extends Ability implements CombatPhaseBonusAbility {
    //LRM FIRE SUPPORT: Each Mech in your deployment zone gets +1 Attack.

    public LRMFireSupport(Card card) {
        super(card);
    }

    @Override
    public void useAbility(Player player) {
        if (player.isYourTurn()) {
            List<Card> otherDeploymentZoneCards = new ArrayList<>(player.getDeploymentZone());
            otherDeploymentZoneCards.remove(card);
            for (Card deploymentZoneCard : otherDeploymentZoneCards) {
                if (deploymentZoneCard instanceof MechUnit) {
                    player.addGameLog(player.getPlayerName() + "'s " + deploymentZoneCard.getName() + " gained +1 Attack from LRMFireSupport ability on " + card.getName());
                    ((MechUnit) deploymentZoneCard).setBonusAttack(((MechUnit) deploymentZoneCard).getBonusAttack() + 1);
                }
            }
        }
    }
}
