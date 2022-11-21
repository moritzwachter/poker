package de.moritzwachter.poker.service;

import de.moritzwachter.poker.model.Hand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class HandComparatorTest {

    @Test
    void hasStraightFlush() {
        Hand hand = new Hand("2H 3H 4H 5H 6H");
        assertTrue(HandComparator.hasFlush(hand));
        assertTrue(HandComparator.hasStraight(hand));
        assertTrue(HandComparator.hasStraightFlush(hand));
    }

    @Test
    void hasFlush() {
        Hand hearts = new Hand("2H 3H 4H 5H 6H");
        assertTrue(HandComparator.hasFlush(hearts));
        
        Hand diamonds = new Hand("2D 3D 4D 5D 6D");
        assertTrue(HandComparator.hasFlush(diamonds));

        Hand clubs = new Hand("2C 3C 4C 5C 6C");
        assertTrue(HandComparator.hasFlush(clubs));

        Hand spades = new Hand("2S 3S 4S 5S 6S");
        assertTrue(HandComparator.hasFlush(spades));
    }

    @Test
    void hasStraight() {
        Hand hand = new Hand("2H 3C 4H 5H 6H");
        assertTrue(HandComparator.hasStraight(hand));
    }

    @Test
    void hasStraightWithAceBottom()
    {
        Hand hand = new Hand("AH 2H 3C 4H 5D");
        assertTrue(HandComparator.hasStraight(hand));

        Hand hand2 = new Hand("2H 4C 5H 6D 7H AD");
        assertFalse(HandComparator.hasStraight(hand2));
    }

    @Test
    void hasFourOfAKind() {
        Hand hand = new Hand("2H 2D 2C 2S 6H");
        assertTrue(HandComparator.hasFourOfAKind(hand));

        Hand noFourOfAKind = new Hand("2H 3D 4C 5S 6H");
        assertFalse(HandComparator.hasFourOfAKind(noFourOfAKind));
    }

    @Test
    void hasFullhouse() {
        Hand hand = new Hand("2H 2D 2C 6S 6H");
        assertTrue(HandComparator.hasFullhouse(hand));

        Hand noFullHouse = new Hand("2H 3D 4C 5S 6H");
        assertFalse(HandComparator.hasFullhouse(noFullHouse));
    }

    @Test
    void hasThreeOfAKind() {
        Hand hand = new Hand("2H 2D 2C 3H 5C TC JH");
        assertTrue(HandComparator.hasThreeOfAKind(hand));
        assertFalse(HandComparator.hasStraightFlush(hand));
        assertFalse(HandComparator.hasFlush(hand));
        assertFalse(HandComparator.hasStraight(hand));
    }

    @Test
    void hasPair() {
        Hand hand = new Hand("TH TC 2C 3D 4S");
        assertTrue(HandComparator.hasPair(hand));
        assertFalse(HandComparator.hasThreeOfAKind(hand));
    }
}
