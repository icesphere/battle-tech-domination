package org.smartreaction.battletechdomination.model.cards.unit.mech;

import org.smartreaction.battletechdomination.model.cards.MechUnit;
import org.smartreaction.battletechdomination.model.cards.abilities.ActiveProbe;
import org.smartreaction.battletechdomination.model.cards.abilities.ECM;
import org.smartreaction.battletechdomination.model.players.Player;

public class Loki extends MechUnit implements ActiveProbe {
    ECM ecm;

    public Loki() {
        name = "Loki";
        subName = "HELLBRINGER";
        cardText = "ECM: Each other Mech in your deployment zone gets +1 Defense. ACTIVE PROBE: +1 Card when your opponent deploys a Mech or Vehicle unit.";
        attack = 3;
        defense = 2;
        industryCost = 6;
        losTechCost = 1;

        ecm = new ECM(this);
    }

    @Override
    public void applyCombatPhaseBonuses(Player player) {
        ecm.useAbility(player);
    }
}
