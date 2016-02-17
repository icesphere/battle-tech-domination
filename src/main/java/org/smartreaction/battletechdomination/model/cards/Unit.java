package org.smartreaction.battletechdomination.model.cards;

import org.smartreaction.battletechdomination.model.Choice;
import org.smartreaction.battletechdomination.model.Player;
import org.smartreaction.battletechdomination.model.cards.abilities.*;
import org.smartreaction.battletechdomination.model.cards.actions.DamageUnit;
import org.smartreaction.battletechdomination.model.cards.actions.DamageUnitMaxCost;
import org.smartreaction.battletechdomination.model.cards.actions.DamageUnitMinCost;
import org.smartreaction.battletechdomination.model.cards.unit.infantry.InfantryPlatoon;

import java.util.ArrayList;
import java.util.List;

public abstract class Unit extends Card {
    protected int attack;
    protected int defense;

    protected int bonusAttack;
    protected int bonusDefense;

    @Override
    public void cardPlayed(Player player) {
        unitDeployed(player);
    }

    public void unitDeployed(Player player) {
        if (this instanceof Mobile) {
            player.addActions(1);
        }

        if (this instanceof Flamers) {
            List<Card> opponentDeploymentZoneCards = new ArrayList<>(player.getOpponent().getDeploymentZone());
            opponentDeploymentZoneCards.stream().filter(card -> card instanceof InfantryPlatoon).forEach(card -> player.getOpponent().cardDamaged(card));
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

        if (this instanceof JumpJets) {
            player.addOpponentAction(new DamageUnitMinCost(6));
        }

        if (this instanceof Versatile) {
            Choice choice1 = new Choice(1, "+1 Cards");
            Choice choice2 = new Choice(2, "+1 Actions");
            Choice choice3 = new Choice(3, "+1 Industry");

            player.makeChoice(this, choice1, choice2, choice3);
        }

        if (this instanceof FastAssault) {
            player.addOpponentAction(new DamageUnitMaxCost(5));
        }
    }

    public boolean isDeployable(Player player) {
        if (this instanceof ChampionMech) {
            return player.getNumMechUnitsInDeploymentZone() < player.getOpponent().getNumMechUnitsInDeploymentZone();
        }

        return true;
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
