package org.smartreaction.battletechdomination.model.cards.abilities.unit;

import org.smartreaction.battletechdomination.model.TurnPhase;
import org.smartreaction.battletechdomination.model.cards.Card;
import org.smartreaction.battletechdomination.model.cards.Unit;
import org.smartreaction.battletechdomination.model.cards.actions.DiscardCardsForBenefitActionCard;
import org.smartreaction.battletechdomination.model.cards.actions.DiscardCardsFromHandForBenefit;
import org.smartreaction.battletechdomination.model.players.Player;

import java.util.List;

public class QuadERPPCs extends UnitAbility implements DiscardCardsForBenefitActionCard {
    //QUAD ER PPCs: You may discard two cards from your hand at the start of your Combat phase. If you do, your opponent gains a Heavy Casualties card.

    public QuadERPPCs(Unit unit) {
        super(unit);
    }

    @Override
    public void useAbility(Player player) {
        player.addAction(new DiscardCardsFromHandForBenefit(this, 2, "Discard 2 cards from your hand to make your opponent gain a Heavy Casualties card."));
    }

    @Override
    public boolean isAbilityAvailable(Player player) {
        return player.getTurnPhase() == TurnPhase.COMBAT_START && player.getHandSize() >= 2;
    }

    @Override
    public void cardsDiscarded(Player player, List<Card> discardedCards) {
        player.getOpponent().gainHeavyCasualties();
    }

    @Override
    public void onChoseDoNotUse(Player player) {

    }
}
