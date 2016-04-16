package org.smartreaction.battletechdomination.model;

import org.smartreaction.battletechdomination.model.players.Player;

import java.util.Objects;

public class User {
    private String username;

    private Game currentGame;

    private GameOptions gameOptions = new GameOptions();

    private Player currentPlayer;

    private boolean autoMatch;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Game getCurrentGame() {
        return currentGame;
    }

    public void setCurrentGame(Game currentGame) {
        this.currentGame = currentGame;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public boolean isAutoMatch() {
        return autoMatch;
    }

    public void setAutoMatch(boolean autoMatch) {
        this.autoMatch = autoMatch;
    }

    public GameOptions getGameOptions() {
        return gameOptions;
    }

    public void setGameOptions(GameOptions gameOptions) {
        this.gameOptions = gameOptions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return Objects.equals(user.getUsername(), ((User) o).getUsername());
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
}
