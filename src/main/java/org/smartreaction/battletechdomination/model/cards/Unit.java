package org.smartreaction.battletechdomination.model.cards;

import org.smartreaction.battletechdomination.model.players.Player;

public abstract class Unit extends Card {
    protected int attack;
    protected int defense;

    protected int bonusAttack;
    protected int bonusDefense;

    protected boolean abilityUsed;

    public boolean isAbilityUsed() {
        return abilityUsed;
    }

    public void setAbilityUsed(boolean abilityUsed) {
        this.abilityUsed = abilityUsed;
    }

    public boolean isAbilityAvailable(Player player) {
        return false;
    }

    public void useUnitAbility(Player player) {}

    @Override
    public void cardPlayed(Player player) {
        unitDeployed(player);
    }

    public void unitDeployed(Player player) {}

    public boolean isDeployable(Player player) {
        return true;
    }

    @Override
    public boolean isActionable(Player player, String cardLocation) {
        if (!player.isYourTurn()) {
            return false;
        }

        if (cardLocation.equals(Card.CARD_LOCATION_HAND)) {
            return player.isActionPhase() && player.getActions() > 0;
        } else if (cardLocation.equals(Card.CARD_LOCATION_PLAYER_UNITS)) {
            return !abilityUsed && isAbilityAvailable(player);
        }

        return false;
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

    public int getBonusAttack() {
        return bonusAttack;
    }

    public void setBonusAttack(int bonusAttack) {
        this.bonusAttack = bonusAttack;
    }

    public int getBonusDefense() {
        return bonusDefense;
    }

    public void setBonusDefense(int bonusDefense) {
        this.bonusDefense = bonusDefense;
    }
}
