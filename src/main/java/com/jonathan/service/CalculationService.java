package com.jonathan.service;

import com.jonathan.domain.Card;
import com.jonathan.domain.Hand;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.SetUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Service
public class CalculationService {

    public Hand findHighestRank(Hand hand) {
        Assert.notNull(hand, "Hand is null");
        Assert.notNull(hand.getCards(), "Hand.Card is null");

        SortedSet<Card> rankedCards;

        rankedCards = this.getStraightFlush(hand.getCards());  if (CollectionUtils.isNotEmpty(rankedCards)) return this.finalize(hand, rankedCards, Card.Rank.STRAIGHT_FLUSH);
        rankedCards = this.getFourOfAKind(hand.getCards());    if (CollectionUtils.isNotEmpty(rankedCards)) return this.finalize(hand, rankedCards, Card.Rank.FOUR_OF_A_KIND);
        rankedCards = this.getFullHouse(hand.getCards());      if (CollectionUtils.isNotEmpty(rankedCards)) return this.finalize(hand, rankedCards, Card.Rank.FULL_HOUSE);
        rankedCards = this.getFlush(hand.getCards());          if (CollectionUtils.isNotEmpty(rankedCards)) return this.finalize(hand, rankedCards, Card.Rank.FLUSH);
        rankedCards = this.getStraight(hand.getCards());       if (CollectionUtils.isNotEmpty(rankedCards)) return this.finalize(hand, rankedCards, Card.Rank.STRAIGHT);
        rankedCards = this.getThreeOfAKind(hand.getCards());   if (CollectionUtils.isNotEmpty(rankedCards)) return this.finalize(hand, rankedCards, Card.Rank.THREE_OF_A_KIND);
        rankedCards = this.getTwoPair(hand.getCards());        if (CollectionUtils.isNotEmpty(rankedCards)) return this.finalize(hand, rankedCards, Card.Rank.TWO_PAIR);
        rankedCards = this.getOnePair(hand.getCards());        if (CollectionUtils.isNotEmpty(rankedCards)) return this.finalize(hand, rankedCards, Card.Rank.ONE_PAIR);
        rankedCards = this.getUnmatched(hand.getCards());      if (CollectionUtils.isNotEmpty(rankedCards)) return this.finalize(hand, rankedCards, Card.Rank.UNMATCHED);

        return null;
    }

    private Hand finalize(Hand hand, SortedSet<Card> ranked, Card.Rank rank) {
        hand.setRankedCards(ranked);
        hand.setLeftoverCards(SetUtils.difference(hand.getCards(), hand.getRankedCards()).toSet());
        hand.setRank(rank);

        return hand;
    }

    private SortedSet<Card> getStraightFlush(Set<Card> cards) {
        TreeSet sorted = new TreeSet();
        sorted.addAll(cards);

        return null;
    }

    private SortedSet<Card> getFourOfAKind(Set<Card> cards) {
        TreeSet<Card> sorted = new TreeSet<>();
        sorted.addAll(cards);

        Set<Card> fours;

        for (Card.Value value: Card.Value.values()) {
            fours = cards.stream().filter(card -> card.getValue() == value).collect(Collectors.toSet());
            if (fours != null && fours.size() == 4) {
                TreeSet<Card> result = new TreeSet<>();

                sorted.removeAll(fours);
                result.addAll(fours);
                result.add(sorted.last()); sorted.remove(sorted.last());

                return result;
            }
        }

        return null;
    }

    private SortedSet<Card> getFullHouse(Set<Card> cards) {
        TreeSet sorted = new TreeSet();
        sorted.addAll(cards);

        TreeSet<Card> result = new TreeSet<>();

        for (Card.Value value: Card.Value.values()) {
            Set<Card> temp = cards.stream().filter(card -> card.getValue() == value).collect(Collectors.toSet());
            if (temp != null && temp.size() == 3) {
                sorted.removeAll(temp);
                result.addAll(temp);
            }

            if (temp != null && temp.size() == 2) {
                sorted.removeAll(temp);
                result.addAll(temp);
            }
        }

        return result.size() == 5 ? result : null;
    }

    private SortedSet<Card> getFlush(Set<Card> cards) {
        TreeSet<Card> sorted = new TreeSet<>();
        sorted.addAll(cards);

        Set<Card> flushed;
        for (Card.Suit suit: Card.Suit.values()) {
            flushed = cards.stream().filter(card -> card.getSuit() == suit).collect(Collectors.toSet());
            if (flushed != null && flushed.size() >= 5) {
                TreeSet<Card> result = new TreeSet<>();

                sorted.removeAll(flushed);
                result.addAll(flushed);

                for (int i = 0; i < (5 - flushed.size()); i++) {
                    result.add(sorted.last());
                    sorted.remove(sorted.last());
                }

                return result;
            }
        }

        return null;
    }

    private SortedSet<Card> getStraight(Set<Card> cards) {
        TreeSet<Card> sorted = new TreeSet<>();
        sorted.addAll(cards);

        Card first = sorted.first();
        Card last = sorted.last();

        return null;
    }

    private SortedSet<Card> getThreeOfAKind(Set<Card> cards) {
        TreeSet<Card> sorted = new TreeSet<>();
        sorted.addAll(cards);

        Set<Card> threes;

        for (Card.Value value: Card.Value.values()) {
            threes = cards.stream().filter(card -> card.getValue() == value).collect(Collectors.toSet());
            if (threes != null && threes.size() == 3) {
                TreeSet<Card> result = new TreeSet<>();

                sorted.removeAll(threes);
                result.addAll(threes);
                result.add(sorted.last()); sorted.remove(sorted.last());
                result.add(sorted.last()); sorted.remove(sorted.last());

                return result;
            }
        }

        return null;
    }

    private SortedSet<Card> getTwoPair(Set<Card> cards) {
        TreeSet sorted = new TreeSet();
        sorted.addAll(cards);

        return null;
    }

    private SortedSet<Card> getOnePair(Set<Card> cards) {
        TreeSet<Card> sorted = new TreeSet<>();
        sorted.addAll(cards);

        Set<Card> twos;

        for (Card.Value value: Card.Value.values()) {
            twos = cards.stream().filter(card -> card.getValue() == value).collect(Collectors.toSet());
            if (twos != null && twos.size() == 2) {
                TreeSet<Card> result = new TreeSet<>();

                sorted.removeAll(twos);
                result.addAll(twos);
                result.add(sorted.last()); sorted.remove(sorted.last());
                result.add(sorted.last()); sorted.remove(sorted.last());
                result.add(sorted.last()); sorted.remove(sorted.last());

                return result;
            }
        }

        return null;
    }

    private SortedSet<Card> getUnmatched(Set<Card> cards) {
        TreeSet<Card> sorted = new TreeSet<>();
        sorted.addAll(cards);

        TreeSet<Card> result = new TreeSet<>();

        for (int i = 0; i < 5; i++) {
            result.add(sorted.last());
            sorted.remove(sorted.last());
        }

        return result;
    }
}
