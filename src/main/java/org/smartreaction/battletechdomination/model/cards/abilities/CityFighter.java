package org.smartreaction.battletechdomination.model.cards.abilities;

import org.smartreaction.battletechdomination.model.cards.Card;
import org.smartreaction.battletechdomination.model.players.Player;

public class CityFighter extends Ability {
    //CITY FIGHTER: +1 Attack if your opponent has two or more Infantry units in his deployment zone.

    public CityFighter(Card card) {
        super(card);
    }

    @Override
    public void useAbility(Player player) {
        if (player.isYourTurn() && player.getOpponent().getNumInfantryUnitsInDeploymentZone() >= 2) {
            player.addGameLog(player.getPlayerName() + " gained +3 Attack from City Fighter ability on " + card.getName());
            player.addAttack(3);
        }
    }
}
