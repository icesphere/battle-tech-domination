package org.smartreaction.battletechdomination.model.cards.abilities.unit;

import org.smartreaction.battletechdomination.model.cards.Unit;
import org.smartreaction.battletechdomination.model.players.Player;

public class QuickToAction extends UnitAbility implements UnitBuyAbility {
    //QUICK TO ACTION: When you buy this unit, place it on top of your deck instead of your discard pile.

    public QuickToAction(Unit unit) {
        super(unit);
    }

    @Override
    public void useAbility(Player player) {
        player.addCardToTopOfDeck(unit);
    }
}
