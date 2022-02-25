package ch7ObjectOrientedDesign.deckOfCards;

public abstract class Card {
    public enum Suit {
        SPADE, DIAMOND, HEART, CLUB
    }
    public enum Type {
        ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, KING, QUEEN
    }
    protected final Suit suit;
    protected final Type type;
    protected final boolean isAvailable;
    public Card(Suit suit, Type type, boolean isAvailable) {
        this.suit = suit;
        this.type = type;
        this.isAvailable = isAvailable;
    }
    public Suit getSuit() {
        return suit;
    }
    public Type getCardType() {
        return type;
    }
    public boolean isAvailable() {
        return isAvailable;
    }
    public abstract int getValue();
}
