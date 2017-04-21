package com.jonathan.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Card implements Comparable<Card> {
    private Suit suit;
    private Value value;

    @Override
    public int compareTo(Card other) {
        int EQUAL = 0;

        if (Objects.equals(this, other)) return EQUAL;
        if (Objects.equals(this.suit.rank, other.suit.rank) && Objects.equals(this.value.rank, other.value.rank)) return EQUAL;

        if (Objects.equals(this.suit.rank, other.suit.rank)) {
            return this.value.rank.compareTo(other.value.rank);
        } else {
            return this.suit.rank.compareTo(other.suit.rank);
        }
    }

    @Getter
    @AllArgsConstructor
    public enum Suit {
        SPADE(4),
        HEART(3),
        CLOVER(2),
        DIAMOND(1),
        ;

        private Integer rank;
    }

    @Getter
    @AllArgsConstructor
    public enum Value {
        TWO(2),
        THREE(3),
        FOUR(4),
        FIVE(5),
        SIX(6),
        SEVEN(7),
        EIGHT(8),
        NINE(9),
        TEN(10),
        JACK(11),
        QUEEN(12),
        KING(13),
        ACE(14),
        ;

        private Integer rank;
    }

    public enum Rank {
        STRAIGHT_FLUSH,
        FOUR_OF_A_KIND,
        FULL_HOUSE,
        FLUSH,
        STRAIGHT,
        THREE_OF_A_KIND,
        TWO_PAIR,
        ONE_PAIR,
        UNMATCHED,
        ;
    }
}
