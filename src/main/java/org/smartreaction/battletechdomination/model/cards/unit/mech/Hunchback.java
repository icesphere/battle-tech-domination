package org.smartreaction.battletechdomination.model.cards.unit.mech;

import org.smartreaction.battletechdomination.model.cards.MechUnit;
import org.smartreaction.battletechdomination.model.cards.abilities.AC20;
import org.smartreaction.battletechdomination.model.players.Player;

public class Hunchback extends MechUnit {
    AC20 ac20;

    public Hunchback() {
        name = "Hunchback";
        subName = "HBK-4G";
        cardText = "AC/20: You may reveal the top card of your deck at the start of your Combat phase. If it is a... Resource card, your opponent must damage a Mech; Support card, damage this unit; Unit card, no effect.";
        attack = 0;
        defense = 1;
        industryCost = 4;

        ac20 = new AC20(this);
    }

    @Override
    public boolean isAbilityAvailable(Player player) {
        return ac20.isAbilityAvailable(player);
    }

    @Override
    public void useUnitAbility(Player player) {
        ac20.useAbility(player);
    }
}
