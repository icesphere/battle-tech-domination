package org.smartreaction.battletechdomination.model.cards.abilities.unit;

import org.smartreaction.battletechdomination.model.cards.Unit;
import org.smartreaction.battletechdomination.model.players.Player;

public class HeavyArmor extends UnitAbility implements UnitDamagedAbility {
    //HEAVY ARMOR: When this unit is damaged, shuffle it into your deck.

    public HeavyArmor(Unit unit) {
        super(unit);
    }

    @Override
    public void useAbility(Player player) {
        player.shuffleCardIntoDeck(unit);
    }
}
