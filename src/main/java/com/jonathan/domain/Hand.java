package com.jonathan.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

@Getter
@Setter
@NoArgsConstructor
public class Hand {

    private Set<Card> cards;

    private Card.Rank rank;
    private Set<Card> rankedCards;
    private Set<Card> leftoverCards;

    public Hand(Card... cards) {
        Assert.isTrue(cards != null, "Cards is null");
        Assert.isTrue(cards.length == 7 , "Cards should be exactly 7");

        this.cards = new TreeSet<>();
        this.cards.addAll(Arrays.asList(cards));
    }
}
