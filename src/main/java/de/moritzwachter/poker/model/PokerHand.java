package de.moritzwachter.poker.model;

public enum PokerHand {
    ROYAL_FLUSH(10.0),
    STRAIGHT_FLUSH(9.0),
    FOUR_OF_A_KIND(8.0),
    FULL_HOUSE(7.0),
    FLUSH(6.0),
    STRAIGHT(5.0),
    THREE_OF_A_KIND(4.0),
    TWO_PAIR(3.0),
    ONE_PAIR(2.0),
    HIGH_CARD(1.0);

    public final double score;
    PokerHand(double score) {
        this.score = score;
    }

    public String toString() {
        return name().replace('_', ' ');
    }
}
