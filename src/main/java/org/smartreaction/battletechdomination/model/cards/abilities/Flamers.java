package org.smartreaction.battletechdomination.model.cards.abilities;

import org.smartreaction.battletechdomination.model.cards.Card;
import org.smartreaction.battletechdomination.model.cards.Unit;
import org.smartreaction.battletechdomination.model.cards.unit.infantry.InfantryPlatoon;
import org.smartreaction.battletechdomination.model.players.Player;

import java.util.ArrayList;
import java.util.List;

public class Flamers extends Ability {
    //FLAMERS: When you deploy this unit, your opponent must damage all Infantry Platoons in his deployment zone.

    public Flamers(Card card) {
        super(card);
    }

    @Override
    public void useAbility(Player player) {
        List<Card> opponentDeploymentZoneCards = new ArrayList<>(player.getOpponent().getDeploymentZone());
        opponentDeploymentZoneCards.stream().filter(card -> card instanceof InfantryPlatoon).forEach(card -> player.getOpponent().cardDamaged((Unit) card));
    }
}
