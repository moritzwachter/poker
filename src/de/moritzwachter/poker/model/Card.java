package de.moritzwachter.poker.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Card implements Comparable<Card> {
    public static final String ANSI_RESET = "\u001B[0m";

    protected Value cardValue;
    protected Symbol cardSymbol;
    @Override
    public String toString() {
        return cardValue + cardSymbol.toString();
    }

    public String toPrettyString() {
        String bgColor;
        if (cardSymbol == Symbol.HEARTS || cardSymbol == Symbol.DIAMONDS) {
            bgColor = "\u001B[41m";
        } else {
            bgColor = "\u001B[40m";
        }
        return bgColor + cardValue + cardSymbol.toPrettyString() + ANSI_RESET;
    }

    @Override
    public int compareTo(Card o) {
        return cardValue.compareTo(o.cardValue);
    }

    @Override
    public boolean equals(Object obj) {
        return cardValue == ((Card) obj).getCardValue();
    }
}
