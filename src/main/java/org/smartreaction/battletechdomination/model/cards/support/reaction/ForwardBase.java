package org.smartreaction.battletechdomination.model.cards.support.reaction;

import org.smartreaction.battletechdomination.model.cards.SupportReaction;

public class ForwardBase extends SupportReaction {
    public ForwardBase() {
        name = "Forward Base";
        cardText = "Place this card in your deployment zone. Scrap this card when you are Overrun to prevent gaining the Overrun card.";
        industryCost = 4;
    }
}
