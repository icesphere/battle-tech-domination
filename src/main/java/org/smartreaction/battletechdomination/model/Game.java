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
    
    private List<String> planetNames = new ArrayList<>();

    private boolean createGameLog;

    private int turn;

    private StringBuilder gameLog = new StringBuilder();

    private int currentPlayerIndex;

    private boolean gameOver;
    
    private boolean startGamePlayer;
    private boolean startGameOpponent;
    
    private String planetName;

    private Player quitGamePlayer;

    private List<ChatMessage> chatMessages = new ArrayList<>();

    public Game() {
        gameId = UUID.randomUUID().toString();
        populatePlanetList();
        planetName = planetNames.get((int)(Math.random() * planetNames.size()));
    }
    
    public void populatePlanetList() {
        planetNames.add("Blackstone");
        planetNames.add("Butte Hold");
        planetNames.add("Crellacor");
        planetNames.add("Drask's Den");
        planetNames.add("Ferris");
        planetNames.add("Gustrell");
        planetNames.add("Oberon VI");
        planetNames.add("Paulus Prime");
        planetNames.add("Placida");
        planetNames.add("The Rock");
        planetNames.add("Sigurd");
        planetNames.add("Botany Bay");
        planetNames.add("Erewhon");
        planetNames.add("Gotterdammerung");
        planetNames.add("Lackhove");
        planetNames.add("Last Chance");
        planetNames.add("Von Strang's World");
        planetNames.add("Elissa");
        planetNames.add("Manaringaine");
        planetNames.add("Nyserta");
        planetNames.add("Porthos");
        planetNames.add("Santander's World");
        planetNames.add("Alleghe");
        planetNames.add("Balsta");
        planetNames.add("Chateau");
        planetNames.add("Icar");
        planetNames.add("New Caledonia");
        planetNames.add("Outpost");
        planetNames.add("St. John");
        planetNames.add("Skallevoll");
        planetNames.add("Svelvik");
        planetNames.add("The Edge");
        planetNames.add("Anywhere");
        planetNames.add("Barcelona");
        planetNames.add("Bensinger");
        planetNames.add("Bone-Norman");
        planetNames.add("Here");
        planetNames.add("Persistence");
        planetNames.add("Steelton");
        planetNames.add("Toland");
        planetNames.add("Trell I");
        planetNames.add("Winfield");
        planetNames.add("Constance");
        planetNames.add("Damian");
        planetNames.add("Holmsbu");
        planetNames.add("Pinnacle");
        planetNames.add("Thule");
        planetNames.add("Almunge");
        planetNames.add("Bjarred");
        planetNames.add("Idlewind");
        planetNames.add("Richmond");
        planetNames.add("Rockland");
        planetNames.add("Schwartz");
        planetNames.add("Stapelfeld");
        planetNames.add("Tarnby");
        planetNames.add("Turtle Bay");
        planetNames.add("Virentofta");
    }

    public void gameLog(String log) {
        gameLog.insert(0, log + "<br/>");
    }

    public void startGame() {
        currentPlayerIndex = 0;
        startGamePlayer = false;
        startGameOpponent = false;
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
    
    public String getSupplyClass() {
        if(supply.size() > 20) return "supplyLeftOver20";
        else if(supply.size() <= 20 && supply.size() > 10) return "supplyLeftOver10";
        else if(supply.size() <= 10 && supply.size() > 5) return "supplyLeftOver5";
        else return "supplyLeftUnder5";
    }
    
    public String getOverrrunClass(List<Card> overrun, int limit) {
        if(overrun.size() <= limit) {
            return "overrunCardBlinking";
        } else {
            return "";
        }
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
    
    public boolean isStartGameOpponent() {
        return startGameOpponent;
    }
    
    public boolean isStartGamePlayer() {
        return startGamePlayer;
    }
    
    public String getPlanetName() {
        return planetName;
    }
    
    public void playerReady(Player player) {
        if(player == getCurrentPlayer()) startGamePlayer = true;
        else startGameOpponent = true;
    }
    
    public boolean isStartGame() {
        return startGameOpponent && startGamePlayer;
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
