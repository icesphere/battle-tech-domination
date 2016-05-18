package org.smartreaction.battletechdomination.model.cards.abilities;

import org.smartreaction.battletechdomination.model.cards.Card;
import org.smartreaction.battletechdomination.model.cards.unit.infantry.InfantryPlatoon;
import org.smartreaction.battletechdomination.model.players.Player;

import java.util.ArrayList;
import java.util.List;

public class Inspiring extends Ability {
    //INSPIRING: +1 Attack per Infantry Platoon in your deployment zone.

    public Inspiring(Card card) {
        super(card);
    }

    @Override
    public void useAbility(Player player) {
        if (player.isYourTurn()) {
            List<Card> deploymentZoneCopy = new ArrayList<>(player.getDeploymentZone());
            for (Card deploymentZoneCard : deploymentZoneCopy) {
                player.addGameLog(player.getPlayerName() + "'s Infantry units gain +1 Attack from Inspiring ability on " + card.getName());
                if (deploymentZoneCard instanceof InfantryPlatoon) {
                    ((InfantryPlatoon) deploymentZoneCard).setBonusAttack(((InfantryPlatoon) deploymentZoneCard).getBonusAttack() + 1);
                }
            }
        }
    }
}
