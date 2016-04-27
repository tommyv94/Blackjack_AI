import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    
    private ArrayList<Card> cardDeck;
    
    // constructor creates a list of 52 cards
    public Deck() {
        // create empty ArrayList of with 52 as the capactiy
        cardDeck = new ArrayList<Card>(52);
        
        // use for loop to add new cards to the deck
        for (int suit = 1; suit <= 4; suit++) {
            for (int value = 2; value <= 14; value++) {
                cardDeck.add(new Card(suit, value));
            }
        }
    }
    
    public void shuffle() {
        // shuffle deck using the Collections static method shuffle()
        Collections.shuffle(cardDeck);
    }
    
    public Card deal() {
        // remove the first card and return its reference
        return cardDeck.remove(0);
    }
}