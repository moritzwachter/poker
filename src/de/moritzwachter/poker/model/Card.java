package de.moritzwachter.poker.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Card {
    protected Value cardValue;
    protected Symbol cardSymbol;
    @Override
    public String toString() {
        return cardValue + cardSymbol.toString();
    }

    public String toPrettyString() {
        return cardValue + cardSymbol.toPrettyString();
    }
}
