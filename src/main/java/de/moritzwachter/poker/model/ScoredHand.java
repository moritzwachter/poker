package de.moritzwachter.poker.model;

import lombok.Getter;

@Getter
public class ScoredHand extends Hand {

    protected double score;
    protected PokerHand pokerHand;

    public ScoredHand(Hand hand, PokerHand pokerHand) {
        super(hand.getHand());
        this.score = pokerHand.score;
        this.pokerHand = pokerHand;
    }

    public void addScore(double cardScore) {
        if (cardScore >= 1.0) {
            throw new IllegalArgumentException("Value cannot be higher than 0.9f");
        }

        score += cardScore;
    }
}
