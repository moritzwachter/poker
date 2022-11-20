package de.moritzwachter.poker;

import de.moritzwachter.poker.model.Card;
import de.moritzwachter.poker.model.Deck;
import de.moritzwachter.poker.service.Dealer;

import java.sql.SQLOutput;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Dealer dealer = new Dealer();
        dealer.dealNewRound(5);

        List<Card> communityCards = dealer.getCommunityCards();
        List<List<Card>> playerCards = dealer.getPocketCards();

        System.out.println("Community Cards");
        communityCards.stream().forEach(c -> System.out.print(c.toPrettyString() + " "));

        System.out.println("");
        for (int i = 0; i < playerCards.size(); i++) {
            System.out.print("\nPlayer " + (i+1) + ": ");

            playerCards.get(i).stream().forEach(c -> System.out.print(c.toPrettyString()));
        }
    }
}