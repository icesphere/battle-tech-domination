package org.smartreaction.battletechdomination.model.cards.abilities;

import org.smartreaction.battletechdomination.model.cards.Card;
import org.smartreaction.battletechdomination.model.players.Player;

public class Heroic extends Ability {
    //HEROIC: If you have fewer Units in your deployment zone than your opponent has in his, +1 Attack and +2 Defense.

    public Heroic(Card card) {
        super(card);
    }

    @Override
    public void useAbility(Player player) {
        if (player.getNumUnitsInDeploymentZone() < player.getOpponent().getNumUnitsInDeploymentZone()) {
            player.addGameLog(player.getPlayerName() + " gained +1 Attack and +2 Defense from Heroic ability on " + card.getName());
            player.addAttack(1);
            player.addDefense(2);
        }
    }
}
