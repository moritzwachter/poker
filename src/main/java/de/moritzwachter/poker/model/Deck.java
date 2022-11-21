package de.moritzwachter.poker.model;

import de.moritzwachter.poker.service.CardFactory;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Deck {
    public final String[] POSSIBLE_CARDS = {
            "2H", "3H", "4H", "5H", "6H", "7H", "8H", "9H", "TH", "JH", "QH", "KH", "AH",
            "2D", "3D", "4D", "5D", "6D", "7D", "8D", "9D", "TD", "JD", "QD", "KD", "AD",
            "2S", "3S", "4S", "5S", "6S", "7S", "8S", "9S", "TS", "JS", "QS", "KS", "AS",
            "2C", "3C", "4C", "5C", "6C", "7C", "8C", "9C", "TC", "JC", "QC", "KC", "AC",
    };

    @Getter
    private final List<Card> deck = new ArrayList<>();

    public Deck(Long seed) {
        for (String cardString : POSSIBLE_CARDS) {
            deck.add(CardFactory.fromString(cardString));
        }

        seed = (seed != null) ? seed : 0L;

        Collections.shuffle(deck, new Random(seed));
    }

    public Card dealCard() {
        Card card = deck.get(0);
        deck.remove(0);

        return card;
    }
}
