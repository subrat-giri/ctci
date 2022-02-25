package ch7ObjectOrientedDesign.deckOfCards;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck<T extends Card> {
    private List<T> cardsList = new ArrayList<>();
    private int nextCardIndex = 0;
    public Deck(List<T> list) {
        cardsList = list;
    }
    public void shuffle() {
        Collections.shuffle(cardsList);
        nextCardIndex = 0;
    }

    public T[] dealHand(int n) {
        int toIndex = nextCardIndex + n;
        if (toIndex > cardsList.size()) {
            throw new IllegalArgumentException("No more cards");
        }
        List<T> result = cardsList.subList(nextCardIndex, toIndex);
        nextCardIndex = toIndex;
        return (T[]) result.toArray();
    }

    public T dealCard() {
        if (nextCardIndex >= cardsList.size()) {
            throw new IllegalStateException("No more cards Left");
        }
        nextCardIndex++;
        return cardsList.get(nextCardIndex - 1);
    }
}
