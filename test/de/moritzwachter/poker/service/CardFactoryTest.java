package de.moritzwachter.poker.service;

import de.moritzwachter.poker.model.Card;
import de.moritzwachter.poker.model.Symbol;
import de.moritzwachter.poker.model.Value;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CardFactoryTest {
    @Test
    void wrongLengthThrowsExceptions() {
        assertThrows(
            RuntimeException.class,
            () -> CardFactory.fromString("ABC"),
            "Invalid card value"
        );
    }

    @Test
    void createAllHearts() {
        assertEquals(CardFactory.fromString("2H"), new Card(Value.TWO, Symbol.HEARTS));
        assertEquals(CardFactory.fromString("3H"), new Card(Value.THREE, Symbol.HEARTS));
        assertEquals(CardFactory.fromString("4H"), new Card(Value.FOUR, Symbol.HEARTS));
        assertEquals(CardFactory.fromString("5H"), new Card(Value.FIVE, Symbol.HEARTS));
        assertEquals(CardFactory.fromString("6H"), new Card(Value.SIX, Symbol.HEARTS));
        assertEquals(CardFactory.fromString("7H"), new Card(Value.SEVEN, Symbol.HEARTS));
        assertEquals(CardFactory.fromString("8H"), new Card(Value.EIGHT, Symbol.HEARTS));
        assertEquals(CardFactory.fromString("9H"), new Card(Value.NINE, Symbol.HEARTS));
        assertEquals(CardFactory.fromString("TH"), new Card(Value.TEN, Symbol.HEARTS));
        assertEquals(CardFactory.fromString("JH"), new Card(Value.JACK, Symbol.HEARTS));
        assertEquals(CardFactory.fromString("QH"), new Card(Value.QUEEN, Symbol.HEARTS));
        assertEquals(CardFactory.fromString("KH"), new Card(Value.KING, Symbol.HEARTS));
        assertEquals(CardFactory.fromString("AH"), new Card(Value.ACE, Symbol.HEARTS));
    }

    @Test
    void createAllClubs() {
        assertEquals(CardFactory.fromString("2C"), new Card(Value.TWO, Symbol.CLUBS));
        assertEquals(CardFactory.fromString("3C"), new Card(Value.THREE, Symbol.CLUBS));
        assertEquals(CardFactory.fromString("4C"), new Card(Value.FOUR, Symbol.CLUBS));
        assertEquals(CardFactory.fromString("5C"), new Card(Value.FIVE, Symbol.CLUBS));
        assertEquals(CardFactory.fromString("6C"), new Card(Value.SIX, Symbol.CLUBS));
        assertEquals(CardFactory.fromString("7C"), new Card(Value.SEVEN, Symbol.CLUBS));
        assertEquals(CardFactory.fromString("8C"), new Card(Value.EIGHT, Symbol.CLUBS));
        assertEquals(CardFactory.fromString("9C"), new Card(Value.NINE, Symbol.CLUBS));
        assertEquals(CardFactory.fromString("TC"), new Card(Value.TEN, Symbol.CLUBS));
        assertEquals(CardFactory.fromString("JC"), new Card(Value.JACK, Symbol.CLUBS));
        assertEquals(CardFactory.fromString("QC"), new Card(Value.QUEEN, Symbol.CLUBS));
        assertEquals(CardFactory.fromString("KC"), new Card(Value.KING, Symbol.CLUBS));
        assertEquals(CardFactory.fromString("AC"), new Card(Value.ACE, Symbol.CLUBS));
    }

    @Test
    void createAllDiamonds() {
        assertEquals(CardFactory.fromString("2D"), new Card(Value.TWO, Symbol.DIAMONDS));
        assertEquals(CardFactory.fromString("3D"), new Card(Value.THREE, Symbol.DIAMONDS));
        assertEquals(CardFactory.fromString("4D"), new Card(Value.FOUR, Symbol.DIAMONDS));
        assertEquals(CardFactory.fromString("5D"), new Card(Value.FIVE, Symbol.DIAMONDS));
        assertEquals(CardFactory.fromString("6D"), new Card(Value.SIX, Symbol.DIAMONDS));
        assertEquals(CardFactory.fromString("7D"), new Card(Value.SEVEN, Symbol.DIAMONDS));
        assertEquals(CardFactory.fromString("8D"), new Card(Value.EIGHT, Symbol.DIAMONDS));
        assertEquals(CardFactory.fromString("9D"), new Card(Value.NINE, Symbol.DIAMONDS));
        assertEquals(CardFactory.fromString("TD"), new Card(Value.TEN, Symbol.DIAMONDS));
        assertEquals(CardFactory.fromString("JD"), new Card(Value.JACK, Symbol.DIAMONDS));
        assertEquals(CardFactory.fromString("QD"), new Card(Value.QUEEN, Symbol.DIAMONDS));
        assertEquals(CardFactory.fromString("KD"), new Card(Value.KING, Symbol.DIAMONDS));
        assertEquals(CardFactory.fromString("AD"), new Card(Value.ACE, Symbol.DIAMONDS));
    }

    @Test
    void createAllSpades() {
        assertEquals(CardFactory.fromString("2S"), new Card(Value.TWO, Symbol.SPADES));
        assertEquals(CardFactory.fromString("3S"), new Card(Value.THREE, Symbol.SPADES));
        assertEquals(CardFactory.fromString("4S"), new Card(Value.FOUR, Symbol.SPADES));
        assertEquals(CardFactory.fromString("5S"), new Card(Value.FIVE, Symbol.SPADES));
        assertEquals(CardFactory.fromString("6S"), new Card(Value.SIX, Symbol.SPADES));
        assertEquals(CardFactory.fromString("7S"), new Card(Value.SEVEN, Symbol.SPADES));
        assertEquals(CardFactory.fromString("8S"), new Card(Value.EIGHT, Symbol.SPADES));
        assertEquals(CardFactory.fromString("9S"), new Card(Value.NINE, Symbol.SPADES));
        assertEquals(CardFactory.fromString("TS"), new Card(Value.TEN, Symbol.SPADES));
        assertEquals(CardFactory.fromString("JS"), new Card(Value.JACK, Symbol.SPADES));
        assertEquals(CardFactory.fromString("QS"), new Card(Value.QUEEN, Symbol.SPADES));
        assertEquals(CardFactory.fromString("KS"), new Card(Value.KING, Symbol.SPADES));
        assertEquals(CardFactory.fromString("AS"), new Card(Value.ACE, Symbol.SPADES));
    }
}
