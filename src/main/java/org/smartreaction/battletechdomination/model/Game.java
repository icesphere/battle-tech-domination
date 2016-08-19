package org.smartreaction.battletechdomination.model;

import org.smartreaction.battletechdomination.model.cards.Card;
import org.smartreaction.battletechdomination.model.cards.overrun.CriticalHit;
import org.smartreaction.battletechdomination.model.cards.overrun.HeavyCasualties;
import org.smartreaction.battletechdomination.model.cards.overrun.RaidedSupplies;
import org.smartreaction.battletechdomination.model.cards.overrun.Defeat;
import org.smartreaction.battletechdomination.model.cards.resource.AdvancedFactory;
import org.smartreaction.battletechdomination.model.cards.resource.HeavyFactory;
import org.smartreaction.battletechdomination.model.cards.resource.SupplyDrop;
import org.smartreaction.battletechdomination.model.cards.unit.infantry.InfantryPlatoon;
import org.smartreaction.battletechdomination.model.players.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Game {
    private String gameId;

    private List<Player> players = new ArrayList<>();

    private List<Card> supply = new ArrayList<>();

    private List<Card> supplyGrid = new ArrayList<>();

    private List<HeavyCasualties> heavyCasualties = new ArrayList<>();

    private List<RaidedSupplies> raidedSupplies = new ArrayList<>();

    private List<CriticalHit> criticalHits = new ArrayList<>();

    private List<Defeat> defeats = new ArrayList<>();

    private List<InfantryPlatoon> infantryPlatoons = new ArrayList<>();

    private List<SupplyDrop> supplyDrops = new ArrayList<>();

    private List<HeavyFactory> heavyFactories = new ArrayList<>();

    private List<AdvancedFactory> advancedFactories = new ArrayList<>();

    private boolean createGameLog;

    private int turn;

    private StringBuilder gameLog = new StringBuilder();

    private int currentPlayerIndex;

    private boolean gameOver;

    private Player quitGamePlayer;

    private List<ChatMessage> chatMessages = new ArrayList<>();

    public Game() {
        gameId = UUID.randomUUID().toString();
    }

    public void gameLog(String log) {
        gameLog.insert(0, log + "<br/>");
    }

    public void startGame() {
        currentPlayerIndex = 0;
        turn = 1;
        getCurrentPlayer().startTurn();
    }

    public void turnEnded() {
        getCurrentPlayer().setYourTurn(false);
        getCurrentPlayer().setTurnPhase(TurnPhase.NONE);

        if (supply.isEmpty() || heavyCasualties.isEmpty() || raidedSupplies.isEmpty() || criticalHits.isEmpty() || defeats.isEmpty()) {
            gameOver();
            return;
        }

        if (currentPlayerIndex == players.size() - 1) {
            currentPlayerIndex = 0;
        } else {
            currentPlayerIndex++;
        }

        turn++;

        getCurrentPlayer().startTurn();
    }

    private void gameOver() {
        gameOver = true;
        gameLog("-----------------------------");
        gameLog("Game over");
        gameLog("Turns: " + turn);

        for (Player player : players) {
            gameLog("----");
            String playerName = player.getPlayerName();
            gameLog(playerName + "'s cards: ");
            player.getAllCards().forEach(c -> gameLog(c.getName()));
        }
    }

    public void addCardToSupplyGrid() {
        addCardsToSupplyGrid(1);
    }

    public void addCardsToSupplyGrid(int cards) {
        for (int i = 0; i < cards; i++) {
            if (!supply.isEmpty()) {
                addCardToSupplyGrid(supply.remove(0));
            }
        }
    }

    public void addCardToSupplyGrid(Card card) {
        supplyGrid.add(card);
    }

    public String getCardsAsString(List cards) {
        String cardString = "";
        boolean first = true;
        for (Object card : cards) {
            if (!first) {
                cardString += ", ";
            } else {
                first = false;
            }
            cardString += ((Card) card).getName();
        }
        return cardString;
    }

    public Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public StringBuilder getGameLog() {
        return gameLog;
    }

    public void setGameLog(StringBuilder gameLog) {
        this.gameLog = gameLog;
    }

    public List<Card> getSupply() {
        return supply;
    }

    public void setSupply(List<Card> supply) {
        this.supply = supply;
    }

    public List<HeavyCasualties> getHeavyCasualties() {
        return heavyCasualties;
    }

    public void setHeavyCasualties(List<HeavyCasualties> heavyCasualties) {
        this.heavyCasualties = heavyCasualties;
    }

    public List<RaidedSupplies> getRaidedSupplies() {
        return raidedSupplies;
    }

    public void setRaidedSupplies(List<RaidedSupplies> raidedSupplies) {
        this.raidedSupplies = raidedSupplies;
    }

    public List<CriticalHit> getCriticalHits() {
        return criticalHits;
    }

    public void setCriticalHits(List<CriticalHit> criticalHits) {
        this.criticalHits = criticalHits;
    }

    public List<Defeat> getDefeats() {
        return defeats;
    }

    public void setDefeats(List<Defeat> defeats) {
        this.defeats = defeats;
    }

    public List<InfantryPlatoon> getInfantryPlatoons() {
        return infantryPlatoons;
    }

    public void setInfantryPlatoons(List<InfantryPlatoon> infantryPlatoons) {
        this.infantryPlatoons = infantryPlatoons;
    }

    public List<SupplyDrop> getSupplyDrops() {
        return supplyDrops;
    }

    public void setSupplyDrops(List<SupplyDrop> supplyDrops) {
        this.supplyDrops = supplyDrops;
    }

    public List<HeavyFactory> getHeavyFactories() {
        return heavyFactories;
    }

    public void setHeavyFactories(List<HeavyFactory> heavyFactories) {
        this.heavyFactories = heavyFactories;
    }

    public List<AdvancedFactory> getAdvancedFactories() {
        return advancedFactories;
    }

    public void setAdvancedFactories(List<AdvancedFactory> advancedFactories) {
        this.advancedFactories = advancedFactories;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }

    public String getGameId() {
        return gameId;
    }

    public List<Card> getSupplyGrid() {
        return supplyGrid;
    }

    public Player getWinner() {
        if (getCurrentPlayer().getPoints() > getCurrentPlayer().getOpponent().getPoints()) {
            return getCurrentPlayer();
        } else {
            return getCurrentPlayer().getOpponent();
        }
    }

    public void quitGame(Player player) {
        quitGamePlayer = player;
        gameOver = true;
    }

    public Player getQuitGamePlayer() {
        return quitGamePlayer;
    }

    public List<ChatMessage> getChatMessages() {
        return chatMessages;
    }
}
