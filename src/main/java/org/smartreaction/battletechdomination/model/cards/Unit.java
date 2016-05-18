package org.smartreaction.battletechdomination.model.cards;

import org.smartreaction.battletechdomination.model.cards.abilities.Ability;
import org.smartreaction.battletechdomination.model.cards.abilities.CardActionAbility;
import org.smartreaction.battletechdomination.model.cards.abilities.CombatPhaseBonusAbility;
import org.smartreaction.battletechdomination.model.cards.abilities.UnitDeployedAbility;
import org.smartreaction.battletechdomination.model.cards.actions.ActionResult;
import org.smartreaction.battletechdomination.model.cards.actions.CardAction;
import org.smartreaction.battletechdomination.model.players.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class Unit extends Card {
    protected int attack;
    protected int defense;

    protected int bonusAttack;
    protected int bonusDefense;

    protected List<Ability> abilities = new ArrayList<>(1);

    protected boolean abilityUsed;

    public void addAbility(Ability ability) {
        abilities.add(ability);
    }

    public boolean isAbilityUsed() {
        return abilityUsed;
    }

    public void setAbilityUsed(boolean abilityUsed) {
        this.abilityUsed = abilityUsed;
    }

    public boolean isAbilityAvailable(Player player) {
        return abilities.stream().anyMatch(a -> a.isAbilityAvailable(player));
    }

    public void useUnitAbility(Player player) {
        abilities.stream().filter(a -> a.isAbilityAvailable(player)).forEach(a -> a.useAbility(player));
    }

    @Override
    public void cardPlayed(Player player) {
        unitDeployed(player);
    }

    public void unitDeployed(Player player) {
        abilities.stream().filter(a -> a instanceof UnitDeployedAbility).forEach(a -> a.useAbility(player));
    }

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

    public boolean isActionableForCardAction(CardAction cardAction, String cardLocation, Player player) {
        return abilities.stream().anyMatch(a -> a instanceof CardActionAbility && ((CardActionAbility) a).isActionableForCardAction(cardAction, cardLocation, player));
    }

    public boolean processCardAction(Player player) {
        Optional<Ability> ability = abilities.stream().filter(a -> a instanceof CardActionAbility).findFirst();
        return !ability.isPresent() || ((CardActionAbility) ability.get()).processCardAction(player);
    }

    public void processCardActionResult(CardAction cardAction, Player player, ActionResult result) {
        Optional<Ability> ability = abilities.stream().filter(a -> a instanceof CardActionAbility).findFirst();
        if (ability.isPresent()) {
            ((CardActionAbility) ability.get()).processCardActionResult(cardAction, player, result);
        }
    }

    public void applyCombatPhaseBonuses(Player player) {
        abilities.stream().filter(a -> a instanceof CombatPhaseBonusAbility).forEach(a -> a.useAbility(player));
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
