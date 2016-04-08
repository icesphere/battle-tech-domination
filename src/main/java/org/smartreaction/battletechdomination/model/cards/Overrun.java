package org.smartreaction.battletechdomination.model.cards;

import org.smartreaction.battletechdomination.model.players.Player;

public abstract class Overrun extends Card {
    protected Overrun() {
        cardType = CardType.OVERRUN;
    }

    protected int overrunAmount;

    @Override
    public void cardPlayed(Player player) {
    }

    public int getOverrunAmount() {
        return overrunAmount;
    }

    public void setOverrunAmount(int overrunAmount) {
        this.overrunAmount = overrunAmount;
    }

    @Override
    public boolean isActionable(Player player, String cardLocation) {
        return false;
    }
}
