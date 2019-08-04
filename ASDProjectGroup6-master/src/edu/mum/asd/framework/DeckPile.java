package edu.mum.asd.framework;

import edu.mum.asd.framework.bridge.BasicDrawing;
import edu.mum.asd.framework.iterator.LinkedList;

public class DeckPile extends CardPile{
	
	public DeckPile(int x, int y) {
		super(x, y);
		this.create();
		this.shuffle();
		drawing = new BasicDrawing();
	}
	
	public void create() {
		for (int i = 0; i < numberSuits; i++)
			for (int j = 0; j < names.length; j++) {
				cards.add(new Card(i, j));
			}
	}
	
	public void shuffle() {
		int count = getNoCards();
		LinkedList sorted = cards;
		cards = new LinkedList();
		LinkedList temp = new LinkedList();
		for (; count > 0; count--) {
			int limit = ((int) (Math.random() * 1000)) % count;

			// move down to a random location in pileOne
			// while poping the cards into pileTwo
			for (int i = 0; i < limit; i++)
				temp.add(sorted.pop());

			// then add the card found there
			// to our LinkedList object: cardList
			cards.add(sorted.pop());

			// now put back into pileOne the cards
			// that we poped into pileTwo
			while (!temp.empty())
				sorted.add(temp.pop());
		}
	}

	@Override
	public boolean canTake(Card card) {
		return false;
	}

}
