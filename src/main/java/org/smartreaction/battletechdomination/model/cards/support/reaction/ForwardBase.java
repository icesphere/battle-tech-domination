package org.smartreaction.battletechdomination.model.cards.support.reaction;

import org.smartreaction.battletechdomination.model.Player;
import org.smartreaction.battletechdomination.model.cards.ScrappableCard;
import org.smartreaction.battletechdomination.model.cards.SupportReaction;

public class ForwardBase extends SupportReaction implements ScrappableCard {
    public ForwardBase() {
        name = "Forward Base";
        cardText = "Place this card in your deployment zone. You may scrap this card when you are Overrun. If you do, you cannot gain any Overrun cards this turn.";
        industryCost = 4;
    }

    @Override
    public void cardScrapped(Player player) {
        //todo
    }
}
