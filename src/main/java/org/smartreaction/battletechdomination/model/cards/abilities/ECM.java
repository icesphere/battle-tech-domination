package org.smartreaction.battletechdomination.model.cards.abilities;

import org.smartreaction.battletechdomination.model.cards.Card;
import org.smartreaction.battletechdomination.model.cards.MechUnit;
import org.smartreaction.battletechdomination.model.players.Player;

import java.util.ArrayList;
import java.util.List;

public class ECM extends Ability {
    //ECM: Each other Mech in your deployment zone gets +1 Defense.

    public ECM(Card card) {
        super(card);
    }

    @Override
    public void useAbility(Player player) {
        if (!player.isYourTurn()) {
            List<Card> otherDeploymentZoneCards = new ArrayList<>(player.getDeploymentZone());
            otherDeploymentZoneCards.remove(card);
            for (Card otherDeploymentZoneCard : otherDeploymentZoneCards) {
                if (otherDeploymentZoneCard instanceof MechUnit) {
                    player.addGameLog(player.getPlayerName() + "'s " + otherDeploymentZoneCard.getName() + " gained +1 Defense from ECM ability on " + card.getName());
                    ((MechUnit) otherDeploymentZoneCard).setBonusDefense(((MechUnit) otherDeploymentZoneCard).getBonusDefense() + 1);
                }
            }
        }
    }
}
