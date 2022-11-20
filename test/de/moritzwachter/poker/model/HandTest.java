package de.moritzwachter.poker.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HandTest {
    @Test
    void fromHands() {
        Hand hand1 = new Hand("2S 3C 4D 5S 6H");
        Hand hand2 = new Hand(List.of(new Card(Value.ACE, Symbol.HEARTS), new Card(Value.KING, Symbol.DIAMONDS)));

        Hand hand = Hand.fromHands(hand1, hand2);

        assertEquals("2S 3C 4D 5S 6H AH KD", hand.getHandString());
    }
}
