package com.jonathan.service;

import com.jonathan.domain.Card;
import com.jonathan.domain.Deck;
import com.jonathan.domain.Hand;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class PlayService {

    private final CalculationService calculationService;

    public Hand deal(Deck deck) {
        Hand hand = new Hand();
        hand.setCards(this.pickRandom(deck, 7));

        return hand;
    }

    public Hand check(Hand hand) {
        return this.calculationService.findHighestRank(hand);
    }

    private Set<Card> pickRandom(Deck deck, Integer number) {
        Set<Card> result = new TreeSet<>();

        List<Card> shuffled = new ArrayList<>();
        shuffled.addAll(deck.getRemainingCards());

        Collections.shuffle(shuffled);
        for (int i = 0; i < number; i++) {
            Card selected = shuffled.get(i);
            result.add(shuffled.get(i));

            deck.getRemainingCards().remove(selected);
            deck.getDealtCards().add(selected);
        }

        return result;
    }
}
