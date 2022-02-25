package ch7ObjectOrientedDesign.deckOfCards;

public class BlackJackCard extends Card {
    private final int faceValue;

    BlackJackCard(Suit suit, Type type, int value) {
        super(suit, type, true);
        this.faceValue = value;
    }
    public boolean isAce() {
        return getValue() == 1;
    }
    public int getValue() {
        if (this.faceValue >= 11) {
            return 10;
        }
        return this.faceValue;
    }

    public int minFaceValue() {
        if (Type.ACE.equals(this.type)) {
            return 1;
        } else {
            return this.faceValue;
        }
    }

    public int maxFaceValue() {
        if (Type.ACE.equals(this.type)) {
            return 11;
        } else {
            return this.faceValue;
        }
    }
}
