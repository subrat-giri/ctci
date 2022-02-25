package ch7ObjectOrientedDesign.deckOfCards;

import java.util.ArrayList;
import java.util.List;

public class Hand<T extends Card> {
    protected List<T> cards = new ArrayList<>();

    public int score() {
        return cards.stream().mapToInt(Card::getValue).sum();
    }
    public void addCard(T card) {
        cards.add(card);
    }
}
