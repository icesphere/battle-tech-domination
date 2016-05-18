package org.smartreaction.battletechdomination.model.cards.abilities;

import org.smartreaction.battletechdomination.model.cards.Card;
import org.smartreaction.battletechdomination.model.players.Player;

public class ChampionMech extends Ability implements UnitDeployedAbility {
    //CHAMPION MECH: When you deploy this unit, +2 cards if your opponent has more units in his deployment zone than you have in yours.

    public ChampionMech(Card card) {
        super(card);
    }

    @Override
    public void useAbility(Player player) {
        if (player.getNumUnitsInDeploymentZone() < player.getOpponent().getNumUnitsInDeploymentZone()) {
            player.drawCards(2);
        }
    }
}
