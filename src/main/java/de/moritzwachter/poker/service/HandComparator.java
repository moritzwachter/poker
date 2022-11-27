package de.moritzwachter.poker.service;

import de.moritzwachter.poker.model.Hand;
import de.moritzwachter.poker.model.ScoredHand;

import java.util.*;

public class HandComparator {

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
