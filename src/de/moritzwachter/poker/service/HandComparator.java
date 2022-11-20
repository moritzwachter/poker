package de.moritzwachter.poker.service;

import de.moritzwachter.poker.model.Card;
import de.moritzwachter.poker.model.Hand;
import de.moritzwachter.poker.model.Symbol;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class HandComparator {

    public static String evaluateHand(Hand hand) {
        System.out.println("\n" + hand.toPrettyString(true));

        /*System.out.print(hasPair(hand) ? "Pair\n" : "");
        System.out.print(hasThreeOfAKind(hand) ? "Three of a kind\n" : "");
        System.out.print(hasFullhouse(hand) ? "Fullhouse\n" : "");*/
        System.out.print(hasFourOfAKind(hand) ? "Four of a kind\n" : "");
        System.out.print(hasFlush(hand) ? "Flush\n" : "");
        System.out.print(hasStraight(hand) ? "Straight\n" : "");
        System.out.print(hasStraightFlush(hand) ? "Straight Flush\n" : "");
        return "";
    }

    public static boolean hasStraight(Hand hand) {
        hand.getHand().sort(Comparator.naturalOrder());
        int straightLength = 1;

        for (int i=0; i < hand.getHand().size() - 1; i++) {
            Card card = hand.getHand().get(i);
            Card nextCard = hand.getHand().get(i + 1);

            if (card.getCardValue().value + 1 == nextCard.getCardValue().value) {
                straightLength++;
            }
        }

        return straightLength >= 5;
    }

    public static boolean hasFullhouse(Hand hand) {
        return hasPair(hand) && hasThreeOfAKind(hand);
    }

    public static boolean hasPair(Hand hand) {
        return hasNoOfAKind(hand, 2);
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

    public static boolean hasFlush(Hand hand) {
        return getPartialHandBySuit(hand, Symbol.HEARTS).getHand().size() == 5
            || getPartialHandBySuit(hand, Symbol.CLUBS).getHand().size() == 5
            || getPartialHandBySuit(hand, Symbol.DIAMONDS).getHand().size() == 5
            || getPartialHandBySuit(hand, Symbol.SPADES).getHand().size() == 5;
    }

    private static boolean hasNoOfAKind(Hand hand, int n) {
        for (Card card : hand.getHand()) {
            if (Collections.frequency(hand.getHand(), card) == n) {
                return true;
            }
        }

        return false;
    }

    public static boolean hasStraightFlush(Hand hand) {
        if (hasFlush(hand)) {

            ArrayList<Hand> hands = new ArrayList<>();
            hands.add(getPartialHandBySuit(hand, Symbol.DIAMONDS));
            hands.add(getPartialHandBySuit(hand, Symbol.HEARTS));
            hands.add(getPartialHandBySuit(hand, Symbol.SPADES));
            hands.add(getPartialHandBySuit(hand, Symbol.CLUBS));

            Hand flush = hands.stream().filter(h -> h.getHand().size() >= 5).findFirst().get();

            return HandComparator.hasStraight(flush);
        }

        return false;
    }
}
