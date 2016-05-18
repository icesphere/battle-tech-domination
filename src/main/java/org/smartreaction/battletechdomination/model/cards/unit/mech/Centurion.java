package org.smartreaction.battletechdomination.model.cards.unit.mech;

import org.smartreaction.battletechdomination.model.cards.MechUnit;
import org.smartreaction.battletechdomination.model.cards.abilities.AC10;
import org.smartreaction.battletechdomination.model.players.Player;

public class Centurion extends MechUnit {
    AC10 ac10;

    public Centurion() {
        name = "Centurion";
        subName = "CN9-D";
        cardText = "AC/10: +1 Attack if your opponent has a Mech or Vehicle in his deployment zone.";
        attack = 1;
        defense = 1;
        industryCost = 3;

        ac10 = new AC10(this);
    }

    @Override
    public void applyCombatPhaseBonuses(Player player) {
        ac10.useAbility(player);
    }
}
