package org.smartreaction.battletechdomination.model.cards.actions;

import org.smartreaction.battletechdomination.model.cards.Card;

import java.util.ArrayList;
import java.util.List;

public class ActionResult {
    private List<Card> selectedCards = new ArrayList<>(3);

    private Integer choiceSelected;

    public ActionResult() {
    }

    public List<Card> getSelectedCards() {
        return selectedCards;
    }

    public void setSelectedCards(List<Card> selectedCards) {
        this.selectedCards = selectedCards;
    }

    public Integer getChoiceSelected() {
        return choiceSelected;
    }

    public void setChoiceSelected(Integer choiceSelected) {
        this.choiceSelected = choiceSelected;
    }
}
