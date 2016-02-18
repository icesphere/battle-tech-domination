package org.smartreaction.battletechdomination.model.cards.support.reaction;

import org.smartreaction.battletechdomination.model.cards.SupportReaction;

public class ForwardBase extends SupportReaction {
    public ForwardBase() {
        name = "Forward Base";
        cardText = "Place this card in your deployment zone. You may scrap this card when you are Overrun. If you do, you cannot gain any Overrun cards this turn.";
        industryCost = 4;
    }
}
