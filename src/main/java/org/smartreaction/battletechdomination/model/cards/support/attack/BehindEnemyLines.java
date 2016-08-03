package org.smartreaction.battletechdomination.model.cards.support.attack;

import org.smartreaction.battletechdomination.model.cards.SupportAttack;
import org.smartreaction.battletechdomination.model.cards.actions.DiscardHandDownTo;
import org.smartreaction.battletechdomination.model.players.Player;

public class BehindEnemyLines extends SupportAttack {
    public BehindEnemyLines() {
        name = "Behind Enemy Lines";
        cardText = "+2 Card. Your opponent must discard down to three card in his hand.";
        industryCost = 3;
        imageFile = "BehindEnemyLines.png";
    }

    @Override
    public void cardPlayed(Player player) {
        player.drawCards(2);
        player.addOpponentAction(new DiscardHandDownTo(3, "Discard down to three cards"));
    }
}
