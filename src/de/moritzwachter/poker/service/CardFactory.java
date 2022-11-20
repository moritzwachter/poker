package de.moritzwachter.poker.service;

import de.moritzwachter.poker.model.Card;
import de.moritzwachter.poker.model.Symbol;
import de.moritzwachter.poker.model.Value;

public class CardFactory {
    public static Card fromString(String cardText) {
        if (cardText.length() != 2) {
            throw new RuntimeException("Invalid card value.");
        }

        Character value = cardText.charAt(0);
        Character symbol = cardText.charAt(1);

        return new Card(Value.getByString(value), Symbol.getByString(symbol));
    }
}
