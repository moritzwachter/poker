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

        Hand noStraight = new Hand("3S 4C 6S TD JD QC KD");
        assertFalse(HandEvaluator.hasStraight(noStraight));
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
    void getStraight() {
        Hand hand = new Hand("AH 2H 3C 4H 5D 6S");
        assertEquals("2H 3C 4H 5D 6S", HandEvaluator.getStraight(hand).getHandString());

        Hand aceStraight = new Hand("2H 3C 4H 5H 7H 8C AC");
        assertEquals("2H 3C 4H 5H AC", HandEvaluator.getStraight(aceStraight).getHandString());
    }

    @Test
    void getStraightFlush() {
        Hand hand = new Hand("AH KH QH JH TH 5D 7S");
        assertEquals("TH JH QH KH AH", HandEvaluator.getStraightFlush(hand).getHandString());

        Hand aceStraight = new Hand("2H 3H 4H 5H 7H 8C AH");
        assertEquals("2H 3H 4H 5H AH", HandEvaluator.getStraightFlush(aceStraight).getHandString());
    }

    @Test
    void hasRoyalFlush() {
        Hand hand = new Hand("TH JH QH KH AH");
        Hand hand2 = new Hand("TC JC QC KC AC");
        assertTrue(HandEvaluator.hasRoyalFlush(hand));
        assertFalse(HandEvaluator.hasRoyalFlush(hand2));
        assertTrue(HandEvaluator.hasStraightFlush(hand2));
    }

    @Test
    void getFinalHand() {
        Hand royalFlush = new Hand("AC TH JH QH KH AH TC");
        assertEquals("AH KH QH JH TH", HandEvaluator.getFinalHand(royalFlush).getHandString());

        Hand fourOfAKind = new Hand("2H 2D 2C 2S 5C TC JH");
        assertEquals("2H 2D 2C 2S JH", HandEvaluator.getFinalHand(fourOfAKind).getHandString());

        Hand straightFlush = new Hand("AC TH 9H QH JH 8H TC");
        assertEquals("QH JH TH 9H 8H", HandEvaluator.getFinalHand(straightFlush).getHandString());

        Hand fullHouse = new Hand("2H 2D 2C 5H 5C TC JH");
        assertEquals("2H 2D 2C 5H 5C", HandEvaluator.getFinalHand(fullHouse).getHandString());

        Hand flush = new Hand("AC TH 9H QH JH 2H TC");
        assertEquals("QH JH TH 9H 2H", HandEvaluator.getFinalHand(flush).getHandString());

        Hand threeOfAKind = new Hand("2H 2D 2C 3H 5C TC JH");
        assertEquals("2H 2D 2C JH TC", HandEvaluator.getFinalHand(threeOfAKind).getHandString());

        Hand twoPair = new Hand("TH TC 3D 3S KD AC");
        assertEquals("TH TC 3D 3S AC", HandEvaluator.getFinalHand(twoPair).getHandString());

        Hand pair = new Hand("TH TC 2C 3D 4S AC KD");
        assertEquals("TH TC AC KD 4S", HandEvaluator.getFinalHand(pair).getHandString());

        Hand highCard = new Hand("TH 9C 6S 5C 3S");
        assertEquals("TH 9C 6S 5C 3S", HandEvaluator.getFinalHand(highCard).getHandString());
    }
}
