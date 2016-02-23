package org.smartreaction.battletechdomination.model.cards.support.attack;

import org.smartreaction.battletechdomination.model.players.Player;
import org.smartreaction.battletechdomination.model.cards.*;
import org.smartreaction.battletechdomination.model.cards.actions.DamageUnit;

import java.util.List;

public class CloseAirSupport extends SupportAttack {
    public CloseAirSupport() {
        name = "Close Air Support";
        cardText = "Discard a card. If you discarded a... Resource Card, your opponent must damage a unit; Unit Card, your opponent must damage a Mech; Support Card, your opponent must damage a unit and gain a Heavy Casualties card.";
        industryCost = 5;
    }

    @Override
    public void cardPlayed(Player player) {
        List<Card> cardsDiscarded = player.discardCardsFromHand(1, false);
        if (!cardsDiscarded.isEmpty()) {
            Card card = cardsDiscarded.get(0);
            if (card instanceof Resource) {
                player.addOpponentAction(new DamageUnit());
            } else if (card instanceof Unit) {
                player.addOpponentAction(new DamageUnit(CardType.UNIT_MECH));
            } else if (card instanceof Support) {
                player.addOpponentAction(new DamageUnit());
                player.getOpponent().gainHeavyCasualties();
            }
        }
    }
}
