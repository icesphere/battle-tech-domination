package org.smartreaction.battletechdomination.model.cards;

import org.smartreaction.battletechdomination.model.players.Player;

import java.util.Objects;
import java.util.UUID;

public abstract class Card {
    protected String id;
    protected String name;
    protected String subName;
    protected String cardText;
    protected CardType cardType;
    protected int industryCost;
    protected int losTechCost;
    protected String imageFile;

    public static String CARD_LOCATION_HAND = "hand";
    public static String CARD_LOCATION_DECK = "deck";
    public static String CARD_LOCATION_DISCARD = "discard";
    public static String CARD_LOCATION_PLAYER_UNITS = "playerUnits";
    public static String CARD_LOCATION_OPPONENT_UNITS = "opponentUnits";
    public static String CARD_LOCATION_SUPPLY = "supply";

    protected Card() {
        id = UUID.randomUUID().toString();
    }

    public abstract void cardPlayed(Player player);

    public void cardBought(Player player) {

    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }

        final Card other = (Card) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public String getCardText() {
        return cardText;
    }

    public void setCardText(String cardText) {
        this.cardText = cardText;
    }

    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    public int getIndustryCost() {
        return industryCost;
    }

    public void setIndustryCost(int industryCost) {
        this.industryCost = industryCost;
    }

    public int getLosTechCost() {
        return losTechCost;
    }

    public void setLosTechCost(int losTechCost) {
        this.losTechCost = losTechCost;
    }

    public boolean isUnit() {
        return this instanceof Unit;
    }

    public boolean isResource() {
        return this instanceof Resource;
    }

    public boolean isSupport() {
        return this instanceof Support || this instanceof SupportAttack || this instanceof SupportReaction || this instanceof OverrunSupport;
    }

    public boolean isOverrun() {
        return this instanceof Overrun;
    }

    public String getId() {
        return id;
    }

    public abstract boolean isActionable(Player player, String cardLocation);

    public String getImageFile() {
        return imageFile;
    }

    public void setImageFile(String imageFile) {
        this.imageFile = imageFile;
    }
}
