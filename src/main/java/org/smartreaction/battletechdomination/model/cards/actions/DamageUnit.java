package org.smartreaction.battletechdomination.model.cards.actions;

import org.smartreaction.battletechdomination.model.cards.Card;
import org.smartreaction.battletechdomination.model.cards.CardType;
import org.smartreaction.battletechdomination.model.cards.Unit;
import org.smartreaction.battletechdomination.model.players.Player;

public class DamageUnit extends Action {
    private CardType cardType;

    public DamageUnit() {
        this.text = "Damage a Unit";
    }

    public DamageUnit(CardType cardType, String text) {
        this.cardType = cardType;
        this.text = text;
    }

    public CardType getCardType() {
        return cardType;
    }

    @Override
    public boolean isCardActionable(Card card, String cardLocation, Player player) {
        return cardLocation.equals(Card.CARD_LOCATION_PLAYER_UNITS) && (cardType == null || card.getCardType() == cardType);
    }

    @Override
    public boolean processAction(Player player) {
        if (player.getDeploymentZone().isEmpty() || (cardType != null && player.getDeploymentZone().stream().noneMatch(u -> u.getCardType() == cardType))) {
            return false;
        } else {
            String log = player.getPlayerName() + " must damage ";
            if (cardType != null) {
                if (cardType == CardType.UNIT_INFANTRY) {
                    log += " an Infantry Unit";
                } else if (cardType == CardType.UNIT_MECH) {
                    log += " a Mech Unit";
                } else if (cardType == CardType.UNIT_VEHICLE) {
                    log += " a Vehicle Unit";
                }
            } else {
                log += " a Unit";
            }
            player.addGameLog(log);

            return true;
        }
    }

    @Override
    public void processActionResult(Player player, ActionResult result) {
        player.cardDamaged((Unit) result.getSelectedCard());
    }
}
