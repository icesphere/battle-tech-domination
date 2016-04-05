package org.smartreaction.battletechdomination.model.cards.actions;

import org.smartreaction.battletechdomination.model.Choice;

import java.util.List;

public abstract class Action {
    protected String text;

    public String getText() {
        return text;
    }

    public List<Choice> getChoices() {
        return null;
    }
}
