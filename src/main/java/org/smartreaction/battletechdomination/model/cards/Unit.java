package org.smartreaction.battletechdomination.model.cards;

import org.smartreaction.battletechdomination.model.Player;

public abstract class Unit extends Card {
    protected int attack;
    protected int defense;

    @Override
    public void cardPlayed(Player player) {

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
