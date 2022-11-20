package de.moritzwachter.poker.service;

import de.moritzwachter.poker.model.Hand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class HandComparatorTest {

    @Test
    void straightFlush() {
        Hand hand = new Hand("2H 3H 4H 5H 6H");
        assertTrue(HandComparator.hasFlush(hand));
        assertTrue(HandComparator.hasStraight(hand));
        assertTrue(HandComparator.hasStraightFlush(hand));
    }
}
