package de.moritzwachter.poker.service;

import de.moritzwachter.poker.model.Hand;

import java.util.*;

public class HandComparator {

    public static Hand getWinner(List<Hand> listOfHands) {
        SortedMap<Float, Hand> scoredHands = new TreeMap<>();

        for (Hand hand : listOfHands) {
            Float score = HandEvaluator.evaluateHand(hand);

            scoredHands.put(score, hand);
        }

        return scoredHands.get(scoredHands.lastKey());
    }
}
