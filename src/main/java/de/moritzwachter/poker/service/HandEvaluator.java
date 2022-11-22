package de.moritzwachter.poker.service;

import de.moritzwachter.poker.model.Card;
import de.moritzwachter.poker.model.Hand;
import de.moritzwachter.poker.model.Symbol;

import java.util.*;
import java.util.stream.Collectors;

public class HandEvaluator {

    public static Float evaluateHand(Hand hand) {
        // System.out.println("\n" + hand.toPrettyString(true));

        /*System.out.print(hasPair(hand) ? "Pair\n" : "");
        System.out.print(hasTwoPair(hand) ? "Two Pair\n" : "");
        System.out.print(hasThreeOfAKind(hand) ? "Three of a kind\n" : "");
        System.out.print(hasFullHouse(hand) ? "Full house\n" : "");
        System.out.print(hasFourOfAKind(hand) ? "Four of a kind\n" : "");
        System.out.print(hasFlush(hand) ? "Flush\n" : "");
        System.out.print(hasStraight(hand) ? "Straight\n" : "");
        System.out.print(hasStraightFlush(hand) ? "Straight Flush\n" : "");
        System.out.print(hasRoyalFlush(hand) ? "Royal Flush\n" : "");*/
        float score;
        
        if (hasRoyalFlush(hand)) {
            score = 10.0f;
        } else if (hasStraightFlush(hand)) {
            score = 9.0f;
        } else if (hasFourOfAKind(hand)) {
            score = 8.0f;
        } else if (hasFullHouse(hand)) {
            score = 7.0f;
        } else if (hasFlush(hand)) {
            score = 6.0f;
        } else if (hasStraight(hand)) {
            score = 5.0f;
        } else if (hasThreeOfAKind(hand)) {
            score = 4.0f;
        } else if (hasTwoPair(hand)) {
            score = 3.0f;
        } else if (hasPair(hand)) {
            score = 2.0f;
        } else {
            score = 1.0f;
        }

        return score;
    }

    public static Hand getFlush(Hand hand) {
        return getPartialHandBySuit(hand, getMostFrequentSymbol(hand));
    }

    private static Symbol getMostFrequentSymbol(Hand hand) {
        Map<Symbol, Long> countMap = countSymbolOccurrences(hand);
        return Collections.max(countMap.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

    public static Hand getFinalHand(Hand hand) {
        if (hasRoyalFlush(hand)) {
            return getFlush(hand).getHighestCards(5);
        }

        if (hasFlush(hand)) {
            return getFlush(hand).getHighestCards(5);
        }

        if (hasFourOfAKind(hand)) {
            Hand fourOfAKind = getNoOfAKind(hand, 4);
            return Hand.fromHands(fourOfAKind, hand.without(fourOfAKind).getHighestCards(1));
        }

        if (hasStraightFlush(hand)) {
            return getStraightFlush(hand).getHighestCards(5);
        }

        if (hasFullHouse(hand)) {
            return Hand.fromHands(getNoOfAKind(hand, 3), getNoOfAKind(hand, 2));
        }

        if (hasStraight(hand)) {
            return getStraight(hand).getHighestCards(5);
        }

        if (hasThreeOfAKind(hand)) {
            Hand threeOfAKind = getNoOfAKind(hand, 3);
            return Hand.fromHands(threeOfAKind, hand.without(threeOfAKind).getHighestCards(2));
        }

        if (hasTwoPair(hand)) {
            Hand twoPair = getNoOfAKind(hand, 2);
            return Hand.fromHands(twoPair, hand.without(twoPair).getHighestCards(1));
        }

        if (hasPair(hand)) {
            Hand pair = getNoOfAKind(hand, 2);
            return Hand.fromHands(pair, hand.without(pair).getHighestCards(3));
        }

        return hand.getHighestCards(5);
    }

    public static Map<Symbol, Long> countSymbolOccurrences(Hand hand) {
        return hand.getHand()
                .stream()
                .map(Card::getCardSymbol)
                .collect(Collectors.groupingBy(e -> e, Collectors.counting()));
    }

    public static boolean hasStraight(Hand hand) {
        return getStraight(hand).getHand().size() >= 5;
    }

    public static Hand getStraight(Hand hand) {
        Hand sortedHand = hand.sorted();
        List<Card> listOfCards = sortedHand.getHand();
        List<Card> result = new ArrayList<>();
        int currentVal = listOfCards.get(0).getCardValue().value;

        for (int i = 0; i < listOfCards.size() - 1; i++) {
            Card card = listOfCards.get(i);
            Card nextCard = listOfCards.get(i + 1);

            if (currentVal + 1 == nextCard.getCardValue().value) {
                if (result.size() == 0) {
                    result.add(card);
                }

                result.add(nextCard);
                currentVal++;
            }
        }

        // Special case: 2 3 4 5 ... A
        if (sortedHand.getHandValueString().contains("2 3 4 5")
                && sortedHand.getHandValueString().contains("A")
                && !sortedHand.getHandValueString().contains("6")
        ) {
            result = sortedHand.getHand()
                    .stream()
                    .filter(c -> "2 3 4 5 A ".contains(c.getCardValue().toString()))
                    .toList();
        }

        return new Hand(result);
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
        return new Hand(hand.getHand().stream().filter((Card c) -> c.getCardSymbol() == symbol).toList());
    }

    public static boolean hasFlush(Hand hand) {
        return getFlush(hand).getHand().size() == 5;
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

    public static boolean hasStraightFlush(Hand hand) {
        if (hasFlush(hand)) {
            Symbol symbol = getMostFrequentSymbol(hand);
            Hand flush = getPartialHandBySuit(hand, symbol);

            return hasStraight(flush);
        }

        return false;
    }

    public static Hand getStraightFlush(Hand hand) {
        return getStraight(getFlush(hand));
    }

    public static boolean hasRoyalFlush(Hand hand) {
        return hand.sorted().getHandString().contains("TH JH QH KH AH");
    }
}
