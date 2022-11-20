package de.moritzwachter.poker.model;

import lombok.Data;

@Data
public class Card {
    protected Symbol cardSymbol;
    protected Value cardValue;
}
