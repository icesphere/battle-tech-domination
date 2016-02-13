package org.smartreaction.battletechdomination.model;

import org.smartreaction.battletechdomination.model.cards.Card;
import org.smartreaction.battletechdomination.model.cards.overrun.CriticalHit;
import org.smartreaction.battletechdomination.model.cards.overrun.HeavyCasualties;
import org.smartreaction.battletechdomination.model.cards.overrun.RaidedSupplies;
import org.smartreaction.battletechdomination.model.cards.overrun.Retreat;
import org.smartreaction.battletechdomination.model.cards.resource.AdvancedFactory;
import org.smartreaction.battletechdomination.model.cards.resource.BasicFactory;
import org.smartreaction.battletechdomination.model.cards.resource.MunitionsFactory;
import org.smartreaction.battletechdomination.model.cards.unit.infantry.InfantryPlatoon;

import java.util.List;

public class Game {
    public static final boolean SHOW_GAME_LOG = false;

    private List<Player> players;

    private List<Card> supply;

    private List<HeavyCasualties> heavyCasualties;

    private List<RaidedSupplies> raidedSupplies;

    private List<CriticalHit> criticalHits;

    private List<Retreat> retreats;

    private List<InfantryPlatoon> infantryPlatoons;

    private List<BasicFactory> basicFactories;

    private List<MunitionsFactory> munitionsFactories;

    private List<AdvancedFactory> advancedFactories;

    private boolean createGameLog;

    private int turn;

    private StringBuilder gameLog = new StringBuilder();

    private int currentPlayerIndex;

    private boolean gameOver;

    public void gameLog(String log) {
        if (SHOW_GAME_LOG) {
            System.out.println(log);
        }
        if (createGameLog) {
            gameLog.append(log).append("<br/>");
        }
    }

    public void turnEnded() {
        gameLog("End of turn " + turn);

        if (supply.isEmpty() || heavyCasualties.isEmpty() || raidedSupplies.isEmpty() || criticalHits.isEmpty() || retreats.isEmpty()) {
            gameOver();
            return;
        }

        if (currentPlayerIndex == players.size() - 1) {
            currentPlayerIndex = 0;
        } else {
            currentPlayerIndex++;
        }

        turn++;
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

    public Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public boolean isCreateGameLog() {
        return createGameLog;
    }

    public void setCreateGameLog(boolean createGameLog) {
        this.createGameLog = createGameLog;
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

    public List<Retreat> getRetreats() {
        return retreats;
    }

    public void setRetreats(List<Retreat> retreats) {
        this.retreats = retreats;
    }

    public List<InfantryPlatoon> getInfantryPlatoons() {
        return infantryPlatoons;
    }

    public void setInfantryPlatoons(List<InfantryPlatoon> infantryPlatoons) {
        this.infantryPlatoons = infantryPlatoons;
    }

    public List<BasicFactory> getBasicFactories() {
        return basicFactories;
    }

    public void setBasicFactories(List<BasicFactory> basicFactories) {
        this.basicFactories = basicFactories;
    }

    public List<MunitionsFactory> getMunitionsFactories() {
        return munitionsFactories;
    }

    public void setMunitionsFactories(List<MunitionsFactory> munitionsFactories) {
        this.munitionsFactories = munitionsFactories;
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
}
