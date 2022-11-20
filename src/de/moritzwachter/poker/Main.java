package de.moritzwachter.poker;

import de.moritzwachter.poker.model.Deck;

public class Main {
    public static void main(String[] args) {
        Deck deck = new Deck(5412351125L);
        System.out.println(deck.dealCard().toPrettyString());
        System.out.println(deck.dealCard().toPrettyString());
        System.out.println(deck.dealCard().toPrettyString());
        System.out.println(deck.dealCard().toPrettyString());
    }
}