package edu.mum.asd.framework;

import edu.mum.asd.framework.bridge.IDrawing;
import edu.mum.asd.framework.iterator.LinkedList;
import edu.mum.asd.framework.iterator.ListIterator;
import javafx.scene.canvas.GraphicsContext;

public abstract class CardPile implements Cloneable {

	protected static String names[] = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" };
	protected LinkedList cards;
	public static int numberSuits = 4;
	protected int x;
	protected int y;
	
	protected IDrawing drawing;

	CardPile(int x, int y) {
		this.x = x;
		this.y = y;
		cards = new LinkedList();
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		
		LinkedList clonedCards = new LinkedList();
		
		ListIterator iterator = cards.iterator();
		while (!iterator.atEnd()) {
			clonedCards.add(((Card)iterator.current()).clone());
			iterator.next();
		}
		
		CardPile clone = (CardPile) super.clone();
		clone.cards=new LinkedList();
		iterator = clonedCards.iterator();
		while (!iterator.atEnd()) {
			clone.cards.add(((Card)iterator.current()));
			iterator.next();
		}
		
		return clone;
	}

	public LinkedList getCards() {
		return cards;
	}

	public boolean includes(double tx, double ty) {
		return x <= tx && tx <= x + Card.width && y <= ty && ty <= y + Card.height;
	}

	public void display(GraphicsContext gc) {
		drawing.display(gc, cards, x, y);
	}
	
	public final Card top() {
		return (Card) cards.front();
	}

	public abstract boolean canTake(Card card);

	public void addCard(Card card) {
		cards.add(card);
	}

	public int getNoCards() {
		int count = 0;
		ListIterator iterator = cards.iterator();
		while (!iterator.atEnd()) {
			count++;
			iterator.next();
		}
		return count;
	}

}
