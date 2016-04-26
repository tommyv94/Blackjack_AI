import java.util.Collections;
import java.util.Vector;

public class Deck {
	public Vector<String> deck = new Vector<String>();
	
	public Deck() {
		this.buildDeck(1);
		this.shuffleDeck();
	}
		
	public void shuffleDeck() {
		Collections.shuffle(deck);
	}
	public void printDeck() {
		for( int i = 0; i < deck.size(); i++) {
			System.out.println(deck.get(i));
		}
	}
	
	private void buildDeck(int x) {
		if (0 < x && x < 14) {
	        if (x == 13) {
	            deck.add("K:C");
	            buildDeck(x+1);
	        }
	        else if (x == 12) {
	            deck.add("Q:C");
	            buildDeck(x+1);
	        }
	        else if (x == 11) {
	            deck.add("J:C");
	            buildDeck(x+1);
	        }
	        else if (x == 1) {
	            deck.add("A:C");
	            buildDeck(x+1);
	        }
	        else {
	            String y = Integer.toString(x);
	            deck.add(y+":C");
	            buildDeck(x+1);
	        }
		}
		else if(13 < x && x < 27) {
	        if (x-13 == 13) {
	            deck.add("K:D");
	            buildDeck(x+1);
	        }
	        else if (x-13 == 12) {
	            deck.add("Q:D");
	            buildDeck(x+1);
	        }
	        else if (x-13 == 11) {
	            deck.add("J:D");
	            buildDeck(x+1);
	        }
	        else if (x-13 == 1) {
	            deck.add("A:D");
	            buildDeck(x+1);
	        }
	        else {
	            String y = Integer.toString(x-13);
	            deck.add(y+":D");
	            buildDeck(x+1);
	        }
		}
		else if (26 < x && x < 40) {
	        if (x-26 == 13) {
	            deck.add("K:H");
	            buildDeck(x+1);
	        }
	        else if (x-26 == 12) {
	            deck.add("Q:H");
	            buildDeck(x+1);
	        }
	        else if (x-26 == 11) {
	            deck.add("J:H");
	            buildDeck(x+1);
	        }
	        else if (x-26 == 1) {
	            deck.add("A:H");
	            buildDeck(x+1);
	        }
	        else {
	            String y = Integer.toString(x-26);
	            deck.add(y+":H");
	            buildDeck(x+1);
	        }
		}
	    else {
	        if (x-39 == 13) {
	            deck.add("K:S");
	            buildDeck(x+1);
	        }
	        else if (x-39 == 12) {
	            deck.add("Q:S");
	            buildDeck(x+1);
	        }
	        else if (x-39 == 11) {
	            deck.add("J:S");
	            buildDeck(x+1);
	        }
            else if (x-39 == 1) {
	            deck.add("A:S");
	            buildDeck(x+1);
            }
            else if (x>52) {
	            System.out.println("Deck Built");
            }
	        else {
	            String y = Integer.toString(x-39);
	            deck.add(y+":S");
	            buildDeck(x+1);
	        }
	    }
	}
}