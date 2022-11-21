package de.moritzwachter.poker.model;

import de.moritzwachter.poker.service.CardFactory;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
public class Hand {
    @Getter
    private List<Card> hand = new ArrayList<>();

    public Hand(String allCards) {
        String[] cards = allCards.split("\\s");

        for (String cardText : cards) {
            hand.add(CardFactory.fromString(cardText));
        }
    }

    public Hand(List<Card> cards) {
        hand.addAll(cards);
    }

    public void add(Card card) {
        hand.add(card);
    }

    public static Hand fromHands(Hand communityCards, Hand pocketCards) {
        return new Hand(communityCards.getHandString() + " " + pocketCards.getHandString());
    }

    public String getHandString() {
        return hand.stream().map(Card::toString).collect(Collectors.joining(" "));
    }

    public String getSortedHandString() {
        return hand.stream().sorted().map(Card::toString).collect(Collectors.joining(" "));
    }

    public String toPrettyString(boolean sorted) {
        if (sorted) {
            return hand.stream().sorted().map(Card::toPrettyString).collect(Collectors.joining(" "));
        }

        return hand.stream().map(Card::toPrettyString).collect(Collectors.joining(" "));
    }
}
