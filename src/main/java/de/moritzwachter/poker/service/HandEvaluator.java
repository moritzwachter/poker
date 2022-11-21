package de.moritzwachter.poker.service;

import de.moritzwachter.poker.model.Card;
import de.moritzwachter.poker.model.Hand;
import de.moritzwachter.poker.model.Symbol;
import de.moritzwachter.poker.model.Value;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class HandEvaluator {

    public static String evaluateHand(Hand hand) {
        System.out.println("\n" + hand.toPrettyString(true));

        /*System.out.print(hasPair(hand) ? "Pair\n" : "");
        System.out.print(hasThreeOfAKind(hand) ? "Three of a kind\n" : "");
        System.out.print(hasFullHouse(hand) ? "Full house\n" : "");*/
        System.out.print(hasFourOfAKind(hand) ? "Four of a kind\n" : "");
        System.out.print(hasFlush(hand) ? "Flush\n" : "");
        System.out.print(hasStraight(hand) ? "Straight\n" : "");
        System.out.print(hasStraightFlush(hand) ? "Straight Flush\n" : "");
        System.out.print(hasRoyalFlush(hand) ? "Royal Flush\n" : "");
        return "";
    }

    public static boolean hasStraight(Hand hand) {
        List<Card> listOfCards = hand.getHand();
        listOfCards.sort(Comparator.naturalOrder());
        int straightLength = 1;

        for (int i = 0; i < listOfCards.size() - 1; i++) {
            Card card = listOfCards.get(i);
            Card nextCard = listOfCards.get(i + 1);

            if (card.getCardValue().value + 1 == nextCard.getCardValue().value) {
                straightLength++;
            }
        }

        // Special case: 2 3 4 5 ... A
        if (
            straightLength == 4
            && listOfCards.get(0).getCardValue() == Value.TWO
            && listOfCards.get(3).getCardValue() == Value.FIVE
            && listOfCards.get(listOfCards.size() - 1).getCardValue() == Value.ACE
        ) {
            straightLength++;
        }

        return straightLength >= 5;
    }

    public static boolean hasFullHouse(Hand hand) {
        return hasPair(hand) && hasThreeOfAKind(hand);
    }

    public static boolean hasNoOfAKind(Hand hand, int n) {
        return getNoOfAKind(hand, n).getHand().size() == n;
    }

    public static boolean hasPair(Hand hand) {
        return hasNoOfAKind(hand, 2);
    }

    public static boolean hasTwoPair(Hand hand) {
        Hand partialHand = getNoOfAKind(hand, 2);

        return partialHand.getHand().size() >= 4;
    }

    public static boolean hasThreeOfAKind(Hand hand) {
        return hasNoOfAKind(hand, 3);
    }

    public static boolean hasFourOfAKind(Hand hand) {
        return hasNoOfAKind(hand, 4);
    }

    public static Hand getPartialHandBySuit(Hand hand, Symbol symbol) {
        Hand partialHand = new Hand();
        for (Card card : hand.getHand()) {
            if (card.getCardSymbol() == symbol) {
                partialHand.add(card);
            }
        }

        return partialHand;
    }

    public static Hand getPartialHandByValue(Hand hand, Value value) {
        Hand partialHand = new Hand();
        for (Card card : hand.getHand()) {
            if (card.getCardValue() == value) {
                partialHand.add(card);
            }
        }

        return partialHand;
    }

    public static boolean hasFlush(Hand hand) {
        return getPartialHandBySuit(hand, Symbol.HEARTS).getHand().size() == 5
            || getPartialHandBySuit(hand, Symbol.CLUBS).getHand().size() == 5
            || getPartialHandBySuit(hand, Symbol.DIAMONDS).getHand().size() == 5
            || getPartialHandBySuit(hand, Symbol.SPADES).getHand().size() == 5;
    }

    public static Hand getNoOfAKind(Hand hand, int n) {
        Hand partialHand = new Hand();

        for (Card card : hand.getHand()) {
            if (Collections.frequency(hand.getHand(), card) == n) {
                partialHand.add(card);
            }
        }

        return partialHand;
    }

    public static Hand getWithoutValue(Hand hand, Hand without) {
        Hand partialHand = new Hand();

        for (Card card : hand.getHand()) {
            if (!without.getHand().contains(card)) {
                partialHand.add(card);
            }
        }

        return partialHand;
    }

    public static boolean hasStraightFlush(Hand hand) {
        if (hasFlush(hand)) {
            ArrayList<Hand> hands = new ArrayList<>();
            hands.add(getPartialHandBySuit(hand, Symbol.DIAMONDS));
            hands.add(getPartialHandBySuit(hand, Symbol.HEARTS));
            hands.add(getPartialHandBySuit(hand, Symbol.SPADES));
            hands.add(getPartialHandBySuit(hand, Symbol.CLUBS));

            Hand flush = hands.stream().filter(h -> h.getHand().size() >= 5).findFirst().get();

            return hasStraight(flush);
        }

        return false;
    }

    public static boolean hasRoyalFlush(Hand hand) {
        return hand.getSortedHandString().contains("TH JH QH KH AH");
    }
}
