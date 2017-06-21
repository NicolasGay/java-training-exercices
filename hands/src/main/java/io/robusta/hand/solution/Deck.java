package io.robusta.hand.solution;

import java.util.Collections;
import java.util.LinkedList;
import java.util.TreeSet;

import io.robusta.hand.Card;
import io.robusta.hand.interfaces.IDeck;

public class Deck extends LinkedList<Card> implements IDeck {

	private static final long serialVersionUID = -4686285366508321800L;

	public Deck() {

	}

	@Override
	public Card pick() {

		// shuffle;
		Collections.shuffle(this);

		// remove card from deck and returns it
		Card pickedCard = this.remove();
		return pickedCard;
	}

	@Override
	public TreeSet<Card> pick(int number) {
		// reuse pick()

		TreeSet<Card> pickedCards = new TreeSet<>();
		Card newlyPickedCard;

		for (int i = 0; i < number; i++) {
			newlyPickedCard = this.pick();
			pickedCards.add(newlyPickedCard);
		}

		return pickedCards;
	}

	@Override
	public Hand giveHand() {
		// A hand is a **5** card TreeSet

		TreeSet<Card> pickedCards = this.pick(5);
		Hand hand = new Hand(pickedCards);
		return hand;
	}

}
