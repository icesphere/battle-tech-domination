package org.smartreaction.battletechdomination.model.cards.abilities;

import org.smartreaction.battletechdomination.model.TurnPhase;
import org.smartreaction.battletechdomination.model.cards.Card;
import org.smartreaction.battletechdomination.model.cards.Unit;
import org.smartreaction.battletechdomination.model.cards.actions.ScrapOpponentUnitMaxCost;
import org.smartreaction.battletechdomination.model.players.Player;

public class DeathFromAbove extends Ability {
    //DEATH FROM ABOVE: You may damage this unit at the start of your Combat phase. If you do, choose an opposing unit costing 5 Industry or less for your opponent to scrap.

    public DeathFromAbove(Card card) {
        super(card);
    }

    @Override
    public void useAbility(Player player) {
        player.cardDamaged((Unit) card);
        player.addGameLog(player.getPlayerName() + " gets to damage an opponent's unit costing 5 Industry or less due to damaging " + card.getName() + " with Death From Above ability");
        player.addAction(new ScrapOpponentUnitMaxCost(5, "Damage an opponent's unit costing 5 Industry or less"));
    }

    @Override
    public boolean isAbilityAvailable(Player player) {
        return player.getTurnPhase() == TurnPhase.COMBAT_START;
    }
}
