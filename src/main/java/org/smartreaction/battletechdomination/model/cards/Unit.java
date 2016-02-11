package org.smartreaction.battletechdomination.model.cards;

import org.smartreaction.battletechdomination.model.Player;

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
            player.getOpponent().damageMech();
        }

        if (this instanceof AntiInfantry) {
            player.getOpponent().damageInfantry();
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
