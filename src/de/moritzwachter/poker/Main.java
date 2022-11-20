package de.moritzwachter.poker;

import de.moritzwachter.poker.model.Card;
import de.moritzwachter.poker.service.CardFactory;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        Card card = CardFactory.fromString("TH");
        System.out.println(card);
    }
}