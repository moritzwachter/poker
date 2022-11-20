package de.moritzwachter.poker.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeckTest {
    @Test
    void deckHas52Cards() {
        Deck deck = new Deck(null);
        assertEquals(52, deck.getDeck().size());

        Deck deck2 = new Deck(5412351125L);
        assertEquals(52, deck2.getDeck().size());
    }

    @Test
    void shuffleWithSeedReturnsSameResult() {
        Deck deck = new Deck(5412351125L);

        assertEquals("6S", deck.dealCard().toString());
        assertEquals("6H", deck.dealCard().toString());
        assertEquals("JH", deck.dealCard().toString());
        assertEquals("KC", deck.dealCard().toString());
        assertEquals("QD", deck.dealCard().toString());
    }

    @Test
    void dealCardDecreasesDeckSize() {
        Deck deck = new Deck(123L);
        deck.dealCard();

        assertEquals(51, deck.getDeck().size());
    }
}
