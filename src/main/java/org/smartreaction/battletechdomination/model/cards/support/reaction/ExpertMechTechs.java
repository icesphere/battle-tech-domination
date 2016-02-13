package org.smartreaction.battletechdomination.model.cards.support.reaction;

import org.smartreaction.battletechdomination.model.Player;
import org.smartreaction.battletechdomination.model.cards.Scrappable;
import org.smartreaction.battletechdomination.model.cards.SupportReaction;

public class ExpertMechTechs extends SupportReaction implements Scrappable {
    public ExpertMechTechs() {
        name = "Expert Mech Techs";
        cardText = "Place this card in your deployment zone. You may scrap this card when one of your Mechs is damaged. If you do, put that Mech into your hand instead of your discard pile.";
        industryCost = 3;
    }

    @Override
    public void cardScrapped(Player player) {
        //todo
    }
}