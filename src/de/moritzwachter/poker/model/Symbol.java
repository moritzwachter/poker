package de.moritzwachter.poker.model;

public enum Symbol {
    HEARTS('H'),
    CLUBS('C'),
    DIAMONDS('D'),
    SPADES('S');

    public final Character symbol;
    Symbol(Character symbol) {
        this.symbol = symbol;
    }
}
