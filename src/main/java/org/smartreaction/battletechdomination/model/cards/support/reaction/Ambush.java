package org.smartreaction.battletechdomination.model.cards.support.reaction;

import org.smartreaction.battletechdomination.model.cards.SupportReaction;

public class Ambush extends SupportReaction {
    public Ambush() {
        name = "Ambush!";
        cardText = "Place this card in your deployment zone. When you are Overrun, scrap this card.  At the start of your next turn choose a Mech for your opponent to damage.";
        industryCost = 2;
    }
}
