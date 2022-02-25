package ch7ObjectOrientedDesign.deckOfCards;

import java.util.List;

public class BlackJackHand extends Hand<BlackJackCard> {
    BlackJackHand(List<BlackJackCard> cards) {
        this.cards = cards;
    }
    @Override
    public int score() {
        int numOfAces = (int) cards.stream().filter(card -> Card.Type.ACE.equals(card.getCardType())).count();
        int valuesOtherThanAce = cards.stream().filter(card -> !Card.Type.ACE.equals(card.getCardType()))
                .mapToInt(Card::getValue).sum();
        int closestValUnder21ElseHigher = 0;
        int lastVal;
        for (int i = 0; i <= numOfAces; i++) {
            lastVal = closestValUnder21ElseHigher;
            closestValUnder21ElseHigher = valuesOtherThanAce + (numOfAces - i) + 11 * i;
            if (closestValUnder21ElseHigher == 21) {
                return closestValUnder21ElseHigher;
            } else if (closestValUnder21ElseHigher > 21) {
                return lastVal != 0 ? lastVal:  closestValUnder21ElseHigher;
            }
        }
        return closestValUnder21ElseHigher;
    }

    public boolean isBusted() {
        return score() > 21;
    }

    public boolean is21() {
        return score() == 21;
    }
}
