package de.moritzwachter.poker;

import de.moritzwachter.poker.model.Card;
import de.moritzwachter.poker.model.Deck;
import de.moritzwachter.poker.service.CardFactory;

public class Main {
    public static void main(String[] args) {
        Card card = CardFactory.fromString("TH");
        System.out.println(card);

        Deck deck = new Deck(5412351125L);
        System.out.println(deck.dealCard());
        System.out.println(deck.dealCard());
        System.out.println(deck.dealCard());
        System.out.println(deck.dealCard());
    }
}