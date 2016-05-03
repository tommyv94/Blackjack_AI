import java.util.Collections;
import java.util.Vector;

public class Deck {
	public int rs = 26;
	public Vector<Card> deck = new Vector<Card>();
	
	public Deck() {
		this.buildDeck(1);
		this.shuffleDeck();
	}
		
	public void shuffleDeck() {
		Collections.shuffle(deck);
	}
	public void printDeck() {
		for( int i = 0; i < deck.size(); i++) {
			Card c = deck.get(i);
			System.out.println(c.getSuit()+":"+c.getVal()+"  ");
		}
	}
	
	private void buildDeck(int x) {
		if (0 < x && x < 14) {
	        if (x == 13) {
	            deck.add(new Card("C","K"));
	            buildDeck(x+1);
	        }
	        else if (x == 12) {
	            deck.add(new Card("C","Q"));
	            buildDeck(x+1);
	        }
	        else if (x == 11) {
	            deck.add(new Card("C","J"));
	            buildDeck(x+1);
	        }
	        else if (x == 1) {
	            deck.add(new Card("C","A"));
	            buildDeck(x+1);
	        }
	        else {
	            String y = Integer.toString(x);
	            deck.add(new Card("C",y));
	            buildDeck(x+1);
	        }
		}
		else if(13 < x && x < 27) {
	        if (x-13 == 13) {
	            deck.add(new Card("D","K"));
	            buildDeck(x+1);
	        }
	        else if (x-13 == 12) {
	            deck.add(new Card("D","Q"));
	            buildDeck(x+1);
	        }
	        else if (x-13 == 11) {
	            deck.add(new Card("D","J"));
	            buildDeck(x+1);
	        }
	        else if (x-13 == 1) {
	            deck.add(new Card("D","A"));
	            buildDeck(x+1);
	        }
	        else {
	            String y = Integer.toString(x-13);
	            deck.add(new Card("D",y));
	            buildDeck(x+1);
	        }
		}
		else if (26 < x && x < 40) {
	        if (x-26 == 13) {
	            deck.add(new Card("H","K"));
	            buildDeck(x+1);
	        }
	        else if (x-26 == 12) {
	            deck.add(new Card("H","Q"));
	            buildDeck(x+1);
	        }
	        else if (x-26 == 11) {
	            deck.add(new Card("H","J"));
	            buildDeck(x+1);
	        }
	        else if (x-26 == 1) {
	            deck.add(new Card("H","A"));
	            buildDeck(x+1);
	        }
	        else {
	            String y = Integer.toString(x-26);
	            deck.add(new Card("H",y));
	            buildDeck(x+1);
	        }
		}
	    else {
	        if (x-39 == 13) {
	            deck.add(new Card("S","K"));
	            buildDeck(x+1);
	        }
	        else if (x-39 == 12) {
	            deck.add(new Card("S","Q"));
	            buildDeck(x+1);
	        }
	        else if (x-39 == 11) {
	            deck.add(new Card("S","J"));
	            buildDeck(x+1);
	        }
            else if (x-39 == 1) {
	            deck.add(new Card("S","A"));
	            buildDeck(x+1);
            }
            else if (x>52) {
	            System.out.println("Deck Built\n\n");
            }
	        else {
	            String y = Integer.toString(x-39);
	            deck.add(new Card("S",y));
	            buildDeck(x+1);
	        }
	    }
	}
	
	public Card Deal() throws InterruptedException {
		if(deck.size() <= rs) {
			System.out.println("\nReshuffle...");
			deck.clear();
			this.buildDeck(1);
			this.shuffleDeck();
			Thread.sleep(5000);
		}
		Card c = deck.firstElement();
		deck.remove(0);
		return c;
	}
	
	public void start(Player p, Dealer d) throws InterruptedException {
		if(this.deck.size() < 4) {
			System.out.println("\nReshuffle...");
			deck.clear();
			this.buildDeck(1);
			this.shuffleDeck();
			Thread.sleep(5000);
		}
		for(int i = 0; i < 4; i++) {
			if(i == 0 || i == 2) {
				Card c = this.deck.firstElement();
				this.deck.remove(0);
				p.addCard(c);
			}
			else {
				Card c = this.deck.firstElement();
				this.deck.remove(0);
				d.addCard(c);
			}
		}
	}
}