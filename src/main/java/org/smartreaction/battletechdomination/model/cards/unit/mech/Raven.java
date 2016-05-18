package org.smartreaction.battletechdomination.model.cards.unit.mech;

import org.smartreaction.battletechdomination.model.cards.MechUnit;
import org.smartreaction.battletechdomination.model.cards.abilities.ActiveProbe;
import org.smartreaction.battletechdomination.model.cards.abilities.ECM;
import org.smartreaction.battletechdomination.model.players.Player;

public class Raven extends MechUnit implements ActiveProbe {
    ECM ecm;

    public Raven() {
        name = "Raven";
        subName = "RVN-3L";
        cardText = "ECM: Each other Mech in your deployment zone gets +1 Defense. ACTIVE PROBE: +1 Card when your opponent deploys a Mech or Vehicle unit.";
        attack = 0;
        defense = 0;
        industryCost = 3;

        ecm = new ECM(this);
    }

    @Override
    public void applyCombatPhaseBonuses(Player player) {
        ecm.useAbility(player);
    }
}
