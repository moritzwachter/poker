package de.moritzwachter.poker.model;

public enum Value {
    TWO(2, '2'),
    THREE(3, '3'),
    FOUR(4, '4'),
    FIVE(5, '5'),
    SIX(6, '6'),
    SEVEN(7, '7'),
    EIGHT(8, '8'),
    NINE(9, '9'),
    TEN(10, 'T'),
    JACK(11, 'J'),
    QUEEN(12, 'Q'),
    KING(13, 'K'),
    ACE(14, 'A');

    public final Integer value;
    public final Character text;

    Value(Integer value, Character text) {
        this.value = value;
        this.text = text;
    }

    public static Value getByString(Character text) {
        for (Value val : Value.values()) {
            if (val.text.equals(text)) {
                return val;
            }
        }

        return null;
    }

    public String toString() {
        return text.toString();
    }
}
