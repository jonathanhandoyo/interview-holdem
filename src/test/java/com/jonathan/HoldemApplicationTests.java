package com.jonathan;

import com.jonathan.domain.Card;
import com.jonathan.domain.Deck;
import com.jonathan.domain.Hand;
import com.jonathan.service.PlayService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HoldemApplicationTests {

    @Autowired private PlayService playService;

	@Test
	public void contextLoads() {
	}

	@Test
    public void test1_DeckInitialized() {
	    Deck deck = new Deck();
        Assert.assertTrue("Deck is not complete", deck.getRemainingCards().size() == 52);
    }

    @Test
    public void test2_HandInitialized() {
	    Deck deck = new Deck();
        Hand hand = this.playService.deal(deck);

        Assert.assertTrue("Deck.remainingCards not correct", deck.getRemainingCards().size() == (52 - 7));
        Assert.assertTrue("Dect.dealtCards not correct", deck.getDealtCards().size() == 7);
        Assert.assertTrue("Hand.cards not correct", hand.getCards().size() == 7);
    }

    @Test
    public void test3_CheckFullHouse() {
        Hand hand = new Hand(
                new Card(Card.Suit.SPADE, Card.Value.TWO),
                new Card(Card.Suit.HEART, Card.Value.TWO),
                new Card(Card.Suit.CLOVER, Card.Value.TWO),
                new Card(Card.Suit.SPADE, Card.Value.THREE),
                new Card(Card.Suit.HEART, Card.Value.THREE),
                new Card(Card.Suit.SPADE, Card.Value.EIGHT),
                new Card(Card.Suit.SPADE, Card.Value.NINE)
        );
        hand = this.playService.check(hand);

        Assert.assertTrue("Hand is not a Full House", Card.Rank.FULL_HOUSE.equals(hand.getRank()));
    }

    @Test
    public void test3_CheckFlush() {
        Hand hand = new Hand(
                new Card(Card.Suit.SPADE, Card.Value.TWO),
                new Card(Card.Suit.SPADE, Card.Value.THREE),
                new Card(Card.Suit.SPADE, Card.Value.FOUR),
                new Card(Card.Suit.SPADE, Card.Value.SIX),
                new Card(Card.Suit.SPADE, Card.Value.SEVEN),
                new Card(Card.Suit.SPADE, Card.Value.EIGHT),
                new Card(Card.Suit.SPADE, Card.Value.NINE)
        );
        hand = this.playService.check(hand);

        Assert.assertTrue("Hand is not a Flush", Card.Rank.FLUSH.equals(hand.getRank()));
    }

    @Test
    public void test3_CheckFourOfAKind() {
        Hand hand = new Hand(
                new Card(Card.Suit.SPADE, Card.Value.TWO),
                new Card(Card.Suit.HEART, Card.Value.TWO),
                new Card(Card.Suit.CLOVER, Card.Value.TWO),
                new Card(Card.Suit.DIAMOND, Card.Value.TWO),
                new Card(Card.Suit.SPADE, Card.Value.SEVEN),
                new Card(Card.Suit.SPADE, Card.Value.EIGHT),
                new Card(Card.Suit.SPADE, Card.Value.NINE)
        );
        hand = this.playService.check(hand);

        Assert.assertTrue("Hand is not a Four of a Kind", Card.Rank.FOUR_OF_A_KIND.equals(hand.getRank()));
    }

    @Test
    public void test3_CheckThreeOfAKind() {
        Hand hand = new Hand(
                new Card(Card.Suit.SPADE, Card.Value.TWO),
                new Card(Card.Suit.HEART, Card.Value.TWO),
                new Card(Card.Suit.CLOVER, Card.Value.TWO),
                new Card(Card.Suit.HEART, Card.Value.SIX),
                new Card(Card.Suit.CLOVER, Card.Value.SEVEN),
                new Card(Card.Suit.SPADE, Card.Value.EIGHT),
                new Card(Card.Suit.SPADE, Card.Value.NINE)
        );
        hand = this.playService.check(hand);

        Assert.assertTrue("Hand is not a Three of a Kind", Card.Rank.THREE_OF_A_KIND.equals(hand.getRank()));
    }

    @Test
    public void test3_CheckOnePair() {
        Hand hand = new Hand(
                new Card(Card.Suit.SPADE, Card.Value.TWO),
                new Card(Card.Suit.HEART, Card.Value.TWO),
                new Card(Card.Suit.HEART, Card.Value.FIVE),
                new Card(Card.Suit.CLOVER, Card.Value.SIX),
                new Card(Card.Suit.CLOVER, Card.Value.SEVEN),
                new Card(Card.Suit.DIAMOND, Card.Value.EIGHT),
                new Card(Card.Suit.DIAMOND, Card.Value.NINE)
        );
        hand = this.playService.check(hand);

        Assert.assertTrue("Hand is not a One Pair", Card.Rank.ONE_PAIR.equals(hand.getRank()));
    }
}
