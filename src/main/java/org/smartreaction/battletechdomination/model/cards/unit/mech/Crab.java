package org.smartreaction.battletechdomination.model.cards.unit.mech;

import org.smartreaction.battletechdomination.model.cards.MechUnit;
import org.smartreaction.battletechdomination.model.cards.abilities.unit.AdvancedCommSystem;

public class Crab extends MechUnit {
    public Crab() {
        name = "Crab";
        subName = "CRB-27";
        cardText = "ADVANCED COMM SYSTEM: At the start of your turn, +1 Card if your total Attack Value is greater than your opponent's total Attack Value.";
        attack = 1;
        defense = 1;
        industryCost = 4;

        addAbility(new AdvancedCommSystem(this));
    }
}
