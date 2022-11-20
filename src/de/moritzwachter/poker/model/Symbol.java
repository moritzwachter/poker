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

        throw new RuntimeException("Invalid card suit");
    }

    public String toString() {
        return symbol.toString();
    }

    public String toPrettyString() {
        return switch (symbol) {
            case 'S' -> "♤";
            case 'H' -> "♡";
            case 'D' -> "♢";
            case 'C' -> "♧";
            default -> "";
        };
    }
}
