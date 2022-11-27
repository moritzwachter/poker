package de.moritzwachter.poker;

import de.moritzwachter.poker.model.Hand;
import de.moritzwachter.poker.service.Dealer;
import de.moritzwachter.poker.service.HandComparator;
import de.moritzwachter.poker.service.HandEvaluator;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Dealer dealer = new Dealer();

        dealer.dealNewRound(9);

        Hand communityCards = dealer.getCommunityCards();
        List<Hand> playerCards = dealer.getPocketCards();


        System.out.println("Community Cards");
        communityCards.getHand().forEach(c -> System.out.print(c.toPrettyString() + " "));

        System.out.println("");
        for (int i = 0; i < playerCards.size(); i++) {
            System.out.print("\nPlayer " + (i + 1) + ": ");

            playerCards.get(i).getHand().forEach(c -> System.out.print(c.toPrettyString()));
        }

        List<Hand> listOfHands = new ArrayList<>();
        for (Hand playerCard : playerCards) {
            Hand hand = Hand.fromHands(communityCards, playerCard);
            listOfHands.add(hand);
        }

        Hand winner = HandEvaluator.getFinalHand(HandComparator.getWinner(listOfHands));
        System.out.println("\n======================");
        System.out.println("Winner: " + HandEvaluator.evaluateHand(winner));
        System.out.println(winner.toPrettyString(true));
    }
}