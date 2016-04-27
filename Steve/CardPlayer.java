import java.util.ArrayList;

public class CardPlayer {
    
    private ArrayList<Card> cards;
    
    public CardPlayer() {
        // create empty ArrayList for the hand of cards
        cards = new ArrayList<Card>();
    }
    
    public void getCard(Card card) {
        // add the card to the hand
        cards.add(card);
    }
    
    public void showCards() {
        // go through all the cards in the hand
        for (Card card : cards) {
            // print the value and suit
            System.out.println(card.getValueAsString() + " of " + card.getSuitAsString());
        }
    }
    
}