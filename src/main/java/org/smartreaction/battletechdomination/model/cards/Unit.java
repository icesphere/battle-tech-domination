package org.smartreaction.battletechdomination.model.cards;

import org.smartreaction.battletechdomination.model.Choice;
import org.smartreaction.battletechdomination.model.Player;
import org.smartreaction.battletechdomination.model.cards.abilities.*;
import org.smartreaction.battletechdomination.model.cards.actions.DamageUnit;

public abstract class Unit extends Card {
    protected int attack;
    protected int defense;

    @Override
    public void cardPlayed(Player player) {
        unitDeployed(player);
    }

    public void unitDeployed(Player player) {
        if (this instanceof Mobile) {
            player.addActions(1);
        }

        if (this instanceof SwarmAttack) {
            player.addOpponentAction(new DamageUnit(CardType.UNIT_MECH));
        }

        if (this instanceof AntiInfantry) {
            player.addOpponentAction(new DamageUnit(CardType.UNIT_INFANTRY));
        }

        if (this instanceof HighMaintenance) {
            player.discardCardFromHand();
        }

        if (this instanceof Versatile) {
            Choice choice1 = new Choice(1, "+1 Cards");
            Choice choice2 = new Choice(2, "+1 Actions");
            Choice choice3 = new Choice(3, "+1 Industry");

            player.makeChoice(this, choice1, choice2, choice3);
        }
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }
}
