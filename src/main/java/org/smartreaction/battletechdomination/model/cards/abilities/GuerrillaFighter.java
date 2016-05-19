package org.smartreaction.battletechdomination.model.cards.abilities;

import org.smartreaction.battletechdomination.model.cards.Unit;
import org.smartreaction.battletechdomination.model.players.Player;

public class GuerrillaFighter extends Ability implements OverrunOpponentAbility {
    //GUERRILLA FIGHTER: +1 Card when you Overrun your opponent.

    public GuerrillaFighter(Unit unit) {
        super(unit);
    }

    @Override
    public void useAbility(Player player) {
        player.addGameLog(player.getPlayerName() + " gets to draw a card due to Guerrilla Fighter ability on " + unit.getName());
        player.drawCards(1);
    }
}
