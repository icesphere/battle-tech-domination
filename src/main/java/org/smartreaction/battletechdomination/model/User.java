package org.smartreaction.battletechdomination.model;

import java.util.Objects;

public class User {
    private String username;

    private Game currentGame;

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

    public boolean isAutoMatch() {
        return autoMatch;
    }

    public void setAutoMatch(boolean autoMatch) {
        this.autoMatch = autoMatch;
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
