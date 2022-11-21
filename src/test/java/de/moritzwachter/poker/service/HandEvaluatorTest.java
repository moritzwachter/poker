package de.moritzwachter.poker.service;

import de.moritzwachter.poker.model.Hand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HandEvaluatorTest {

    @Test
    void hasStraightFlush() {
        Hand hand = new Hand("2H 3H 4H 5H 6H");
        assertTrue(HandEvaluator.hasFlush(hand));
        assertTrue(HandEvaluator.hasStraight(hand));
        assertTrue(HandEvaluator.hasStraightFlush(hand));
    }

    @Test
    void hasFlush() {
        Hand hearts = new Hand("2H 3H 4H 5H 6H");
        assertTrue(HandEvaluator.hasFlush(hearts));
        
        Hand diamonds = new Hand("2D 3D 4D 5D 6D");
        assertTrue(HandEvaluator.hasFlush(diamonds));

        Hand clubs = new Hand("2C 3C 4C 5C 6C");
        assertTrue(HandEvaluator.hasFlush(clubs));

        Hand spades = new Hand("2S 3S 4S 5S 6S");
        assertTrue(HandEvaluator.hasFlush(spades));
    }

    @Test
    void hasStraight() {
        Hand hand = new Hand("2H 3C 4H 5H 6H");
        assertTrue(HandEvaluator.hasStraight(hand));
    }

    @Test
    void hasStraightWithAceBottom()
    {
        Hand hand = new Hand("AH 2H 3C 4H 5D");
        assertTrue(HandEvaluator.hasStraight(hand));

        Hand hand2 = new Hand("2H 4C 5H 6D 7H AD");
        assertFalse(HandEvaluator.hasStraight(hand2));
    }

    @Test
    void hasFourOfAKind() {
        Hand hand = new Hand("2H 2D 2C 2S 6H");
        assertTrue(HandEvaluator.hasFourOfAKind(hand));

        Hand noFourOfAKind = new Hand("2H 3D 4C 5S 6H");
        assertFalse(HandEvaluator.hasFourOfAKind(noFourOfAKind));
    }

    @Test
    void hasFullHouse() {
        Hand hand = new Hand("2H 2D 2C 6S 6H");
        assertTrue(HandEvaluator.hasFullHouse(hand));

        Hand noFullHouse = new Hand("2H 3D 4C 5S 6H");
        assertFalse(HandEvaluator.hasFullHouse(noFullHouse));
    }

    @Test
    void hasThreeOfAKind() {
        Hand hand = new Hand("2H 2D 2C 3H 5C TC JH");
        assertTrue(HandEvaluator.hasThreeOfAKind(hand));
        assertFalse(HandEvaluator.hasStraightFlush(hand));
        assertFalse(HandEvaluator.hasFlush(hand));
        assertFalse(HandEvaluator.hasStraight(hand));
    }

    @Test
    void hasPair() {
        Hand hand = new Hand("TH TC 2C 3D 4S");
        assertTrue(HandEvaluator.hasPair(hand));
        assertFalse(HandEvaluator.hasThreeOfAKind(hand));
    }

    @Test
    void hasTwoPair() {
        Hand hand = new Hand("TH TC 2C 2D 4S");
        assertTrue(HandEvaluator.hasTwoPair(hand));
        assertFalse(HandEvaluator.hasThreeOfAKind(hand));
    }

    @Test
    void getNoOfAKindWithTwoPair() {
        Hand hand = new Hand("TH TC 2C 2D 4S");
        Hand firstPair = HandEvaluator.getNoOfAKind(hand, 2);

        assertEquals("TH TC 2C 2D", firstPair.getHandString());
    }

    @Test
    void getWithoutValue() {
        Hand hand = new Hand("TH TC 2C 2D 4S TS");
        Hand without = new Hand("TC 2D");

        assertEquals("4S", HandEvaluator.getWithoutValue(hand, without).getHandString());
    }

    @Test
    void hasRoyalFlush() {
        Hand hand = new Hand("TH JH QH KH AH");
        Hand hand2 = new Hand("TC JC QC KC AC");
        assertTrue(HandEvaluator.hasRoyalFlush(hand));
        assertFalse(HandEvaluator.hasRoyalFlush(hand2));
        assertTrue(HandEvaluator.hasStraightFlush(hand2));
    }


}
