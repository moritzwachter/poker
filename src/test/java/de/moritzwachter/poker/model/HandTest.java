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

    @Test
    void getSortedHandValueString() {
        Hand hand = new Hand("2S 3C 4D 5S 6H");
        assertEquals("2 3 4 5 6", hand.getHandValueString());

        Hand unordered = new Hand("AS 3C JD 5S QH");
        assertEquals("A 3 J 5 Q", unordered.getHandValueString());
        assertEquals("3 5 J Q A", unordered.sorted().getHandValueString());

        Hand royalFlush = new Hand("AH KH QH JH TH");
        assertEquals("AH KH QH JH TH", royalFlush.getHandString());
        assertEquals("TH JH QH KH AH", royalFlush.sorted().getHandString());
    }


    @Test
    void without() {
        Hand hand = new Hand("TH TC 2C 2D 4S TS");
        Hand without = new Hand("TC 2D");

        assertEquals("4S", hand.without(without).getHandString());
    }
}
