package com.jonathan.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.TreeSet;

@Getter
@Setter
public class Deck {
    private Set<Card> remainingCards = new TreeSet<>();
    private Set<Card> dealtCards = new TreeSet<>();

    public Deck() {
        for (Card.Suit suit: Card.Suit.values()) {
            for (Card.Value value: Card.Value.values()) {
                Card card = new Card();
                card.setSuit(suit);
                card.setValue(value);
                this.getRemainingCards().add(card);
            }
        }
    }
}
