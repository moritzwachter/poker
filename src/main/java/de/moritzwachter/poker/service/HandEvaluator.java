package de.moritzwachter.poker.service;

import de.moritzwachter.poker.model.*;

import java.util.*;
import java.util.stream.Collectors;

public class HandEvaluator {

    public static PokerHand evaluateHand(Hand hand) {
        if (hasRoyalFlush(hand)) {
            return PokerHand.ROYAL_FLUSH;
        } else if (hasStraightFlush(hand)) {
            return PokerHand.STRAIGHT_FLUSH;
        } else if (hasFourOfAKind(hand)) {
            return PokerHand.FOUR_OF_A_KIND;
        } else if (hasFullHouse(hand)) {
            return PokerHand.FULL_HOUSE;
        } else if (hasFlush(hand)) {
            return PokerHand.FLUSH;
        } else if (hasStraight(hand)) {
            return PokerHand.STRAIGHT;
        } else if (hasThreeOfAKind(hand)) {
            return PokerHand.THREE_OF_A_KIND;
        } else if (hasTwoPair(hand)) {
            return PokerHand.TWO_PAIR;
        } else if (hasPair(hand)) {
            return PokerHand.ONE_PAIR;
        } else {
            return PokerHand.HIGH_CARD;
        }
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

        if (hasStraightFlush(hand)) {
            return getStraightFlush(hand).getHighestCards(5);
        }

        if (hasFourOfAKind(hand)) {
            Hand fourOfAKind = getNoOfAKind(hand, 4);
            return Hand.fromHands(fourOfAKind, hand.without(fourOfAKind).getHighestCards(1));
        }

        if (hasFullHouse(hand)) {
            return Hand.fromHands(getNoOfAKind(hand, 3), getNoOfAKind(hand, 2));
        }

        if (hasFlush(hand)) {
            return getFlush(hand).getHighestCards(5);
        }

        if (hasStraight(hand)) {
            return getStraight(hand).getHighestCards(5);
        }

        if (hasThreeOfAKind(hand)) {
            Hand threeOfAKind = getNoOfAKind(hand, 3);
            return Hand.fromHands(threeOfAKind, hand.without(threeOfAKind).getHighestCards(2));
        }

        if (hasTwoPair(hand)) {
            Hand firstPair = getNoOfAKind(hand, 2);
            Hand secondPair = getNoOfAKind(hand.without(firstPair), 2);

            Hand twoPair = Hand.fromHands(firstPair, secondPair);
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

        return getNoOfAKind(hand.without(partialHand), 2).getHand().size() == 2;
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

        if (partialHand.getHand().size() > n) {
            return partialHand.getHighestCards(n);
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
        String handString = hand.sorted().getHandString();
        String[] royalFlushHands = {
            "TH JH QH KH AH",
            "TC JC QC KC AC",
            "TS JS QS KS AS",
            "TD JD QD KD AD"
        };

        for (String royalFlushHand : royalFlushHands) {
            String[] royalFlushCards = royalFlushHand.split(" ");

            if (Arrays.stream(royalFlushCards).allMatch(handString::contains)) {
                return true;
            }
        }

        return false;
    }

    public static double evaluateCards(ScoredHand scored) {
        double score = scored.getHand()
                .stream()
                .map(c -> c.getCardValue().value)
                .mapToInt(Integer::intValue)
                .sum();

        // "A 2 3 4 5" case:
        if (hasStraight(scored) && scored.sorted().getHandValueString().contains("2 3 4 5 A")) {
            score -= (Value.ACE.value - 1);
        }

        return score / 100.0;
    }

    public static List<ScoredHand> getWinners(List<Hand> listOfHands) {
        Map<ScoredHand, Double> scoredHands = new HashMap<>();

        for (Hand hand : listOfHands) {
            ScoredHand scored = new ScoredHand(hand, HandEvaluator.evaluateHand(hand));
            scored.addScore(HandEvaluator.evaluateCards(scored));

            scoredHands.put(scored, scored.getScore());
        }

        Double maxValue = Collections.max(scoredHands.entrySet(), Comparator.comparingDouble(Map.Entry::getValue)).getValue();

        return scoredHands.keySet().stream().filter(hand -> hand.getScore() == maxValue).toList();
    }
}
