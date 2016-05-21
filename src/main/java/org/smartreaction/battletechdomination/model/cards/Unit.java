package org.smartreaction.battletechdomination.model.cards;

import org.smartreaction.battletechdomination.model.cards.abilities.unit.*;
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

    protected List<UnitAbility> abilities = new ArrayList<>(1);

    protected boolean abilityUsed;

    public void addAbility(UnitAbility ability) {
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

    public void unitDamaged(Player player) {
        abilities.stream().filter(a -> a instanceof UnitDamagedAbility).forEach(a -> a.useAbility(player));
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

    public boolean isActionableForCardAction(Card card, CardAction cardAction, String cardLocation, Player player) {
        return abilities.stream().anyMatch(a -> a instanceof CardActionAbility && ((CardActionAbility) a).isActionableForCardAction(card, cardAction, cardLocation, player));
    }

    public boolean hasBuyAbility() {
        return abilities.stream().anyMatch(a -> a instanceof UnitBuyAbility);
    }

    public void applyBuyAbility(Player player) {
        Optional<UnitAbility> buyAbility = abilities.stream().filter(a -> a instanceof UnitBuyAbility).findAny();
        if (buyAbility.isPresent()) {
            buyAbility.get().useAbility(player);
        }
    }

    public boolean processCardAction(Player player) {
        Optional<UnitAbility> ability = abilities.stream().filter(a -> a instanceof CardActionAbility).findFirst();
        return !ability.isPresent() || ((CardActionAbility) ability.get()).processCardAction(player);
    }

    public void processCardActionResult(CardAction cardAction, Player player, ActionResult result) {
        Optional<UnitAbility> ability = abilities.stream().filter(a -> a instanceof CardActionAbility).findFirst();
        if (ability.isPresent()) {
            ((CardActionAbility) ability.get()).processCardActionResult(cardAction, player, result);
        }
    }

    public void applyCombatPhaseBonuses(Player player) {
        abilities.stream().filter(a -> a instanceof CombatPhaseBonusAbility).forEach(a -> a.useAbility(player));
    }

    public void applyOverrunOpponentAbilities(Player player) {
        abilities.stream().filter(a -> a instanceof OverrunOpponentAbility).forEach(a -> a.useAbility(player));
    }

    public void applyOverrunByOpponentAbilities(Player player) {
        abilities.stream().filter(a -> a instanceof OverrunByOpponentAbility).forEach(a -> a.useAbility(player));
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
