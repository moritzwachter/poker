package de.moritzwachter.poker.service;

import de.moritzwachter.poker.model.Card;
import de.moritzwachter.poker.model.Hand;
import de.moritzwachter.poker.model.Symbol;

import java.util.Collections;

public class HandComparator {

    public static String evaluateHand(Hand hand) {
        System.out.println("\n" + hand.toPrettyString());

        //hand.getHand().stream().sorted().forEachOrdered(System.out::print);

        System.out.print(hasPair(hand) ? "Pair\n" : "");
        System.out.print(hasThreeOfAKind(hand) ? "Three of a kind\n" : "");
        System.out.print(hasFullhouse(hand) ? "Fullhouse\n" : "");
        System.out.print(hasFourOfAKind(hand) ? "Four of a kind\n" : "");
        System.out.print(hasFlush(hand) ? "Flush\n" : "");

        return "";
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
}
