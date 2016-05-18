package org.smartreaction.battletechdomination.model.cards.abilities;

import org.smartreaction.battletechdomination.model.cards.Card;
import org.smartreaction.battletechdomination.model.cards.CardType;
import org.smartreaction.battletechdomination.model.cards.actions.DamageUnit;
import org.smartreaction.battletechdomination.model.players.Player;

public class SwarmAttack extends Ability implements UnitDeployedAbility {
    //SWARM ATTACK: When you deploy this unit, your opponent must damage a Mech.

    public SwarmAttack(Card card) {
        super(card);
    }

    @Override
    public void useAbility(Player player) {
        player.addOpponentAction(new DamageUnit(CardType.UNIT_MECH, "Damage a Mech Unit"));
    }
}
