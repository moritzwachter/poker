package de.moritzwachter.poker;

import de.moritzwachter.poker.model.Hand;
import de.moritzwachter.poker.model.ScoredHand;
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

        printCommunityCards(communityCards);
        printPlayerCards(playerCards);

        List<Hand> listOfHands = collectFinalHands(communityCards, playerCards);

        List<ScoredHand> winners = HandComparator.getWinners(listOfHands);
        printWinners(winners);
    }

    private static void printCommunityCards(Hand communityCards) {
        System.out.println("Community Cards");
        communityCards.getHand().forEach(c -> System.out.print(c.toPrettyString() + " "));
    }

    private static void printPlayerCards(List<Hand> playerCards) {
        System.out.println("");
        for (int i = 0; i < playerCards.size(); i++) {
            System.out.print("\nPlayer " + (i + 1) + ": ");

            System.out.print(playerCards.get(i).toPrettyString());
        }
    }

    private static void printWinners(List<ScoredHand> winners) {
        System.out.println("\n======================");

        if (winners.size() > 1) {
            System.out.println("Split pot! The winners are: ");
            winners.forEach(h -> {
                System.out.printf("%s (%s) - %s %n", HandEvaluator.evaluateHand(h), h.getScore(), h.toPrettyString());
            });
        } else {
            ScoredHand winner = winners.get(0);
            System.out.printf("The winner is: %s (%s)%n", HandEvaluator.evaluateHand(winner), winner.getScore());
        }
    }

    private static List<Hand> collectFinalHands(Hand communityCards, List<Hand> playerCards) {
        List<Hand> listOfHands = new ArrayList<>();
        for (Hand playerCard : playerCards) {
            Hand hand = Hand.fromHands(communityCards, playerCard);
            listOfHands.add(HandEvaluator.getFinalHand(hand));
        }

        return listOfHands;
    }
}