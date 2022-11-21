package de.moritzwachter.poker.service;

import de.moritzwachter.poker.model.Card;
import de.moritzwachter.poker.model.Deck;
import de.moritzwachter.poker.model.Hand;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Dealer {

    private Deck deck;

    @Getter
    private List<Hand> pocketCards = new ArrayList<>();

    @Getter
    private Hand communityCards = new Hand();

    public void dealNewRound(int playerCount) {
        Long seed = new Random().nextLong();
        // Flush: -4906000838828884205 (9 players)
        // Straight: 2029875863529557528 (9 players)
        // Straight Flush: 4618207293208155648 (9 players)
        // Royal Flush: 6901695129663031837 (9 players)

        dealNewRound(playerCount, seed);
    }

    public void dealNewRound(int playerCount, Long seed) {
        pocketCards = new ArrayList<>();
        communityCards = new Hand();

        System.out.println("Seed " + seed);
        deck = new Deck(seed);

        for (int i = 0; i < playerCount * 2; i++) {
            if (i < playerCount) {
                pocketCards.add(i, new Hand());
            }

            pocketCards.get(i % playerCount).add(deck.dealCard());
        }

        // flop
        deck.dealCard();
        communityCards.add(deck.dealCard());
        communityCards.add(deck.dealCard());
        communityCards.add(deck.dealCard());

        // turn
        deck.dealCard();
        communityCards.add(deck.dealCard());

        // river
        deck.dealCard();
        communityCards.add(deck.dealCard());
    }
}
