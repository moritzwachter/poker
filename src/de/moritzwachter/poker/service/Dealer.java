package de.moritzwachter.poker.service;

import de.moritzwachter.poker.model.Card;
import de.moritzwachter.poker.model.Deck;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Dealer {

    private Deck deck;

    @Getter
    private List<List<Card>> pocketCards = new ArrayList<List<Card>>();

    @Getter
    private List<Card> communityCards = new ArrayList<>();

    public void dealNewRound(int playerCount) {
        deck = new Deck(new Random().nextLong());

        for (int i = 0; i < playerCount * 2; i++) {
            if (i < playerCount) {
                pocketCards.add(i, new ArrayList<>());
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
