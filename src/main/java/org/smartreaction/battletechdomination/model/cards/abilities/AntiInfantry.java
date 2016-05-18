package org.smartreaction.battletechdomination.model.cards.abilities;

import org.smartreaction.battletechdomination.model.cards.Card;
import org.smartreaction.battletechdomination.model.cards.CardType;
import org.smartreaction.battletechdomination.model.cards.actions.DamageUnit;
import org.smartreaction.battletechdomination.model.players.Player;

public class AntiInfantry extends Ability {
    //ANTI-INFANTRY: When you deploy this unit, your opponent must damage an Infantry unit.

    public AntiInfantry(Card card) {
        super(card);
    }

    @Override
    public void useAbility(Player player) {
        player.addOpponentAction(new DamageUnit(CardType.UNIT_INFANTRY, "Damage an Infantry Unit"));
    }
}
