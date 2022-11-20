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

    public static Symbol getByString(Character symbol) {
        for (Symbol sym : Symbol.values()) {
            if (sym.symbol.equals(symbol)) {
                return sym;
            }
        }

        return null;
    }

    public String toString() {
        return symbol.toString();
    }
}
