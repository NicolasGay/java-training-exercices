package io.robusta.hand.solution;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import io.robusta.hand.Card;
import io.robusta.hand.HandClassifier;
import io.robusta.hand.HandValue;
import io.robusta.hand.interfaces.IDeck;
import io.robusta.hand.interfaces.IHand;
import io.robusta.hand.interfaces.IHandResolver;

public class Hand extends TreeSet<Card> implements IHand {

	private static final long serialVersionUID = 7824823998655881611L;

	public Hand() {
		// TODO Auto-generated constructor stub
	}

	public Hand(TreeSet<Card> singleCards) {
		this.addAll(singleCards);
	}

	@Override
	public Set<Card> changeCards(IDeck deck, Set<Card> cards) {
		// For exemple remove three cards from this hand
		// , and get 3 new ones from the Deck
		// returns the new given cards
		return null;
	}

	/**
	 * beats is the same than compareTo, but with a nicer name. The problem is
	 * that it does not handle equality :(
	 * 
	 * @param villain
	 * @return
	 */
	@Override
	public boolean beats(IHand villain) {

		return false;
	}

	@Override
	public IHand getHand() {
		return this;
	}

	@Override
	public HandClassifier getClassifier() {
		// OK, do not touch, see getValue()
		return this.getValue().getClassifier();
	}

	@Override
	public boolean isStraight() {

		int count = 0;
		Card previous = this.first();

		for (Card current : this) {
			if (current.getValue() == previous.getValue() + 1) {
				count++;
				previous = current;
			} else {
				previous = current;
			}
		}

		if (count == 4) {
			return true;
		}

		return false;
	}

	@Override
	public boolean isFlush() {

		int count = 0;
		Card previous = this.first();

		for (Card current : this) {

			if (current.getColor() == previous.getColor()) {
				count++;
				previous = current;
			} else {
				previous = current;
			}

		}

		if (count == 5) {
			return true;
		}

		return false;
	}

	/**
	 * Returns number of identical cards 5s5cAd2s3s has two cardValue of 5
	 */
	@Override
	public int number(int cardValue) {
		int result = 0;
		for (Card current : this) {
			if (current.getValue() == cardValue) {
				result++;
			}
		}
		return result;
	}

	/**
	 * The fundamental map Check the tests and README to understand
	 */
	@Override
	public Map<Integer, List<Card>> group() {

		Map<Integer, List<Card>> map = new TreeMap<Integer, List<Card>>();
		List<Card> cards = new ArrayList<Card>();

		for (Card current : this) {
			if (map.containsKey(current.getValue())) {
				cards.add(current);
			} else {
				map.put(current.getValue(), new ArrayList<Card>());
			}
			map.get(current.getValue()).add(current);
		}

		return map;
	}

	// different states of the hand
	// Using stateful variables. We need to fill this, then use it before.
	int levelValue = 0;
	// Needed with two pairs or full
	int secondValue = 0;
	// Put all cards for flush or highCard ;
	TreeSet<Card> singleCards = new TreeSet<>();

	/**
	 * return all single cards not used to build the classifier
	 *
	 * @param map
	 * @return
	 */
	TreeSet<Card> getSingleCards(Map<Integer, List<Card>> map) {
		// method is done, DO NOT TOUCH !
		TreeSet<Card> singleCards = new TreeSet<>();
		// May be adapted at the end of project:
		// if straight or flush : return empty
		// If High card, return 4 cards
		for (List<Card> group : map.values()) {
			if (group.size() == 1) {
				singleCards.add(group.get(0));
			}
		}
		return singleCards;
	}

	@Override
	public boolean isPair() {

		for (int i = 2; i < 15; i++) {
			if (this.group().get(i).size() == 2) {
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean isDoublePair() {

		int counter1 = 0;
		int counter2 = 0;

		for (int i = 2; i < 15; i++) {

			if (this.group().get(i).size() == 2) {
				if (counter1 == 0 && counter2 == 0) {
					counter1 = 1;
				} else if (counter1 != 0 && counter2 == 0) {
					counter2 = 1;
				} else {
					return false;
				}
			}

		}

		if (counter1 + counter2 == 2) {
			return true;
		}

		return false;
	}

	@Override
	public boolean isHighCard() {

		if (this.isPair() || this.isDoublePair() || this.isFlush() || this.isStraight() || this.isStraightFlush()
				|| this.isFourOfAKind() || this.isTrips()) {
			return false;
		}
		return true;

	}

	@Override
	public boolean isTrips() {

		return false;
	}

	@Override
	public boolean isFourOfAKind() {

		return false;

	}

	@Override
	public boolean isFull() {
		return false;
	}

	@Override
	public boolean isStraightFlush() {

		if (this.isStraight() && this.isFlush()) {
			return true;
		}

		return false;
	}

	@Override
	public HandValue getValue() {
		HandValue handValue = new HandValue();

		// Exemple for FourOfAKind ; // do for all classifiers
		if (this.isFourOfAKind()) {
			handValue.setClassifier(HandClassifier.FOUR_OF_A_KIND);
			handValue.setLevelValue(this.levelValue);
			handValue.setSingleCards(this.singleCards); // or
														// this.getsingleCards()
			return handValue;
		}

		if (this.isPair() && !this.isDoublePair()) {
			handValue.setClassifier(HandClassifier.PAIR);
			handValue.setLevelValue(this.levelValue);
			handValue.setSingleCards(this.singleCards);
		}

		if (this.isDoublePair()) {
			handValue.setClassifier(HandClassifier.TWO_PAIR);
			handValue.setLevelValue(this.levelValue);
			handValue.setSingleCards(this.singleCards);
		}

		// For the flush, all singleCards are needed

		return handValue;
	}

	@Override
	public boolean hasCardValue(int level) {

		if (this.number(level) != 0) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	public boolean hasAce() {

		if (this.number(14) != 0) {
			return true;
		}
		return false;
	}

	@Override
	public int highestValue() {

		// Les cartes sont rangées dans l'ordre naturel (par valeur croissante)
		return this.last().getValue();

	}

	@Override
	public int compareTo(IHandResolver o) {
		// You should reuse HandValue.compareTo()
		return 0;
	}

}