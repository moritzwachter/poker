package de.moritzwachter.poker.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DealerTest {
    @Test
    void fullRun() {
        Dealer dealer = new Dealer();
        dealer.dealNewRound(1, 4618207293208155648L);

        assertEquals("8D 8S 2H 3C KH", dealer.getCommunityCards().getHandString());
    }

    @Test
    void randomFullRun() {
        Dealer dealer = new Dealer();
        dealer.dealNewRound(0);

        assertEquals(0, dealer.getPocketCards().size());
        assertEquals(5, dealer.getCommunityCards().getHand().size());
    }
}
