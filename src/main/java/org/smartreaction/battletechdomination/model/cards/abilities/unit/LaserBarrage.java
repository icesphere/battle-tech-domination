package org.smartreaction.battletechdomination.model.cards.abilities.unit;

import org.smartreaction.battletechdomination.model.TurnPhase;
import org.smartreaction.battletechdomination.model.cards.Card;
import org.smartreaction.battletechdomination.model.cards.Unit;
import org.smartreaction.battletechdomination.model.players.Player;

public class LaserBarrage extends UnitAbility {
    //LASER BARRAGE: At the start of your Combat phase, reveal the top card of your deck. If it is a... Resource card, X = 3; Unit card, X = 5; Otherwise, shuffle this unit into your deck.

    public LaserBarrage(Unit unit) {
        super(unit);
    }

    @Override
    public void useAbility(Player player) {
        Card card = player.revealTopCardOfDeck();
        if (card != null) {
            if (card.isResource()) {
                player.addGameLog(player.getPlayerName() + "'s " + unit.getName() + " has 3 Attack.");
                unit.setBonusAttack(3);
            } else if (card.isUnit()) {
                player.addGameLog(player.getPlayerName() + "'s " + unit.getName() + " has 5 Attack.");
                unit.setBonusAttack(5);
            } else {
                player.addGameLog(player.getPlayerName() + " shuffled " + unit.getName() + " back into their deck");
                player.getDeploymentZone().remove(unit);
                player.shuffleCardIntoDeck(unit);
            }
        }
    }

    @Override
    public boolean isAbilityAvailable(Player player) {
        return player.getTurnPhase() == TurnPhase.COMBAT_START;
    }
}
