package org.smartreaction.battletechdomination.model.cards.abilities;

import org.smartreaction.battletechdomination.model.cards.Unit;
import org.smartreaction.battletechdomination.model.players.Player;

public class HeavyArmor extends Ability implements UnitDamagedAbility {
    //HEAVY ARMOR: When this unit is damaged, you may shuffle it into your deck.

    public HeavyArmor(Unit unit) {
        super(unit);
    }

    @Override
    public void useAbility(Player player) {
        player.makeYesNoAbilityChoice(unit, "HeavyArmor", "Shuffle " + unit.getName() + " into deck?");
    }
}
