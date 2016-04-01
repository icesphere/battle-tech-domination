package org.smartreaction.battletechdomination.model.cards;

import org.smartreaction.battletechdomination.model.Choice;
import org.smartreaction.battletechdomination.model.TurnPhase;
import org.smartreaction.battletechdomination.model.players.Player;
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

    protected boolean abilityUsed;

    public boolean isAbilityUsed() {
        return abilityUsed;
    }

    public void setAbilityUsed(boolean abilityUsed) {
        this.abilityUsed = abilityUsed;
    }

    public boolean isAbilityAvailable(Player player) {
        if (abilityUsed) {
            return false;
        }

        TurnPhase turnPhase = player.getTurnPhase();

        if (this instanceof AC20) {
            if (turnPhase == TurnPhase.ACTION) {
                return true;
            }
        }

        if (this instanceof DeathFromAbove) {
            if (turnPhase == TurnPhase.COMBAT_START) {
                return true;
            }
        }

        if (this instanceof Expendable) {
            if (turnPhase == TurnPhase.COMBAT_START) {
                return true;
            }
        }

        if (this instanceof HeavyFireSupport) {
            if (turnPhase == TurnPhase.ACTION && player.numUnitsInHand() > 0) {
                return true;
            }
        }

        if (this instanceof MobileFireSupport) {
            if (turnPhase == TurnPhase.COMBAT_START && player.getHandSize() > 0) {
                return true;
            }
        }

        if (this instanceof Overheat) {
            if (turnPhase == TurnPhase.COMBAT_START) {
                return true;
            }
        }

        if (this instanceof QuadERPPCs) {
            if (turnPhase == TurnPhase.COMBAT_START && player.getHandSize() >= 2) {
                return true;
            }
        }

        if (this instanceof Scout) {
            if (turnPhase == TurnPhase.COMBAT_START) {
                return true;
            }
        }

        return false;
    }

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
            player.addOpponentAction(new DamageUnit(CardType.UNIT_MECH, "Damage a Mech Unit"));
        }

        if (this instanceof AntiInfantry) {
            player.addOpponentAction(new DamageUnit(CardType.UNIT_INFANTRY, "Damage an Infantry Unit"));
        }

        if (this instanceof HighMaintenance) {
            player.discardCardFromHand();
        }

        if (this instanceof JumpJets) {
            player.addOpponentAction(new DamageUnitMinCost(6, "Damage a unit costing 6 Industry or more"));
        }

        if (this instanceof FastAssault) {
            player.addOpponentAction(new DamageUnitMaxCost(5, "Damage a unit costing 5 Industry or less"));
        }

        if (this instanceof ReconInForce) {
            if (player.getHandSize() >= 1) {
                player.makeYesNoAbilityChoice(this, "ReconInForce", "Discard a card to make opponent gain a Raided Supplies card?");
            }
        }

        if (this instanceof Versatile) {
            Choice choice1 = new Choice(1, "+1 Cards");
            Choice choice2 = new Choice(2, "+1 Actions");
            Choice choice3 = new Choice(3, "+1 Industry");

            player.makeAbilityChoice(this, "Versatile", "Choose one", choice1, choice2, choice3);
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
